package com.dataus.deverse.global.principal;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.dataus.deverse.domain.user.vo.UserVO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class UserPrincipal implements OAuth2User {

    private int userNo;
    private String userId;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public static UserPrincipal create(UserVO user, Map<String, Object> attributes) {
        
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        
        return UserPrincipal.builder()
                    .userNo(user.getUserNo())
                    .userId(user.getUserId())
                    .authorities(authorities)
                    .attributes(attributes)
                    .build();

    }  

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.userId;
    }

    public String getUserId() {
        return this.userId;
    }
       
    
}
