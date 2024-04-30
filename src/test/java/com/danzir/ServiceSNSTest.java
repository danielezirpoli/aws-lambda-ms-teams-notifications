package com.danzir;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ServiceSNSTest {
    
    @Test
    public void postToTeamsTest(){
        SNSEvent.SNS sns1 = new SNSEvent.SNS();
        sns1.setMessage("{\\n    \\\"body\\\": \\\"Some of the endpoints don't meet the expected \\\\\\\"Response time\\\\\\\" KPI (Response time \\\\u003c= 3000 ms)\\\",\\n    \\\"name\\\": \\\"Response time KPI not satisfied\\\",\\n    \\\"state\\\": \\\"alerting\\\",\\n    \\\"path\\\": \\\"/d/-9uhfTQVk/load-test\\\"\\n}");
        sns1.setSubject("State: ALERTING");
        SNSEvent.SNSRecord record1 = new SNSEvent.SNSRecord();
        record1.setSns(sns1);

        SNSEvent.SNS sns2 = new SNSEvent.SNS();
        sns2.setMessage("{\\n    \\\"body\\\": \\\"Some of the endpoints don't meet the expected \\\\\\\"Response time\\\\\\\" KPI (Response time \\\\u003c= 3000 ms)\\\",\\n    \\\"name\\\": \\\"Response time KPI not satisfied\\\",\\n    \\\"state\\\": \\\"no_data\\\",\\n    \\\"path\\\": \\\"/d/-9uhfTQVk/load-test\\\"\\n}");
        sns2.setSubject("State: NO_DATA");
        SNSEvent.SNSRecord record2 = new SNSEvent.SNSRecord();
        record2.setSns(sns2);

        SNSEvent event = new SNSEvent();
        event.withRecords(Arrays.asList(record1, record2));

        ServiceSNS service = new ServiceSNS();
        service.handleRequest(event);
    }

}
