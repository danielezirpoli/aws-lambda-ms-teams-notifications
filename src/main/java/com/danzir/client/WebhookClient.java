package com.danzir.client;

import com.danzir.config.Constants;
import com.danzir.model.notification.Message;
import com.danzir.model.webhook.request.Fact;
import com.danzir.model.webhook.request.PotentialAction;
import com.danzir.model.webhook.request.Section;
import com.danzir.model.webhook.request.Target;
import com.danzir.model.webhook.request.WebhookRequestBody;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class WebhookClient {

    HttpClient client = HttpClient.newHttpClient();

    public HttpResponse hitTeamsWebhook(Message message) {
        String name = message.getName();
        String body = message.getBody();
        String path = message.getPath();
        String state = message.getState();

        WebhookRequestBody requestBody = new WebhookRequestBody();
        requestBody.setSummary(name);

        Fact fact = new Fact("State", state);
        Section section = new Section(name, body, new Fact[]{fact});
        requestBody.setSections(new Section[]{section});

        String link = Constants.GRAFANA_BASE_URL + path;
        Target target = new Target("default", link);
        PotentialAction potentialAction = new PotentialAction("OpenUri", "Dashboard", new Target[]{target});
        requestBody.setPotentialAction(new PotentialAction[]{potentialAction});

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Constants.TEAMS_WEBHOOK_URL))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        log.info("Request: {}", request);

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("Response status code: {}", response.statusCode());
        log.info("Response body: {}", response.body());

        return response;
    }

}
