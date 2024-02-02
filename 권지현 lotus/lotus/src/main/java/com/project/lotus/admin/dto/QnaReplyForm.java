package com.project.lotus.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnaReplyForm {

    // Q&A 인덱스 *24.01.28 jihyun
    private Long qnaIdx;

    // 관리자 답변 *24.01.30 jihyun
    private String reply;

    // 생성일 *24.01.30 jihyun
    private String postingDate;
}
