package com.mediastore.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;

public class File {
    private Long id;

    @JsonProperty("original_name")
    private String originalName;

    private Long size;

    @JsonProperty("size_mb")
    private Double sizeMb;

    private String type;

    private String url;

    private Map<String, String> variants;

    @JsonProperty("uploaded_at")
    private OffsetDateTime uploadedAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOriginalName() { return originalName; }
    public void setOriginalName(String originalName) { this.originalName = originalName; }

    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }

    public Double getSizeMb() { return sizeMb; }
    public void setSizeMb(Double sizeMb) { this.sizeMb = sizeMb; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Map<String, String> getVariants() { return variants; }
    public void setVariants(Map<String, String> variants) { this.variants = variants; }

    public OffsetDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(OffsetDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}