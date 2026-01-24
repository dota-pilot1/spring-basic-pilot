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
    
    public static LoginResponse of(String token, String email) {
        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .email(email)
                .build();
    }
}
