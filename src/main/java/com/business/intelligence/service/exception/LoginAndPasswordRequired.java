package com.business.intelligence.service.exception;

public class LoginAndPasswordRequired extends RuntimeException {
    public LoginAndPasswordRequired(String message) {
        super(message);
    }
}
