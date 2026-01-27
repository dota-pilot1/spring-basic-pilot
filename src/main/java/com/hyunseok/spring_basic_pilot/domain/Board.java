package com.hyunseok.spring_basic_pilot.domain;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "자동 생성되는 ID")
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private String authorName; // JOIN을 통해 채울 필드
    private Integer hit;
    private LocalDateTime createdAt;
}
