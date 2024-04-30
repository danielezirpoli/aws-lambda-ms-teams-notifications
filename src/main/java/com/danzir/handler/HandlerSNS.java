package com.danzir.handler;

import com.danzir.ServiceSNS;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class HandlerSNS implements RequestHandler<SNSEvent, Object> {

    @Override
    public List<String> handleRequest(SNSEvent event, Context context) {
        System.setProperty("jdk.httpclient.HttpClient.log", "all");
        log.info("EVENT TYPE: " + event.getClass().toString());
        ServiceSNS service = new ServiceSNS();
        return service.handleRequest(event);
    }

}
