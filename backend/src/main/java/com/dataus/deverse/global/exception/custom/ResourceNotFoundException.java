package com.dataus.deverse.global.exception.custom;

import com.dataus.deverse.global.exception.payload.ErrorType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorType error;

    public ResourceNotFoundException(ErrorType error) {
        super();
        this.error = error;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
