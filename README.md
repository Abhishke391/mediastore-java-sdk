## Installation

[![](https://jitpack.io/#Abhishke391/mediastore-java-sdk/v1.0.0)](https://jitpack.io/#Abhishke391/mediastore-java-sdk/v1.0.0)

### Maven

Add the JitPack repository to your `pom.xml`:
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
	    <groupId>com.github.Abhishke391</groupId>
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
    implementation 'com.github.Abhishke391:mediastore-java-sdk:v1.0.0'
}
```

## Quick Start
```java
import com.mediastore.sdk.MediaStoreClient;
import com.mediastore.sdk.MediaStoreConfig;

public class Example {
    public static void main(String[] args) throws Exception {
        // Initialize client
        MediaStoreClient client = new MediaStoreClient(
            MediaStoreConfig.builder()
                .apiKey("sk_live_your_api_key")
                .baseUrl("http://localhost:8080")  // Change when deployed
                .build()
        );
        
        // Upload a file
        java.io.File file = new java.io.File("photo.jpg");
        com.mediastore.sdk.models.File uploaded = client.files().upload(file);
        
        System.out.println("✓ Uploaded: " + uploaded.getUrl());
        System.out.println("✓ Variants: " + uploaded.getVariants());
    }
}
```

## Features

✅ **File Operations**
- Upload files (with automatic image variants)
- List files
- Delete files
- Rename files

✅ **Image Processing**
- Automatic thumbnail generation
- Multiple size variants (thumbnail, medium, large)
- Parallel processing with Go backend

✅ **Account Management**
- Get account info
- View storage usage
- Usage statistics

✅ **API Key Management**
- List API keys
- Generate new keys
- Revoke keys

✅ **Spring Boot Ready**
- Easy integration
- Configuration via properties
- Dependency injection support
```

---

## **Step 5: Share Your Work!**

Now anyone can use your SDK by:

1. Adding JitPack repository
2. Adding your dependency
3. Running `mvn install`

**Share the link:**
```
https://github.com/yourusername/mediastore-java-sdk
