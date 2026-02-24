package com.mediastore.sdk.services;

import com.mediastore.sdk.http.HttpClient;
import com.mediastore.sdk.models.Account;
import com.mediastore.sdk.models.UsageStats;

import java.io.IOException;

public class AccountService {

    private final HttpClient httpClient;

    public AccountService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Account get() throws IOException {
        AccountResponse response = httpClient.get("/api/account", AccountResponse.class);
        return response.getAccount();
    }

    public UsageStats stats() throws IOException {
        StatsResponse response = httpClient.get("/api/account/stats", StatsResponse.class);
        return response.getStats();
    }

    private static class AccountResponse {
        private boolean success;
        private Account account;

        public Account getAccount() { return account; }
        public void setAccount(Account account) { this.account = account; }
    }

    private static class StatsResponse {
        private boolean success;
        private UsageStats stats;

        public UsageStats getStats() { return stats; }
        public void setStats(UsageStats stats) { this.stats = stats; }
    }

}
