package com.securitydemo.config;

import com.securitydemo.model.LoggedInUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class KeycloakHttpServletPrinciple {

    private final HttpServletRequest httpServletRequest;

    public KeycloakHttpServletPrinciple(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public LoggedInUserInfo getLoggedInUserInfo() {
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) this.httpServletRequest.getUserPrincipal();

        AccessToken token = principal.getAccount().getKeycloakSecurityContext().getToken();

        Set<String> roles = token.getRealmAccess().getRoles();

        return LoggedInUserInfo.builder()
                .id(token.getSubject())
                .firstName(token.getGivenName())
                .lastName(token.getFamilyName())
                .email(token.getEmail())
                .username(token.getPreferredUsername())
                .roles(roles)
                .build();
    }
}