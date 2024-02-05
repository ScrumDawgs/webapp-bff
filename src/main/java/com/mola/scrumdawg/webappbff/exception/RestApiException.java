package com.mola.scrumdawg.webappbff.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestApiException extends RuntimeException{

    protected int statusCode;

    public RestApiException() {
    }

    public RestApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
