package com.hyunseok.spring_basic_pilot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Schema(description = "이메일", example = "admin@example.com")
    private String email;
    
    @Schema(description = "비밀번호", example = "admin123")
    private String password;
}
