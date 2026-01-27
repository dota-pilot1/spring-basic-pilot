package com.hyunseok.spring_basic_pilot.mapper;

import com.hyunseok.spring_basic_pilot.domain.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<Notice> findAll();
    Notice findById(Long id);
    void insert(Notice notice);
    void update(Notice notice);
    void delete(Long id);
    void incrementViewCount(Long id);
}
