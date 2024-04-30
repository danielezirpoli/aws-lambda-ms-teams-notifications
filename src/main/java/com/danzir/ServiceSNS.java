package com.danzir;

import com.danzir.client.WebhookClient;
import com.danzir.deserializer.MessageDeserializer;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ServiceSNS {

    public List<String> handleRequest(SNSEvent event){
        List<String> messagesFound = new ArrayList<>();
        for (SNSEvent.SNSRecord record : event.getRecords()) {
            SNSEvent.SNS notification = record.getSNS();
            String subject = notification.getSubject();
            log.info("subject: {}", subject);
            if(subject.equalsIgnoreCase("State: ALERTING")){
                postToTeams(notification);
                messagesFound.add(notification.toString());
            }
        }
        return messagesFound;
    }

    private void postToTeams(SNSEvent.SNS notification) {
        String message = notification.getMessage();
        log.info("Post to MS Teams: {}", message);
        WebhookClient client = new WebhookClient();
        client.hitTeamsWebhook(MessageDeserializer.deserialize(message));
    }

}
