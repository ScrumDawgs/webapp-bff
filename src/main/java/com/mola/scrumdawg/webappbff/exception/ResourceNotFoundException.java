package com.mola.scrumdawg.webappbff.exception;

public class ResourceNotFoundException extends RestApiException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message, int statusCode) {
        super(message, statusCode);
    }
}
