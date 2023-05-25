package com.example.apiLogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Long userId;
    private String email;
    @JsonIgnore
    private String password;
    private String role;
    private String extraInfo;


}
