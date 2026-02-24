# MediaStore Java SDK

Java client library for MediaStore - a simple file storage and CDN service.

## Installation

### Maven

Add the JitPack repository:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency:
```xml
<dependency>
    <groupId>com.github.yourusername</groupId>
    <artifactId>mediastore-java-sdk</artifactId>
    <version>v1.0.0</version>
</dependency>
```

### Gradle
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.yourusername:mediastore-java-sdk:v1.0.0'
}
```

## Quick Start
```java
import com.mediastore.sdk.MediaStoreClient;
import com.mediastore.sdk.MediaStoreConfig;

public class Example {
    public static void main(String[] args) {
        // Create client
        MediaStoreClient client = new MediaStoreClient(
            MediaStoreConfig.builder()
                .apiKey("sk_live_your_api_key")
                .baseUrl("https://api.mediastore.com")
                .build()
        );
        
        // Upload file
        java.io.File file = new java.io.File("photo.jpg");
        com.mediastore.sdk.models.File uploaded = client.files().upload(file);
        
        System.out.println("Uploaded: " + uploaded.getUrl());
    }
}
```

## Features

- ✅ File upload with automatic variants
- ✅ File management (list, delete, rename)
- ✅ Account management
- ✅ API key management
- ✅ Spring Boot integration ready

## Documentation

Full documentation at: [your-docs-url]

## License

MIT License