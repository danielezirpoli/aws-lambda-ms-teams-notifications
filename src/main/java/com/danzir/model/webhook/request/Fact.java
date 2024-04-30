package com.danzir.model.webhook.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fact {

    private String name;
    private String value;

}
