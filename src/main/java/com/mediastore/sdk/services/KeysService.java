package com.mediastore.sdk.services;

import com.mediastore.sdk.http.HttpClient;
import com.mediastore.sdk.models.APIKey;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeysService {

    private final HttpClient httpClient;

    public KeysService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<APIKey> list() throws IOException {
        ListResponse response = httpClient.get("/api/keys", ListResponse.class);
        return response.getKeys();
    }

    public APIKey create(String name) throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);

        CreateResponse response = httpClient.post("/api/keys", body, CreateResponse.class);
        return response.getApiKey();
    }

    public void rename(Long id, String newName) throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("name", newName);

        httpClient.patch("/api/keys/" + id, body, RenameResponse.class);
    }

    public void revoke(Long id) throws IOException {
        httpClient.delete("/api/keys/" + id, RevokeResponse.class);
    }

    private static class ListResponse {
        private boolean success;
        private List<APIKey> keys;

        public List<APIKey> getKeys() { return keys; }
        public void setKeys(List<APIKey> keys) { this.keys = keys; }
    }

    private static class CreateResponse {
        private boolean success;
        private APIKey api_key;

        public APIKey getApiKey() { return api_key; }
        public void setApiKey(APIKey apiKey) { this.api_key = apiKey; }
    }

    private static class RenameResponse {
        private boolean success;
    }

    private static class RevokeResponse {
        private boolean success;
    }

}
