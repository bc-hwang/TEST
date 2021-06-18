package com.dataus.deverse.domain.user.security.oauth2.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProviderType {

    github,
    google,
    facebook;
    
}
