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
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            String username = userService.signup(user);
            return ResponseEntity.ok().body(new SignupResponse("success", "회원가입 성공: " + username));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new SignupResponse("error", e.getMessage()));
        }
    }

    // 내부 클래스: 회원가입 응답
    record SignupResponse(String status, String message) {}

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

        // 3. 사용자 정보 조회 (role 포함)
        User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 4. JWT 토큰 생성 (ROLE_ 접두사 제거)
        String roleWithoutPrefix = user.getRole().name().replace("ROLE_", "");
        String jwt = jwtTokenProvider.generateToken(
                loginRequest.getEmail(),
                roleWithoutPrefix
        );

        // 5. 토큰 + role 리턴
        return ResponseEntity.ok(LoginResponse.of(jwt, loginRequest.getEmail(), roleWithoutPrefix));
    }
}
