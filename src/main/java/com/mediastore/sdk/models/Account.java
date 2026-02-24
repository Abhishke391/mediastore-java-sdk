package com.mediastore.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Account {
    private Long id;
    private String email;

    @JsonProperty("storage_used_mb")
    private Double storageUsedMb;

    @JsonProperty("storage_limit_mb")
    private Double storageLimitMb;

    @JsonProperty("storage_percentage")
    private Double storagePercentage;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getStorageUsedMb() { return storageUsedMb; }
    public void setStorageUsedMb(Double storageUsedMb) { this.storageUsedMb = storageUsedMb; }

    public Double getStorageLimitMb() { return storageLimitMb; }
    public void setStorageLimitMb(Double storageLimitMb) { this.storageLimitMb = storageLimitMb; }

    public Double getStoragePercentage() { return storagePercentage; }
    public void setStoragePercentage(Double storagePercentage) { this.storagePercentage = storagePercentage; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}