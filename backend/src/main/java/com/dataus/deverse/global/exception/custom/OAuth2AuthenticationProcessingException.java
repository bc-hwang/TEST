package com.dataus.deverse.global.exception.custom;

import org.springframework.security.core.AuthenticationException;

import lombok.Getter;

@Getter
public class OAuth2AuthenticationProcessingException extends AuthenticationException {

    private static final long serialVersionUID = 1L;
    
    public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
    
}
