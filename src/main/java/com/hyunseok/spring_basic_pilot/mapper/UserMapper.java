package com.hyunseok.spring_basic_pilot.mapper;

import com.hyunseok.spring_basic_pilot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findByUsername(String username);
}
