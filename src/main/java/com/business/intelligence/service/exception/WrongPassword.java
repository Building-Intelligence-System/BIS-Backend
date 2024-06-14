package com.business.intelligence.service.exception;

public class WrongPassword extends RuntimeException{

    public WrongPassword(String message) {
        super(message);
    }

}
