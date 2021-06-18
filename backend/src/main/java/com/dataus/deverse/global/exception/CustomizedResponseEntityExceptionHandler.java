package com.dataus.deverse.global.exception;

import com.dataus.deverse.global.exception.custom.BadRequestException;
import com.dataus.deverse.global.exception.custom.DatabaseErrorException;
import com.dataus.deverse.global.exception.custom.ResourceNotFoundException;
import com.dataus.deverse.global.exception.payload.ErrorResponse;
import com.dataus.deverse.global.exception.payload.ErrorType;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        ErrorType error = ErrorType.fromValue(
            ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage()
        );
        
        return new ResponseEntity<>(
            new ErrorResponse(error), 
            error.getStatus()
        );

    }

    @ExceptionHandler(DatabaseErrorException.class)
    protected ResponseEntity<Object> handleDatabaseError(DatabaseErrorException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(ex.getError()),
            ex.getError().getStatus()
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(ex.getError()),
            ex.getError().getStatus()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(ex.getError()),
            ex.getError().getStatus()
        );
    }
    
}
