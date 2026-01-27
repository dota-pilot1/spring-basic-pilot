package com.hyunseok.spring_basic_pilot.controller;

import com.hyunseok.spring_basic_pilot.domain.Notice;
import com.hyunseok.spring_basic_pilot.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 전체 공지사항 조회 (모든 사용자 접근 가능)
     */
    @GetMapping
    public ResponseEntity<List<Notice>> getAllNotices() {
        return ResponseEntity.ok(noticeService.getAllNotices());
    }

    /**
     * 공지사항 상세 조회 (모든 사용자 접근 가능)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    /**
     * 공지사항 생성 (관리자만)
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Notice> createNotice(
            @RequestBody Notice notice,
            Authentication authentication
    ) {
        // 현재 로그인한 관리자의 ID를 authorId로 설정
        // 추후 User 객체에서 ID를 가져오도록 개선 가능
        notice.setAuthorId(1L); // TODO: 실제 로그인 사용자 ID로 변경
        return ResponseEntity.ok(noticeService.createNotice(notice));
    }

    /**
     * 공지사항 수정 (관리자만)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Notice> updateNotice(
            @PathVariable Long id,
            @RequestBody Notice notice
    ) {
        return ResponseEntity.ok(noticeService.updateNotice(id, notice));
    }

    /**
     * 공지사항 삭제 (관리자만)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok().build();
    }
}
