package com.hyunseok.spring_basic_pilot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    // 1. 단순 텍스트 반환 (로그인 없이 접근 가능하도록 설정할 예정)
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, Spring Boot Pilot!";
    }

    // 2. 파라미터를 받는 JSON 반환
    @GetMapping("/api/greet")
    public Map<String, String> greet(@RequestParam(defaultValue = "Guest") String name) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to Pilot Project");
        response.put("user", name);
        response.put("status", "success");
        return response;
    }
}
