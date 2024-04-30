package com.danzir.model.webhook.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PotentialAction {

    @SerializedName("@type")
    private String type;

    private String name;

    private Target[] targets;
}
