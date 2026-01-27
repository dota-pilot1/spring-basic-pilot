package com.hyunseok.spring_basic_pilot.controller;

import com.hyunseok.spring_basic_pilot.domain.User;
import com.hyunseok.spring_basic_pilot.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin", description = "관리자 API")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final UserMapper userMapper;

    @Operation(summary = "전체 사용자 조회", description = "ADMIN만 접근 가능")
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userMapper.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "사용자 삭제", description = "ADMIN만 접근 가능")
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userMapper.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
