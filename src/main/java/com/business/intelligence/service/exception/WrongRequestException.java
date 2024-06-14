package com.business.intelligence.service.exception;

public class WrongRequestException extends RuntimeException {

    public WrongRequestException(String message) {
        super(message);
    }

}
