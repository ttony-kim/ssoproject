package com.cos.security1.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CustomKeycloakAuthenticationProcessingFilter extends KeycloakAuthenticationProcessingFilter {
    
    public CustomKeycloakAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public CustomKeycloakAuthenticationProcessingFilter(AuthenticationManager authenticationManager, RequestMatcher requiresAuthenticationRequestMatcher) {
        super(authenticationManager, requiresAuthenticationRequestMatcher);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
    	System.out.println("test!!~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // Line of importance down here
        if (authResult instanceof KeycloakAuthenticationToken) {
            super.successfulAuthentication(request, response, chain, authResult);
            return;
        }
        // whatever spring-boot-keycloak does copy paste here
    }
}
