package com.hyunseok.spring_basic_pilot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String type;
    private String email;
    private String role;
    
    public static LoginResponse of(String token, String email, String role) {
        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .email(email)
                .role(role)
                .build();
    }
}
