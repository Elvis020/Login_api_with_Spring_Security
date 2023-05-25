package com.example.apiLogin.controller;

import com.example.apiLogin.model.LoginRequest;
import com.example.apiLogin.model.LoginResponse;
import com.example.apiLogin.security.JwtIssuer;
import com.example.apiLogin.security.UserPrincipal;
import com.example.apiLogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return authService.attemptLogin(loginRequest.getEmail(), loginRequest.getPassword());
    }


}
