package com.mediastore.sdk.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediastore.sdk.MediaStoreConfig;
import com.mediastore.sdk.exceptions.MediaStoreException;
import com.mediastore.sdk.exceptions.UnauthorizedException;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final MediaStoreConfig config;

    public HttpClient(MediaStoreConfig config) {
        this.config = config;

        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JavaTimeModule());
    }

    public <T> T get(String path, Class<T> responseType) throws IOException {
        Request request = new Request.Builder()
                .url(config.getBaseUrl() + path)
                .header("X-API-Key", config.getApiKey())
                .get()
                .build();

        return executeRequest(request, responseType);
    }

    public <T> T post(String path, Object body, Class<T> responseType) throws IOException {
        String json = objectMapper.writeValueAsString(body);

        RequestBody requestBody = RequestBody.create(
                json,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(config.getBaseUrl() + path)
                .header("X-API-Key", config.getApiKey())
                .post(requestBody)
                .build();

        return executeRequest(request, responseType);
    }

    public <T> T patch(String path, Object body, Class<T> responseType) throws IOException {
        String json = objectMapper.writeValueAsString(body);

        RequestBody requestBody = RequestBody.create(
                json,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(config.getBaseUrl() + path)
                .header("X-API-Key", config.getApiKey())
                .patch(requestBody)
                .build();

        return executeRequest(request, responseType);
    }

    public <T> T delete(String path, Class<T> responseType) throws IOException {
        Request request = new Request.Builder()
                .url(config.getBaseUrl() + path)
                .header("X-API-Key", config.getApiKey())
                .delete()
                .build();

        return executeRequest(request, responseType);
    }

    public <T> T uploadFile(String path, File file, Class<T> responseType) throws IOException {
        RequestBody fileBody = RequestBody.create(
                file,
                MediaType.parse("application/octet-stream")
        );

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();

        Request request = new Request.Builder()
                .url(config.getBaseUrl() + path)
                .header("X-API-Key", config.getApiKey())
                .post(requestBody)
                .build();

        return executeRequest(request, responseType);
    }

    private <T> T executeRequest(Request request, Class<T> responseType) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body() != null ? response.body().string() : "";

            if (!response.isSuccessful()) {
                handleErrorResponse(response.code(), responseBody);
            }

            return objectMapper.readValue(responseBody, responseType);
        }
    }

    private void handleErrorResponse(int statusCode, String responseBody) {
        String errorMessage = "Request failed";

        try {
            ErrorResponse error = objectMapper.readValue(responseBody, ErrorResponse.class);
            errorMessage = error.getError();
        } catch (IOException e) {
            // Use default message
        }

        if (statusCode == 401) {
            throw new UnauthorizedException(errorMessage);
        } else {
            throw new MediaStoreException(errorMessage, statusCode);
        }
    }

    private static class ErrorResponse {
        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

}
