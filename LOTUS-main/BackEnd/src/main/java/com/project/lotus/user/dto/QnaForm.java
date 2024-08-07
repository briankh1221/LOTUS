package com.project.lotus.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class QnaForm {

    @Schema(description = "Q&A 질문 요청", name = "QnaForm.Request")
    @Getter
    @Builder
    public static class Request {

        // Q&A 제목 *24.01.28 jihyun
        @Schema(description = "Q&A 제목", example = "관리자님 질문 사항 있습니다!")
        @NotBlank(message = "제목은 필수로 입력해야 합니다.")
        private String title;

        // Q&A 내용 *24.01.28 jihyun
        @Schema(description = "Q&A 내용", example = "리뷰 상품 수정 가능한가요?")
        @NotBlank(message = "내용은 필수로 입력해야 합니다.")
        private String content;

        // Q&A 이미지 *24.01.28 jihyun
        @Schema(description = "이미지")
        // @NotNull(message = "이미지는 필수로 첨부해야 합니다.")
        private List<MultipartFile> images;
    }

    @Schema(description = "Q&A 질문 응답", name = "QnaForm.Response")
    @Getter
    @Builder
    public static class Response {

        // Q&A 인덱스 *24.01.28 jihyun
        @Schema(description = "Q&A 인덱스", example = "1")
        private Long qnaIdx;

        @Schema(description = "Q&A 제목", example = "관리자님 질문 사항 있습니다!")
        private String title;

        @Schema(description = "Q&A 내용", example = "리뷰 상품 수정 가능한가요?")
        private String content;

        @Schema(description = "이미지")
        private List<MultipartFile> images;

        @Schema(description = "생성일", example = "5초 전")
        // Q&A 생성일 *24.01.19 jihyun
        private LocalDateTime postingDate;
    }
}
