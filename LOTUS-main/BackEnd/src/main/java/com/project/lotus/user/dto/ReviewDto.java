package com.project.lotus.user.dto;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.enums.Evaluation;
import com.project.lotus.product.entity.Product;
import com.project.lotus.user.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReviewDto {

    @Schema(description = "리뷰 요청", name = "ReviewDto.Request")
    @Getter @Setter
    @Builder
    public static class Request {

        // 리뷰 제목 *24.01.26 jihyun
        @Schema(description = "리뷰 제목", example = "물건 마음에 듭니다")
        private String title;

        // 리뷰 내용 *24.01.26 jihyun
        @Schema(description = "리뷰 내용", example = "생각보다 물건 꺠끗합니다.")
        private String content;

        // 리뷰 이미지 *24.01.26 jihyun
        @Schema(description = "리뷰 이미지")
        private String images;

        // 리뷰 평가 *24.01.26 jihyun
        @Schema(description = "리뷰 평가", example = "EXCELLENT")
        private Evaluation evaluation;

        // ReviewForm -> ReviewDto로 변환 *24.01.26 jihyun
        public static ReviewDto.Request from(ReviewForm.Request reviewForm) {

            return Request.builder()
                    .title(reviewForm.getTitle())
                    .content(reviewForm.getContent())
                    .images(reviewForm.getImages().toString())
                    .evaluation(reviewForm.getEvaluation())
                    .build();
        }
    }

    @Schema(description = "리뷰 요청", name = "ReviewDto.Response")
    @Getter @Setter
    @Builder
    public static class Response {

        // 리뷰 인덱스 *24.02.01 jihyun
        @Schema(description = "리뷰 인덱스", example = "1")
        private Long reviewIdx;

        @Schema(description = "리뷰 제목", example = "물건 마음에 듭니다")
        private String title;

        @Schema(description = "리뷰 내용", example = "생각보다 물건 꺠끗합니다.")
        private String content;

        @Schema(description = "리뷰 이미지")
        private String images;

        @Schema(description = "리뷰 평가", example = "EXCELLENT")
        private Evaluation evaluation;

        // 리뷰 생성일 *24.02.01 jihyun
        @Schema(description = "생성일", example = "5초 전")
        private LocalDateTime postingDate;

        @Schema(description = "중고 물품")
        private Product product;

        @Schema(description = "리뷰 작성자")
        private User user;

        // Review(Entity) -> ReviewDto로 변환 *24.01.26 jihyun
        public static ReviewDto.Response from(Review review) {

            return Response.builder()
                    .reviewIdx(review.getReviewIdx())
                    .title(review.getTitle())
                    .content(review.getContent())
                    .images(review.getImages())
                    .evaluation(review.getEvaluation())
                    .postingDate(review.getPostingDate())
                    .product(review.getProduct())
                    .user(review.getUser())
                    .build();
        }
    }
}
