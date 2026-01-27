package com.hyunseok.spring_basic_pilot.service;

import com.hyunseok.spring_basic_pilot.domain.Notice;
import com.hyunseok.spring_basic_pilot.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    /**
     * 전체 공지사항 조회 (고정 공지 우선, 최신순)
     */
    public List<Notice> getAllNotices() {
        return noticeMapper.findAll();
    }

    /**
     * 공지사항 상세 조회 (조회수 증가)
     */
    @Transactional
    public Notice getNoticeById(Long id) {
        noticeMapper.incrementViewCount(id);
        return noticeMapper.findById(id);
    }

    /**
     * 공지사항 생성 (관리자만)
     */
    @Transactional
    public Notice createNotice(Notice notice) {
        if (notice.getIsPinned() == null) {
            notice.setIsPinned(false);
        }
        noticeMapper.insert(notice);
        return notice;
    }

    /**
     * 공지사항 수정 (관리자만)
     */
    @Transactional
    public Notice updateNotice(Long id, Notice notice) {
        Notice existingNotice = noticeMapper.findById(id);
        if (existingNotice == null) {
            throw new RuntimeException("공지사항을 찾을 수 없습니다.");
        }
        notice.setId(id);
        noticeMapper.update(notice);
        return noticeMapper.findById(id);
    }

    /**
     * 공지사항 삭제 (관리자만)
     */
    @Transactional
    public void deleteNotice(Long id) {
        Notice existingNotice = noticeMapper.findById(id);
        if (existingNotice == null) {
            throw new RuntimeException("공지사항을 찾을 수 없습니다.");
        }
        noticeMapper.delete(id);
    }
}
