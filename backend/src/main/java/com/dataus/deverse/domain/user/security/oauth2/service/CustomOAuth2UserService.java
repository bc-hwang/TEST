package com.dataus.deverse.domain.user.security.oauth2.service;

import java.util.Optional;

import com.dataus.deverse.domain.user.mapper.OAuth2Mapper;
import com.dataus.deverse.domain.user.security.oauth2.info.OAuth2UserInfo;
import com.dataus.deverse.domain.user.security.oauth2.info.OAuth2UserInfoFactory;
import com.dataus.deverse.domain.user.security.oauth2.info.ProviderType;
import com.dataus.deverse.domain.user.vo.UserVO;
import com.dataus.deverse.global.exception.custom.OAuth2AuthenticationProcessingException;
import com.dataus.deverse.global.principal.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private OAuth2Mapper oAuth2Mapper;

    public OAuth2User loadUserById(String userId) throws UsernameNotFoundException {

        UserVO user = oAuth2Mapper.findById(userId)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserPrincipal.create(user, null);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }

    }

    private OAuth2User processOAuth2User(
        OAuth2UserRequest oAuth2UserRequest, 
        OAuth2User oAuth2User
    ) {
        
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
            oAuth2UserRequest.getClientRegistration().getRegistrationId(), 
            oAuth2User.getAttributes()
        );
        
        Optional<UserVO> userOptional = oAuth2Mapper.findById(oAuth2UserInfo.getId());
        
        UserVO user;

        if(userOptional.isPresent()) {
            user = userOptional.get();            

            if(!user.getOauthProvider()
                    .equals(
                        ProviderType.valueOf(
                            oAuth2UserRequest
                                .getClientRegistration()
                                .getRegistrationId()
                        )
                    )
                ) {
                throw new OAuth2AuthenticationProcessingException(
                    "Looks like you're signed up with " +
                    user.getOauthProvider() + 
                    " account. Please use your " + 
                    user.getOauthProvider() +
                    " account to login.");
            }
            
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());

    }

    private UserVO registerNewUser(
        OAuth2UserRequest oAuth2UserRequest, 
        OAuth2UserInfo oAuth2UserInfo
    ) {
        
        UserVO user = UserVO.builder()
                        .oauthProvider(ProviderType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                        .userId(oAuth2UserInfo.getId())
                        .userNm(oAuth2UserInfo.getName())
                        .userNick(oAuth2UserInfo.getNickName())
                        .userEmail(oAuth2UserInfo.getEmail())
                        .userAvatarUrl(oAuth2UserInfo.getImageUrl())
                        .instId(oAuth2UserInfo.getId())
                        .build();

        oAuth2Mapper.addUser(user);

        return user;

    }

    private UserVO updateExistingUser(UserVO existingUser, OAuth2UserInfo oAuth2UserInfo) {

        existingUser.setUserNm(oAuth2UserInfo.getName());
        existingUser.setUserAvatarUrl(oAuth2UserInfo.getImageUrl());

        oAuth2Mapper.modifyUser(existingUser);

        return existingUser;
    }   

}
