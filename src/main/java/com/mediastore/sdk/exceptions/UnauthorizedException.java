package com.mediastore.sdk.exceptions;

public class UnauthorizedException extends MediaStoreException{
    public UnauthorizedException(String message) {
        super(message, 401);
    }
}
