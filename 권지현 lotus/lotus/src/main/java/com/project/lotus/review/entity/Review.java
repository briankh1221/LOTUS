package com.project.lotus.review.entity;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.BaseTimeEntity;
import com.project.lotus.common.enums.Evaluation;
import com.project.lotus.product.entity.Product;
import com.project.lotus.review.dto.ReviewDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewIdx;

    @NotNull
    @ManyToOne
    @JoinColumn(name ="user_idx")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name ="product_idx")
    private Product product;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String image;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Evaluation evaluation;

    // ReviewDto -> Review(Entity)로 변환 *24.01.26 jihyun
    public static Review from(ReviewDto reviewDto, User user, Product product) {

        return Review.builder()
                .user(user)
                .product(product)
                .title(reviewDto.getTitle())
                .content(reviewDto.getContent())
                .image(reviewDto.getImage())
                .evaluation(reviewDto.getEvaluation())
                .build();
    }

    public void modifyReview(ReviewDto reviewDto) {

        if (reviewDto.getTitle() != null) {
            this.reviewIdx = reviewDto.getReviewIdx();
        }
        if (reviewDto.getContent() != null) {
            this.content = reviewDto.getContent();
        }
        if (reviewDto.getEvaluation() != null) {
            this.evaluation = reviewDto.getEvaluation();
        }
    }
}
