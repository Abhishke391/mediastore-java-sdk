package com.mediastore.sdk.exceptions;

public class MediaStoreException extends RuntimeException{

    private final int statusCode;

    public MediaStoreException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public MediaStoreException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
