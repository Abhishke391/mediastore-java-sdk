package com.mediastore.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UsageStats {
    @JsonProperty("total_files")
    private Long totalFiles;

    @JsonProperty("total_images")
    private Long totalImages;

    @JsonProperty("total_documents")
    private Long totalDocuments;

    @JsonProperty("storage_used_mb")
    private Double storageUsedMb;

    @JsonProperty("storage_limit_mb")
    private Double storageLimitMb;

    @JsonProperty("files_by_type")
    private List<FileTypeCount> filesByType;

    @JsonProperty("recent_uploads")
    private List<DailyUploadCount> recentUploads;

    @JsonProperty("largest_file")
    private FileSummary largestFile;

    @JsonProperty("oldest_file")
    private FileSummary oldestFile;

    @JsonProperty("newest_file")
    private FileSummary newestFile;

    // Getters and Setters
    public Long getTotalFiles() { return totalFiles; }
    public void setTotalFiles(Long totalFiles) { this.totalFiles = totalFiles; }

    public Long getTotalImages() { return totalImages; }
    public void setTotalImages(Long totalImages) { this.totalImages = totalImages; }

    public Long getTotalDocuments() { return totalDocuments; }
    public void setTotalDocuments(Long totalDocuments) { this.totalDocuments = totalDocuments; }

    public Double getStorageUsedMb() { return storageUsedMb; }
    public void setStorageUsedMb(Double storageUsedMb) { this.storageUsedMb = storageUsedMb; }

    public Double getStorageLimitMb() { return storageLimitMb; }
    public void setStorageLimitMb(Double storageLimitMb) { this.storageLimitMb = storageLimitMb; }

    public List<FileTypeCount> getFilesByType() { return filesByType; }
    public void setFilesByType(List<FileTypeCount> filesByType) { this.filesByType = filesByType; }

    public List<DailyUploadCount> getRecentUploads() { return recentUploads; }
    public void setRecentUploads(List<DailyUploadCount> recentUploads) { this.recentUploads = recentUploads; }

    public FileSummary getLargestFile() { return largestFile; }
    public void setLargestFile(FileSummary largestFile) { this.largestFile = largestFile; }

    public FileSummary getOldestFile() { return oldestFile; }
    public void setOldestFile(FileSummary oldestFile) { this.oldestFile = oldestFile; }

    public FileSummary getNewestFile() { return newestFile; }
    public void setNewestFile(FileSummary newestFile) { this.newestFile = newestFile; }

    // Inner classes
    public static class FileTypeCount {
        private String type;
        private Long count;

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public Long getCount() { return count; }
        public void setCount(Long count) { this.count = count; }
    }

    public static class DailyUploadCount {
        private String date;
        private Long count;

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public Long getCount() { return count; }
        public void setCount(Long count) { this.count = count; }
    }

    public static class FileSummary {
        private String name;

        @JsonProperty("size_mb")
        private Double sizeMb;

        @JsonProperty("uploaded_at")
        private String uploadedAt;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public Double getSizeMb() { return sizeMb; }
        public void setSizeMb(Double sizeMb) { this.sizeMb = sizeMb; }

        public String getUploadedAt() { return uploadedAt; }
        public void setUploadedAt(String uploadedAt) { this.uploadedAt = uploadedAt; }
    }
}