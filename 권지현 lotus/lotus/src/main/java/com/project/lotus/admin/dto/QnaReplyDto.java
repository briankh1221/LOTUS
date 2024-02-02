package com.project.lotus.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class QnaReplyDto {

    // Q&A 인덱스 *24.01.28 jihyun
    private Long qnaIdx;

    @NotBlank
    // 관리자 답변 *24.01.30 jihyun
    private String reply;

    @NotBlank
    // 생성일 *24.01.30 jihyun
    private String postingDate;
}
