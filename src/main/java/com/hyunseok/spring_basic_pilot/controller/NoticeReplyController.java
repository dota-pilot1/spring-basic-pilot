package com.hyunseok.spring_basic_pilot.controller;

import com.hyunseok.spring_basic_pilot.domain.NoticeReply;
import com.hyunseok.spring_basic_pilot.mapper.NoticeReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices/{noticeId}/replies")
@RequiredArgsConstructor
public class NoticeReplyController {

    private final NoticeReplyMapper noticeReplyMapper;

    /**
     * 특정 공지사항의 답변 목록 조회 (모든 사용자 접근 가능)
     */
    @GetMapping
    public ResponseEntity<List<NoticeReply>> getRepliesByNoticeId(@PathVariable Long noticeId) {
        return ResponseEntity.ok(noticeReplyMapper.findByNoticeId(noticeId));
    }

    /**
     * 답변 생성 (로그인한 모든 사용자 가능)
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<NoticeReply> createReply(
            @PathVariable Long noticeId,
            @RequestBody NoticeReply noticeReply,
            Authentication authentication
    ) {
        noticeReply.setNoticeId(noticeId);
        // TODO: 실제 로그인 사용자 ID로 변경
        noticeReply.setAuthorId(1L); // 임시값

        noticeReplyMapper.insert(noticeReply);
        return ResponseEntity.ok(noticeReply);
    }

    /**
     * 답변 수정 (작성자 본인 또는 관리자만)
     */
    @PutMapping("/{replyId}")
    @PreAuthorize("@noticeReplySecurityService.isOwnerOrAdmin(#replyId, authentication)")
    public ResponseEntity<NoticeReply> updateReply(
            @PathVariable Long noticeId,
            @PathVariable Long replyId,
            @RequestBody NoticeReply noticeReply
    ) {
        noticeReply.setId(replyId);
        noticeReply.setNoticeId(noticeId);
        noticeReplyMapper.update(noticeReply);
        return ResponseEntity.ok(noticeReplyMapper.findById(replyId));
    }

    /**
     * 답변 삭제 (작성자 본인 또는 관리자만)
     */
    @DeleteMapping("/{replyId}")
    @PreAuthorize("@noticeReplySecurityService.isOwnerOrAdmin(#replyId, authentication)")
    public ResponseEntity<Void> deleteReply(
            @PathVariable Long noticeId,
            @PathVariable Long replyId
    ) {
        noticeReplyMapper.delete(replyId);
        return ResponseEntity.ok().build();
    }
}
