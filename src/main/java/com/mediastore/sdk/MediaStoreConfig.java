package com.mediastore.sdk;

public class MediaStoreConfig {
    private String apiKey;
    private String baseUrl;

    private MediaStoreConfig(Builder builder) {
        this.apiKey = builder.apiKey;
        this.baseUrl = builder.baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String apiKey;
        private String baseUrl = "http://localhost:8081";

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public MediaStoreConfig build() {
            return new MediaStoreConfig(this);
        }
    }
}