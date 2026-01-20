package com.hyunseok.spring_basic_pilot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> list; // 데이터 목록
    private long totalCount; // 전체 데이터 수
    private int page; // 현재 페이지
    private int size; // 페이지당 개수

    // 전체 페이지 수 계산
    public int getTotalPage() {
        return (int) Math.ceil((double) totalCount / size);
    }
}
