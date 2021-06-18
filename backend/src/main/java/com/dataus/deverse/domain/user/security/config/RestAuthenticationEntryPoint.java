package com.dataus.deverse.domain.user.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataus.deverse.global.exception.payload.ErrorResponse;
import com.dataus.deverse.global.exception.payload.ErrorType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(
        HttpServletRequest httpServletRequest, 
        HttpServletResponse httpServletResponse, 
        AuthenticationException e
    ) throws IOException, ServletException {

        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());

        ErrorResponse error = new ErrorResponse(ErrorType.FailToAuthenticate);

        String json = new ObjectMapper().writeValueAsString(error);
        httpServletResponse.setStatus(error.getStatus());
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletResponse.getWriter().write(json);
        httpServletResponse.flushBuffer();
        
    }
    
}
