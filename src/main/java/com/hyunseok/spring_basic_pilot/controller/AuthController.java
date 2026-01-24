package com.hyunseok.spring_basic_pilot.controller;

import com.hyunseok.spring_basic_pilot.domain.User;
import com.hyunseok.spring_basic_pilot.dto.LoginRequest;
import com.hyunseok.spring_basic_pilot.dto.LoginResponse;
import com.hyunseok.spring_basic_pilot.security.JwtTokenProvider;
import com.hyunseok.spring_basic_pilot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 1. email/password 기반 AuthenticationToken 생성
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // 2. 인증 성공 시 SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. JWT 토큰 생성 (email을 subject로 사용)
        String jwt = jwtTokenProvider.generateToken(loginRequest.getEmail());

        // 4. 토큰 리턴
        return ResponseEntity.ok(LoginResponse.of(jwt, loginRequest.getEmail()));
    }
}
