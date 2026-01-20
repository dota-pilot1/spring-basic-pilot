package com.hyunseok.spring_basic_pilot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String authorName; // JOIN을 통해 채울 필드
    private Integer hit;
    private LocalDateTime createdAt;
}
