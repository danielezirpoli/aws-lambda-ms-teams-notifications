package com.danzir.deserializer;

import com.danzir.model.notification.Message;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;

@Slf4j
public class MessageDeserializer {

    public static Message deserialize(String jsonString) {
        String jsonStringEscape = StringEscapeUtils.unescapeJson(jsonString);
        log.info("jsonStringEscape: {}", jsonStringEscape);
        return new Gson().fromJson(jsonStringEscape, Message.class);
    }
}
