package com.danzir.model.webhook.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Target {

    private String os;
    private String uri;

}
