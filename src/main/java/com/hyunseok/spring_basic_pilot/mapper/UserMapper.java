package com.hyunseok.spring_basic_pilot.mapper;

import com.hyunseok.spring_basic_pilot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void insert(User user);

    void deleteById(Long id);
}
