package com.dataus.deverse.domain.user.security.oauth2.handler;

import static com.dataus.deverse.domain.user.security.oauth2.service.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataus.deverse.domain.user.property.AppProperties;
import com.dataus.deverse.domain.user.security.jwt.TokenProvider;
import com.dataus.deverse.domain.user.security.oauth2.service.HttpCookieOAuth2AuthorizationRequestRepository;
import com.dataus.deverse.global.exception.custom.BadRequestException;
import com.dataus.deverse.global.exception.payload.ErrorType;
import com.dataus.deverse.global.util.CookieUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private TokenProvider tokenProvider;
    private AppProperties appProperties;
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    
    @Autowired
    OAuth2AuthenticationSuccessHandler(
        TokenProvider tokenProvider, 
        AppProperties appProperties,
        HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository
    ) {
        this.tokenProvider = tokenProvider;
        this.appProperties = appProperties;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }
    
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Authentication authentication
    ) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(request, response, authentication);
        
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        
    }

    protected String determineTargetUrl(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Authentication authentication
    ) {

        Optional<String> redirectUri = 
                CookieUtils
                .getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if(!redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException(ErrorType.UnauthorizedRedirection);
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        
        String token = tokenProvider.createToken(authentication);

        return UriComponentsBuilder
                .fromUriString(targetUrl)
                .queryParam("token", token)
                .build()
                .toUriString();
    }

    protected void clearAuthenticationAttributes(
        HttpServletRequest request, 
        HttpServletResponse response
    ) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository
            .removeAuthorizationRequestCookies(request, response);
    }
    
    private boolean isAuthorizedRedirectUri(String uri) {
        
        URI clientRedirectUri = URI.create(uri);
        
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port. Let the clients use different paths if they want to
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }
    
}
