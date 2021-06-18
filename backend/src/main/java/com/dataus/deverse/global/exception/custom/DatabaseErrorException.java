package com.dataus.deverse.global.exception.custom;

import com.dataus.deverse.global.exception.payload.ErrorType;

import lombok.Getter;

@Getter
public class DatabaseErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorType error;

    public DatabaseErrorException(ErrorType error) {
        super();
        this.error = error;
    }

    public DatabaseErrorException(String message) {
        super(message);
    }
    
    public DatabaseErrorException(String message, Throwable cause) {
        super(message, cause);
    }   
    
}
