package com.project.lotus.review.dto;

import com.project.lotus.common.enums.Evaluation;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewForm {

    // 리뷰 인덱스 *24.01.26 jihyun
    private Long reviewIdx;

    // 상품 인덱스 *24.01.27 jihyun
    private Long productIdx;

    // 리뷰 제목 *24.01.26 jihyun
    @NotBlank(message = "제목은 필수로 입력해야 합니다.")
    private String title;

    // 리뷰 내용 *24.01.26 jihyun
    @NotBlank(message = "내용은 필수로 입력해야 합니다.")
    private String content;

    // 리뷰 이미지 *24.01.26 jihyun
    private String image;

    // 리뷰 평가 *24.01.26 jihyun
    @NotBlank(message = "평가는 필수로 선택해야 합니다.")
    private Evaluation evaluation;
}
