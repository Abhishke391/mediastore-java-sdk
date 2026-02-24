# MediaStore Java SDK

Java client library for MediaStore - a simple file storage and CDN service.

## Requirements

- Java 11 or higher
- Maven 3.6+

## Installation

### Maven

Add to your `pom.xml`:
```xml
<dependency>
    <groupId>com.mediastore</groupId>
    <artifactId>mediastore-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle
```gradle
implementation 'com.mediastore:mediastore-java-sdk:1.0.0'
```

## Quick Start
```java
import com.mediastore.sdk.MediaStoreClient;
import com.mediastore.sdk.models.File;

import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException {
        // Create client
        MediaStoreClient client = new MediaStoreClient("sk_live_your_api_key");
        
        // Upload file
        File file = client.files().upload(new java.io.File("photo.jpg"));
        
        System.out.println("Uploaded: " + file.getUrl());
    }
}
```

## Configuration

### Custom Base URL
```java
MediaStoreClient client = new MediaStoreClient(
    MediaStoreConfig.builder()
        .apiKey("sk_live_your_api_key")
        .baseUrl("https://your-domain.com")
        .build()
);
```

## Spring Boot Integration

### Configuration
```java
@Configuration
public class MediaStoreConfiguration {
    
    @Value("${mediastore.api-key}")
    private String apiKey;
    
    @Value("${mediastore.base-url:http://localhost:8080}")
    private String baseUrl;
    
    @Bean
    public MediaStoreClient mediaStoreClient() {
        return new MediaStoreClient(
            MediaStoreConfig.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .build()
        );
    }
}
```

### application.properties
```properties
mediastore.api-key=sk_live_your_api_key_here
mediastore.base-url=http://localhost:8080
```

### Usage in Service
```java
@Service
public class FileUploadService {
    
    @Autowired
    private MediaStoreClient mediaStoreClient;
    
    public String uploadUserAvatar(MultipartFile multipartFile) throws IOException {
        // Convert MultipartFile to File
        java.io.File tempFile = java.io.File.createTempFile("upload-", ".tmp");
        multipartFile.transferTo(tempFile);
        
        // Upload to MediaStore
        File uploaded = mediaStoreClient.files().upload(tempFile);
        
        // Clean up temp file
        tempFile.delete();
        
        return uploaded.getUrl();
    }
    
    public List<File> getUserFiles() throws IOException {
        return mediaStoreClient.files().list();
    }
}
```

### Controller Example
```java
@RestController
@RequestMapping("/api/files")
public class FileController {
    
    @Autowired
    private MediaStoreClient mediaStoreClient;
    
    @PostMapping("/upload")
    public ResponseEntity<File> upload(@RequestParam("file") MultipartFile file) {
        try {
            java.io.File tempFile = java.io.File.createTempFile("upload-", file.getOriginalFilename());
            file.transferTo(tempFile);
            
            File uploaded = mediaStoreClient.files().upload(tempFile);
            tempFile.delete();
            
            return ResponseEntity.ok(uploaded);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<File>> list() {
        try {
            return ResponseEntity.ok(mediaStoreClient.files().list());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            mediaStoreClient.files().delete(id);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
```

## API Reference

### Files

#### Upload
```java
File file = client.files().upload(new java.io.File("path/to/file.jpg"));
```

#### List
```java
List<File> files = client.files().list();
```

#### Get
```java
File file = client.files().get(fileId);
```

#### Rename
```java
File renamed = client.files().rename(fileId, "new-name.jpg");
```

#### Delete
```java
client.files().delete(fileId);
```

### Account

#### Get Account Info
```java
Account account = client.account().get();
```

#### Get Usage Stats
```java
UsageStats stats = client.account().stats();
```

### API Keys

#### List Keys
```java
List<APIKey> keys = client.keys().list();
```

#### Create Key
```java
APIKey key = client.keys().create("Production Key");
```

#### Rename Key
```java
client.keys().rename(keyId, "New Name");
```

#### Revoke Key
```java
client.keys().revoke(keyId);
```

## Error Handling
```java
try {
    File file = client.files().upload(new java.io.File("photo.jpg"));
} catch (UnauthorizedException e) {
    System.err.println("Invalid API key");
} catch (MediaStoreException e) {
    System.err.println("Error: " + e.getMessage() + " (Status: " + e.getStatusCode() + ")");
} catch (IOException e) {
    System.err.println("Network error: " + e.getMessage());
}
```

## Building from Source
```bash
mvn clean install
```

## Running Examples
```bash
cd examples
mvn compile exec:java -Dexec.mainClass="com.mediastore.examples.BasicExample"
```

## License

MIT