package com.danzir.model.webhook.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Section {

    private String activityTitle;
    private String activitySubtitle;
    private Fact[] facts;
}
