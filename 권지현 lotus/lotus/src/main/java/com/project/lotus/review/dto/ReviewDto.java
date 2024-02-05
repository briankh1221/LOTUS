package com.project.lotus.review.dto;

import com.project.lotus.common.enums.Evaluation;
import com.project.lotus.product.dto.ProductForm;
import com.project.lotus.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReviewDto {

    // 리뷰 인덱스 *24.01.26 jihyun
    private Long reviewIdx;

    // 상품 인덱스 *24.01.26 jihyun
    private Long productIdx;

    // 리뷰 제목 *24.01.26 jihyun
    private String title;

    // 리뷰 내용 *24.01.26 jihyun
    private String content;

    // 리뷰 이미지 *24.01.26 jihyun
    private String image;

    // 리뷰 평가 *24.01.26 jihyun
    private Evaluation evaluation;

    // ReviewForm -> ReviewDto로 변환 *24.01.26 jihyun
    public static ReviewDto from(ReviewForm reviewForm) {

        return ReviewDto.builder()
                .reviewIdx(reviewForm.getReviewIdx())
                .title(reviewForm.getTitle())
                .content(reviewForm.getContent())
                .image(reviewForm.getImage())
                .evaluation(reviewForm.getEvaluation())
                .build();
    }
    // Review(Entity) -> ReviewDto로 변환 *24.01.26 jihyun
    public static ReviewDto from(Review review) {

        return ReviewDto.builder()
                .reviewIdx(review.getReviewIdx())
                .title(review.getTitle())
                .content(review.getContent())
                .image(review.getImage())
                .evaluation(review.getEvaluation())
                .build();
    }
}
