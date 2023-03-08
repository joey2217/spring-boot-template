package com.joey.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Joey
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailException extends RuntimeException {
    private Object loginRequest;

    public LoginFailException(String message, Object loginRequest) {
        super(message);
        this.loginRequest = loginRequest;
    }

    public LoginFailException(String message, Object loginRequest, Throwable cause) {
        super(message, cause);
        this.loginRequest = loginRequest;
    }
}