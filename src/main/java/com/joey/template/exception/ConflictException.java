package com.joey.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Joey
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
