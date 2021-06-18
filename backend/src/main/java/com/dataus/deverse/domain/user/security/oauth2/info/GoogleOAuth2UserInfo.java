package com.dataus.deverse.domain.user.security.oauth2.info;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {
 
    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }
    
    @Override
    public String getId() {
        return ProviderType.google + "_" + attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getNickName() {
        return ((String) attributes.get("email")).split("@")[0];
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
    
    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

}
