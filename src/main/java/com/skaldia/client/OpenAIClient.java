package com.skaldia.client;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class OpenAIClient {

    private Client client;
    private WebTarget target;
    private WebTarget targetImg;

    @Inject
    @ConfigProperty(name="openai.secret-key")
    String secretKey;

    @PostConstruct
    void initClient() {
        client = ClientBuilder.newClient();
        target = client.target("https://api.openai.com/v1/chat/completions");
        targetImg = client.target("https://api.openai.com/v1/images/generations");
    }

    public String generateText(String prompt) {
        JsonArray messages = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("role", "system")
                        .add("content", prompt))
                .build();

        JsonObject json = Json.createObjectBuilder()
                .add("model", "gpt-4")
                .add("messages", messages)
                .build();

        String jsonString = json.toString();
        System.out.println(jsonString);

        // Faire l'appel API
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .post(Entity.json(jsonString));  // Passer la chaîne JSON ici

        return response.readEntity(String.class);
    }

    public String generatePortrait(String prompt) {
        JsonObject json = Json.createObjectBuilder()
                .add("prompt", prompt)
                .add("n", 1)
                .add("size", "512x512")
                .build();

        String jsonString = json.toString();
        System.out.println(jsonString);

        // Faire l'appel API
        Response response = targetImg.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .post(Entity.json(jsonString));  // Passer la chaîne JSON ici

        return response.readEntity(String.class);
    }

    @PreDestroy
    void close() {
        client.close();
    }
}
