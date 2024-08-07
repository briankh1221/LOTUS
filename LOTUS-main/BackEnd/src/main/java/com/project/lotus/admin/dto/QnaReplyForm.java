package com.project.lotus.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnaReplyForm {

    @Schema(description = "관리자 Q&A 답변 요청", name = "QnaReplyForm.Request")
    @Getter
    @Builder
    public static class Request {

        // 관리자 Q&A 답변 *24.01.30 jihyun
        @Schema(description = "관리자 Q&A 답변", example = "testQ&AReply")
        @NotBlank(message = "답변은 필수로 입력해야 합니다.")
        private String reply;
    }

    @Schema(description = "관리자 Q&A 답변 응답", name = "QnaReplyForm.Response")
    @Getter
    @Builder
    public static class Response {

        // Q&A 답변 인덱스 *24.01.28 jihyun
        @Schema(description = "관리자 Q&A 답변 인덱스", example = "1")
        private Long qnaReplyIdx;

        @Schema(description = "관리자 Q&A 답변", example = "testQ&AReply")
        private String reply;

        // Q&A 답변 생성일 *24.01.30 jihyun
        @Schema(description = "관리자 Q&A 답변 생성일", example = "testQ&AReplyPostingDate")
        private String postingDate;
    }
}
