package com.dataus.deverse.domain.user.vo;

import java.util.Date;

import com.dataus.deverse.domain.user.security.oauth2.info.ProviderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserVO {

    private int userNo;

    @JsonIgnore
    private String userId;
    
    @JsonProperty("provider")
    private ProviderType oauthProvider;
    
    @JsonProperty("name")
    private String userNm;
    
    @JsonProperty("nickname")
    private String userNick;
    
    @JsonProperty("email")
    private String userEmail;
    
    @JsonProperty("picture")
    private String userAvatarUrl;
    
    @JsonProperty(value="registDate")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date userRegistDt;
    
    @JsonIgnore
    private String delYn;
    @JsonIgnore
    private Date instDt;
    @JsonIgnore
    private String instId;
    @JsonIgnore
    private Date updtDt;
    @JsonIgnore
    private String updtId;
    
}
