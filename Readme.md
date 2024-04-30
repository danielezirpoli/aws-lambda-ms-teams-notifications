# Description
Need: push Grafana alerts to MS Teams.
Issue: Amazon Managed Grafana doesn't support native integration with MS Teams.
Solution: push Grafana alerts to AWS SNS topic and then to MS Teams through an AWS Lambda function.
This project implements the Lambda function triggered by SNS notifications sent by Grafana alerts. It pushes messages in MS Teams through webhooks.

# Tools setup
## AWS SNS setup
Create an SNS topic in AWS with type Standard: https://docs.aws.amazon.com/sns/latest/dg/sns-create-topic.html

## Grafana alert setup
Create alert with SNS integration: https://docs.aws.amazon.com/grafana/latest/userguide/old-alert-notifications.html#old-amazon-sns

## MS Teams setup
Create webhook: https://learn.microsoft.com/en-us/microsoftteams/platform/webhooks-and-connectors/how-to/add-incoming-webhook?tabs=dotnet

# How to deploy
## Setup AWS role
Create a role (example ms-teams-notifications) to be used through AssumeRole by the Lambda (arn to be set in serverless.yml - provider.iam.role)

## AWS user setup
Create a user in AWS console with proper permissions to run serverless deploy command

## Generate access key
Generate access key for user created at previous step

## Set AWS local profile
Add in the configuration file ~/.aws/credentials a profile (example my-sls-user) with generated access key
```
[my-sls-user]
aws_access_key_id = XXXXXXXX
aws_secret_access_key = YYYYYYY
```

## Run local build command
```
mvn clean package -DskipTests
```

## Run local deploy command
```
serverless deploy --aws-profile my-sls-user
```

## Subscribe Lambda to SNS
Manually subscribe the deployed Lambda function to an SNS topic

# Expected SNS payload
Here an example of typical message sent by Grafana alerts.
This Lambda implementation only needs Subject and Message fields
```
{
    "Type": "Notification",
    "MessageId": "62f72b17-0c96-5356-8a2d-77e067630150",
    "TopicArn": "arn:aws:sns:us-east-1:2473803663:grafana-alerts",
    "Subject": "State: ALERTING",
    "Message": "{\n    \"body\": \"Some of the endpoints don't meet the expected \\\"Response time\\\" KPI (Response time \\u003c= 3000 ms)\",\n    \"name\": \"Response time KPI not satisfied\",\n    \"state\": \"alerting\",\n    \"path\": \"/d/-9uhfTQVk/load-test\"\n}",
    "Timestamp": "2023-06-05T04:51:01.910Z",
    "SignatureVersion": "1",
    "Signature": "qqeuamqyEeetHaqO5sOxVlr/ik8s8M+WQqCA==",
    "SigningCertURL": "https://danzir.com/v2/url?u=https-3A__sns.us-2Deast-2D1.amazonaws.com_SimpleNotificationService-2D01d0",
    "UnsubscribeURL": "https://danzir.com/v2/url?u=https-3A__sns.us-2Deast-2D1.amazonaws.com_-3FAction-3DUnsubscribe-26Subs"
}
```