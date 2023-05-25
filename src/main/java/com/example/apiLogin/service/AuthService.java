package com.example.apiLogin.service;

import com.example.apiLogin.model.LoginResponse;
import com.example.apiLogin.security.JwtIssuer;
import com.example.apiLogin.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(String loginEmail, String loginPassword) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginEmail, loginPassword)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        var roles = principal
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
        return LoginResponse
                .builder()
                .accessToken(token)
                .build();

    }
}
