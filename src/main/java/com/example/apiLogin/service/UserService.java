package com.example.apiLogin.service;

import com.example.apiLogin.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final String EXISTING_EMAIL = "test@test.com";
    private static final String ANOTHER_EMAIL = "user@test.com";

    public Optional<User> findByEmail(String email) {
        if (EXISTING_EMAIL.equalsIgnoreCase(email)) {
            var user = new User();
            user.setUserId(1L);
            user.setEmail(EXISTING_EMAIL);
            user.setPassword("$2a$12$OncyrnWdhK4S2wqbzsw.gujYLk7EU2mr8XRx7t9u4nvRbeJfHL1JC"); // admin
            user.setRole("ROLE_ADMIN");
            user.setExtraInfo("My nice Admin");
            return Optional.of(user);
        }
        else if (ANOTHER_EMAIL.equalsIgnoreCase(email)) {
            var user = new User();
            user.setUserId(99L);
            user.setEmail(EXISTING_EMAIL);
            user.setPassword("$2a$12$OncyrnWdhK4S2wqbzsw.gujYLk7EU2mr8XRx7t9u4nvRbeJfHL1JC"); // user
            user.setRole("ROLE_USER");
            user.setExtraInfo("My nice user ");
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
