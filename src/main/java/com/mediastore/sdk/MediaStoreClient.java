package com.mediastore.sdk;

import com.mediastore.sdk.http.HttpClient;
import com.mediastore.sdk.services.AccountService;
import com.mediastore.sdk.services.FilesService;
import com.mediastore.sdk.services.KeysService;

public class MediaStoreClient {

    private final MediaStoreConfig config;
    private final HttpClient httpClient;

    private final FilesService files;
    private final AccountService account;
    private final KeysService keys;

    public MediaStoreClient(String apiKey) {
        this(MediaStoreConfig.builder()
                .apiKey(apiKey)
                .build());
    }

    public MediaStoreClient(MediaStoreConfig config) {
        this.config = config;
        this.httpClient = new HttpClient(config);

        this.files = new FilesService(httpClient);
        this.account = new AccountService(httpClient);
        this.keys = new KeysService(httpClient);
    }

    public FilesService files() {
        return files;
    }

    public AccountService account() {
        return account;
    }

    public KeysService keys() {
        return keys;
    }

}
