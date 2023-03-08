package com.joey.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Joey
 * token过期
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtInvalidException extends RuntimeException{
    public JwtInvalidException(String message) {
        super(message);
    }
}
