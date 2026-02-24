package com.mediastore.sdk.services;

import com.mediastore.sdk.http.HttpClient;
import com.mediastore.sdk.models.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesService {

    private final HttpClient httpClient;

    public FilesService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public File upload(java.io.File file) throws IOException {
        UploadResponse response = httpClient.uploadFile("/api/upload", file, UploadResponse.class);
        return response.getFile();
    }

    public List<File> list() throws IOException {
        ListResponse response = httpClient.get("/api/files", ListResponse.class);
        return response.getFiles();
    }

    public File get(Long id) throws IOException {
        GetResponse response = httpClient.get("/api/files/" + id, GetResponse.class);
        return response.getFile();
    }

    public void delete(Long id) throws IOException {
        httpClient.delete("/api/files/" + id, DeleteResponse.class);
    }

    public File rename(Long id, String newName) throws IOException {
        Map<String, String> body = new HashMap<>();
        body.put("original_name", newName);

        RenameResponse response = httpClient.patch("/api/file/" + id, body, RenameResponse.class);
        return response.getFile();
    }

    // Response wrapper classes
    private static class UploadResponse {
        private boolean success;
        private File file;

        public File getFile() { return file; }
        public void setFile(File file) { this.file = file; }
    }

    private static class ListResponse {
        private boolean success;
        private List<File> files;

        public List<File> getFiles() { return files; }
        public void setFiles(List<File> files) { this.files = files; }
    }

    private static class GetResponse {
        private boolean success;
        private File file;

        public File getFile() { return file; }
        public void setFile(File file) { this.file = file; }
    }

    private static class DeleteResponse {
        private boolean success;
        private String message;
    }

    private static class RenameResponse {
        private boolean success;
        private File file;

        public File getFile() { return file; }
        public void setFile(File file) { this.file = file; }
    }

}
