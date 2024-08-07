package com.project.lotus.user.dto;

import com.project.lotus.common.enums.Evaluation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewForm {

    @Schema(description = "리뷰 요청", name = "ReviewForm.Request")
    @Getter
    @Builder
    public static class Request {

        // 리뷰 제목 *24.01.26 jihyun
        @Schema(description = "리뷰 제목", example = "물건 마음에 듭니다")
        @NotBlank(message = "제목은 필수로 입력해야 합니다.")
        private String title;

        // 리뷰 내용 *24.01.26 jihyun
        @Schema(description = "리뷰 내용", example = "생각보다 물건 꺠끗합니다.")
        @NotBlank(message = "내용은 필수로 입력해야 합니다.")
        private String content;

        // 리뷰 이미지 *24.01.26 jihyun
        @Schema(description = "리뷰 이미지")
        @NotNull(message = "이미지는 필수로 첨부해야 합니다.")
        private List<MultipartFile> images;

        // 리뷰 평가 *24.01.26 jihyun
        @Schema(description = "리뷰 평가", example = "EXCELLENT")
        @NotNull(message = "평가는 필수로 선택해야 합니다.")
        private Evaluation evaluation;
    }

    @Schema(description = "리뷰 응답", name = "ReviewForm.Response")
    @Builder
    @Getter
    public static class Response {

        // 리뷰 인덱스 *24.02.01 jihyun
        @Schema(description = "리뷰 인덱스", example = "1")
        private Long reviewIdx;

        @Schema(description = "리뷰 제목", example = "물건 마음에 듭니다")
        private String title;

        @Schema(description = "리뷰 내용", example = "생각보다 물건 꺠끗합니다.")
        private String content;

        @Schema(description = "리뷰 이미지")
        private List<MultipartFile> images;

        @Schema(description = "리뷰 평가", example = "EXCELLENT")
        private Evaluation evaluation;

        // 리뷰 생성일 *24.02.01 jihyun
        @Schema(description = "생성일", example = "5초 전")
        private LocalDateTime postingDate;
    }
}
