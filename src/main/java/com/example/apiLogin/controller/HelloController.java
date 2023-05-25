package com.example.apiLogin.controller;

import com.example.apiLogin.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String greeting(){
        return "Hello world";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal){
        return "If you see this than you are logged in as "+principal.getEmail()+" User ID: "+principal.getUserId();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal){
        return "If you see this then you are logged in as ADMIN, with user ID: "+principal.getUserId();
    }
}
