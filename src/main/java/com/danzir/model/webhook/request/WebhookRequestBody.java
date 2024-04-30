package com.danzir.model.webhook.request;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class WebhookRequestBody {

    private String summary;
    private Section[] sections;
    private PotentialAction[] potentialAction;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
