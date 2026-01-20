package com.hyunseok.spring_basic_pilot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().toString();

        return String.format(
                "<h1>Spring Boot Basic Pilot Project</h1>" +
                        "<p>Welcome, <b>%s</b>!</p>" +
                        "<p>Your role: %s</p>" +
                        "<hr>" +
                        "<p><a href='/logout'>Logout</a></p>",
                username, role);
    }
}
