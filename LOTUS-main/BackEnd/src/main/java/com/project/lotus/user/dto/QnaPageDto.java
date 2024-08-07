package com.project.lotus.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Q&A 페이지", name = "PageDto")
@Getter @Setter
@Builder
public class QnaPageDto {

    // 현재 Q&A 페이지 기준 시작 페이지 값 24.02.20 jihyun
    @Schema(description = "Q&A 페이지")
    private int startPage;

    // 현재 Q&A 페이지 기준 마지막 페이지 값 24.02.20 jihyun
    @Schema(description = "리뷰 마지막 페이지")
    private int endPage;
}
