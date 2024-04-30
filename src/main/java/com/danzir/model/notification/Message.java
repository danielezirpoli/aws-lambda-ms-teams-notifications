package com.danzir.model.notification;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class Message {

    private String name;
    private String body;
    private String path;
    private String state;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
