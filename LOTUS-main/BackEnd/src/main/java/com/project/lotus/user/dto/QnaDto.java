package com.project.lotus.user.dto;

import com.project.lotus.admin.entity.QnaReply;
import com.project.lotus.auth.entity.User;
import com.project.lotus.user.entity.Qna;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class QnaDto {

    @Schema(description = "Q&A 질문 요청", name = "QnaDto.Request")
    @Getter @Setter
    @Builder
    public static class Request {

        // Q&A 제목 *24.01.28 jihyun
        @Schema(description = "Q&A 제목", example = "관리자님 질문 사항 있습니다!")
        private String title;

        // Q&A 내용 *24.01.28 jihyun
        @Schema(description = "Q&A 내용", example = "리뷰 상품 수정 가능한가요?")
        private String content;

        // Q&A 이미지 *24.01.28 jihyun
        @Schema(description = "이미지")
        private String images;

        // QnaForm -> QnaDto로 변환 *24.01.28 jihyun
        public static QnaDto.Request from(QnaForm.Request qnaForm) {

            return Request.builder()
                    .title(qnaForm.getTitle())
                    .content(qnaForm.getContent())
                    .images(qnaForm.getImages().toString())
                    .build();
        }
    }

    @Schema(description = "Q&A 질문 응답", name = "QnaDto.Response")
    @Getter @Setter
    @Builder
    public static class Response {

        // Q&A 인덱스 *24.02.01 jihyun
        @Schema(description = "Q&A 인덱스", example = "1")
        private Long qnaIdx;

        @Schema(description = "Q&A 제목", example = "관리자님 질문 사항 있습니다!")
        private String title;

        @Schema(description = "Q&A 내용", example = "리뷰 상품 수정 가능한가요?")
        private String content;

        @Schema(description = "이미지")
        private String images;

        @Schema(description = "생성일", example = "5초 전")
        // 생성일 *24.02.01 jihyun
        private LocalDateTime postingDate;

        @Schema(description = "답변")
        private QnaReply qnaReply;

        @Schema(description = "질문 작성자")
        private User user;

        // Qna(Entity) -> QnaDto로 변환 *24.01.28 jihyun
        public static QnaDto.Response from(Qna qna) {

            return Response.builder()
                    .qnaIdx(qna.getQnaIdx())
                    .title(qna.getTitle())
                    .content(qna.getContent())
                    .images(qna.getImages())
                    .postingDate(qna.getPostingDate())
                    .qnaReply(qna.getQnaReply())
                    .user(qna.getUser())
                    .build();
        }
    }
}