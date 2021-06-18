package com.dataus.deverse.domain.user.controller;

import com.dataus.deverse.domain.user.mapper.OAuth2Mapper;
import com.dataus.deverse.global.principal.CurrentUser;
import com.dataus.deverse.global.principal.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OAuth2Mapper mapper;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(
        @CurrentUser UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(
            mapper.findById(userPrincipal.getName()).get()
        );
    }
    
}
