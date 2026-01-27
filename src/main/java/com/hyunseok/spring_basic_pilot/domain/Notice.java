package com.hyunseok.spring_basic_pilot.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notice {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "자동 생성되는 ID")
    private Long id;
    private String title;
    private String content;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "작성자 ID (자동 설정)")
    private Long authorId;
    private Boolean isPinned;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "조회수 (자동 증가)")
    private Integer viewCount;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "생성 시간")
    private LocalDateTime createdAt;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "수정 시간")
    private LocalDateTime updatedAt;
}
