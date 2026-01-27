package com.hyunseok.spring_basic_pilot.mapper;

import com.hyunseok.spring_basic_pilot.domain.NoticeReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeReplyMapper {
    List<NoticeReply> findByNoticeId(Long noticeId);
    NoticeReply findById(Long id);
    void insert(NoticeReply noticeReply);
    void update(NoticeReply noticeReply);
    void delete(Long id);
}
