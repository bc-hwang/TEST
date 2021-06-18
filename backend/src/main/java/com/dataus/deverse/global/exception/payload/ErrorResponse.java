package com.dataus.deverse.global.exception.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ErrorResponse {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date timestamp;
    private boolean success;
    private int status;
    private String statusName;
    private String errorCode;
    private String errorName;
    private String message;

    public ErrorResponse(ErrorType error) {
        this.timestamp = new Date();
        this.success = false;
        this.status = error.getStatus().value();
        this.statusName = error.getStatus().getReasonPhrase();
        this.errorCode = error.getCode();
        this.errorName = error.toString();
        this.message = error.getMessage();
    }

}
