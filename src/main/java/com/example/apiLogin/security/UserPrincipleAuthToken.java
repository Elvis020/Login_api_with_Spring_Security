package com.example.apiLogin.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class UserPrincipleAuthToken extends AbstractAuthenticationToken {
    private final UserPrincipal userPrincipal;
    public UserPrincipleAuthToken(UserPrincipal principal) {
        super(principal.getAuthorities());
        this.userPrincipal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return userPrincipal;
    }
}
