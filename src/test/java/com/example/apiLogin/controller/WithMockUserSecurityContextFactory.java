package com.example.apiLogin.controller;

import com.example.apiLogin.entity.User;
import com.example.apiLogin.security.UserPrincipal;
import com.example.apiLogin.security.UserPrincipleAuthToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockUser annotation) {
        var myAuthorities = Arrays.stream(annotation.authorities())
                .map(SimpleGrantedAuthority::new)
                .toList();
        var principal = UserPrincipal
                .builder()
                .userId(annotation.userId())
                .email("fake@gmail.com")
                .authorities(myAuthorities)
                .build();

        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UserPrincipleAuthToken(principal));
        return context;
    }
}
