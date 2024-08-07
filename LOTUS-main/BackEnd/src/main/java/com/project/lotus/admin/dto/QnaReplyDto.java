package com.project.lotus.admin.dto;

import com.project.lotus.admin.entity.QnaReply;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class QnaReplyDto {

    @Getter @Setter
    @Builder
    public static class Request {

        // 관리자 답변 *24.01.30 jihyun
        private String reply;

        // QnaReplyForm -> QnaReplyDto로 변환 *24.02.01  jihyun
        public static QnaReplyDto.Request from(QnaReplyForm.Request qnaReplyForm) {

            return Request.builder()
                    .reply(qnaReplyForm.getReply())
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class Response {

        // Q&A 답변 인덱스 *24.01.28 jihyun
        private Long qnaReplyIdx;

        @NotBlank
        // 관리자 답변 *24.01.30 jihyun
        private String reply;

        @NotBlank
        // Q&A 답변 생성일 *24.01.30 jihyun
        private LocalDateTime postingDate;

        // Q&A *24.02.22 jihyun
        private Long qnaIdx;

        // QnaReply(Entity) -> QnaReplyDto로 변환 *24.02.01 jihyun
        public static QnaReplyDto.Response from(QnaReply qnaReply) {

            return Response.builder()
                    .qnaReplyIdx(qnaReply.getQnaReplyIdx())
                    .reply(qnaReply.getReply())
                    .postingDate(qnaReply.getPostingDate())
                    .qnaIdx(qnaReply.getQna_idx())
                    .build();
        }
    }
}
