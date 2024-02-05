package com.project.lotus.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnaForm {

    // Q&A 인덱스 *24.01.28 jihyun
    private Long qnaIdx;

    // Q&A 제목 *24.01.28 jihyun
    @NotBlank(message = "제목은 필수로 입력해야 합니다.")
    private String title;

    // Q&A 내용 *24.01.28 jihyun
    @NotBlank(message = "내용은 필수로 입력해야 합니다.")
    private String content;

    // Q&A 이미지 *24.01.28 jihyun
    private String image;

    @NotBlank
    // 생성일 *24.01.19 jihyun
    private String postingDate;
}
