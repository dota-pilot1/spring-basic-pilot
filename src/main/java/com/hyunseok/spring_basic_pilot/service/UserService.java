package com.hyunseok.spring_basic_pilot.service;

import com.hyunseok.spring_basic_pilot.domain.User;
import com.hyunseok.spring_basic_pilot.domain.UserRole;
import com.hyunseok.spring_basic_pilot.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;

    @Transactional
    public String signup(User user) {
        // 필수 값 체크
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required!");
        }

        // 아이디 중복 체크
        if (userMapper.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        // 이메일 중복 체크
        if (userMapper.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        // 비밀번호에 {noop} 접두사 추가
        if (!user.getPassword().startsWith("{noop}")) {
            user.setPassword("{noop}" + user.getPassword());
        }

        // 기본 역할 설정
        if (user.getRole() == null) {
            user.setRole(UserRole.ROLE_USER);
        }

        userMapper.insert(user);
        return user.getUsername();
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }
}
