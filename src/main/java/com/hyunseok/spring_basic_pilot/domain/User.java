package com.hyunseok.spring_basic_pilot.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Schema(hidden = true)
    private Long id;
    
    @Schema(description = "사용자 이름", example = "hyunsok")
    private String username;
    
    @Schema(description = "이메일", example = "admin@daum.net")
    private String email;
    
    @Schema(description = "비밀번호", example = "admin123")
    private String password;
    
    @Schema(description = "권한", example = "ROLE_USER")
    private String role;
    
    @Schema(hidden = true)
    private LocalDateTime createdAt;
}
