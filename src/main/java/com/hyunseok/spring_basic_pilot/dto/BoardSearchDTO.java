package com.hyunseok.spring_basic_pilot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchDTO {
    private String keyword; // 검색어 (제목 또는 내용)
    @Builder.Default
    private int page = 1; // 현재 페이지 (기본값 1)
    @Builder.Default
    private int size = 10; // 페이지당 게시글 수 (기본값 10)

    // MyBatis에서 사용할 OFFSET 계산
    public int getOffset() {
        return (page - 1) * size;
    }
}
