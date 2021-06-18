package com.dataus.deverse.global.exception.custom;

import com.dataus.deverse.global.exception.payload.ErrorType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorType error;

    public BadRequestException(ErrorType error) {
        super();
        this.error = error;
    }

    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }   
    
}
