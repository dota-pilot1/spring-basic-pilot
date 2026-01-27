package com.hyunseok.spring_basic_pilot.service;

import com.hyunseok.spring_basic_pilot.domain.NoticeReply;
import com.hyunseok.spring_basic_pilot.mapper.NoticeReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * 답변 권한 검증 서비스
 * @PreAuthorize에서 사용할 메서드 제공
 */
@Service("noticeReplySecurityService")
@RequiredArgsConstructor
public class NoticeReplySecurityService {

    private final NoticeReplyMapper noticeReplyMapper;

    /**
     * 현재 사용자가 답변의 작성자이거나 관리자인지 확인
     * @param replyId 답변 ID
     * @param authentication 현재 인증 정보
     * @return 작성자 또는 관리자인 경우 true
     */
    public boolean isOwnerOrAdmin(Long replyId, Authentication authentication) {
        if (authentication == null) {
            return false;
        }

        // ADMIN 권한 체크
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(auth -> auth.equals("ROLE_ADMIN"));

        if (isAdmin) {
            return true;
        }

        // 작성자 체크
        NoticeReply reply = noticeReplyMapper.findById(replyId);
        if (reply == null) {
            return false;
        }

        // TODO: 실제 로그인 사용자 ID와 비교하도록 개선 필요
        // 현재는 email 기반이므로 User 조회 로직 추가 필요
        String email = authentication.getName();

        // 임시: authorId 1 (admin)은 admin@example.com, 2 (user)는 user@example.com
        // 실제로는 UserService를 주입하여 email로 User 조회 후 ID 비교
        if (email.equals("admin@example.com") && reply.getAuthorId() == 1) {
            return true;
        }
        if (email.equals("user@example.com") && reply.getAuthorId() == 2) {
            return true;
        }

        return false;
    }
}
