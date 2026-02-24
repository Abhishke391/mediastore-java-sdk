package com.mediastore.examples;

import com.mediastore.sdk.MediaStoreClient;
import com.mediastore.sdk.MediaStoreConfig;
import com.mediastore.sdk.models.APIKey;
import com.mediastore.sdk.models.Account;
import com.mediastore.sdk.models.File;
import com.mediastore.sdk.models.UsageStats;

import java.io.IOException;
import java.util.List;

public class BasicExample {
    public static void main(String[] args) {
        // Initialize client
        MediaStoreClient client = new MediaStoreClient(
                MediaStoreConfig.builder()
                        .apiKey("sk_live_d78b543972b251b9027a6a2dcaa278fc")
                        .baseUrl("http://localhost:8081")
                        .build()
        );

        try {
            // Example 1: Upload a file
            System.out.println("=== Uploading file ===");
            java.io.File fileToUpload = new java.io.File("test.txt");

            if (!fileToUpload.exists()) {
                System.err.println("Error: management.jpeg not found.");
                return;
            }

            File uploaded = client.files().upload(fileToUpload);
            System.out.println("Uploaded: " + uploaded.getOriginalName() + " (ID: " + uploaded.getId() + ")");
            System.out.println("URL: " + uploaded.getUrl());

            if (uploaded.getVariants() != null && !uploaded.getVariants().isEmpty()) {
                System.out.println("Variants:");
                uploaded.getVariants().forEach((name, url) ->
                        System.out.println("  " + name + ": " + url)
                );
            }

            // Example 2: List all files
            System.out.println("\n=== Listing files ===");
            List<File> files = client.files().list();
            System.out.println("Total files: " + files.size());
            for (File f : files) {
                System.out.println("- " + f.getOriginalName() + " (" + f.getSizeMb() + " MB)");
            }

            // Example 3: Get account info
            System.out.println("\n=== Account info ===");
            Account account = client.account().get();
            System.out.println("Email: " + account.getEmail());
            System.out.printf("Storage: %.2f / %.2f MB (%.1f%%)\n",
                    account.getStorageUsedMb(),
                    account.getStorageLimitMb(),
                    account.getStoragePercentage());

            // Example 4: Get usage stats
            System.out.println("\n=== Usage statistics ===");
            UsageStats stats = client.account().stats();
            System.out.println("Total files: " + stats.getTotalFiles());
            System.out.println("Images: " + stats.getTotalImages() +
                    ", Documents: " + stats.getTotalDocuments());

            if (stats.getLargestFile() != null) {
                System.out.println("Largest file: " + stats.getLargestFile().getName() +
                        " (" + stats.getLargestFile().getSizeMb() + " MB)");
            }

            // Example 5: List API keys
            System.out.println("\n=== API Keys ===");
            List<APIKey> keys = client.keys().list();
            System.out.println("Total API keys: " + keys.size());
            for (APIKey key : keys) {
                System.out.println("- " + key.getName() + " (Active: " + key.getIsActive() + ")");
            }

            // Example 6: Rename a file
            System.out.println("\n=== Renaming file ===");
            File renamed = client.files().rename(uploaded.getId(), "my-awesome-photo.txt");
            System.out.println("Renamed to: " + renamed.getOriginalName());

            // Example 7: Delete a file
            System.out.println("\n=== Deleting file ===");
            client.files().delete(uploaded.getId());
            System.out.println("File deleted successfully!");

            System.out.println("\n✓ All examples completed successfully!");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}