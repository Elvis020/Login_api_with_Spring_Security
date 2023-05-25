package com.example.apiLogin.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {
    @Autowired
    private MockMvc api;

    @Test
    void greeting() throws Exception {
        api.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("hello")));
    }

    @Test
    void secured() throws Exception {
        api.perform(get("/secured"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void loggedIntoSecuredEndpoint() throws Exception {
        api.perform(get("/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("User ID: 1")));
    }

    @Test
    void notLoggedAsAdminShouldNotSeeAdminEndpoint() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void simpleUserShouldNotSeeAdminEndpoint() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAdminUser
    void loggedInAsAdminShouldSeeAdminEndpoint() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("User ID: 1")));
    }
}