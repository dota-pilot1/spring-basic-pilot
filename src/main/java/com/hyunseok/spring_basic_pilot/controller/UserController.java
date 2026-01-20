package com.hyunseok.spring_basic_pilot.controller;

import com.hyunseok.spring_basic_pilot.domain.User;
import com.hyunseok.spring_basic_pilot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        try {
            String username = userService.signup(user);
            return "Signup successful for user: " + username;
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
