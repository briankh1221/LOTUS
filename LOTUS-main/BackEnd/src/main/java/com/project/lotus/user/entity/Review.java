package com.project.lotus.user.entity;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.BaseTimeEntity;
import com.project.lotus.common.enums.Evaluation;
import com.project.lotus.product.entity.Product;
import com.project.lotus.user.dto.ReviewDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private String images;

    @Enumerated(EnumType.STRING)
    private Evaluation evaluation;

    @CreatedDate
    private LocalDateTime postingDate;

    // ReviewDto -> Review(Entity)로 변환 *24.01.26 jihyun
    public static Review from(ReviewDto.Request reviewDto, User user, Product product) {

        return Review.builder()
                .user(user)
                .product(product)
                .title(reviewDto.getTitle())
                .content(reviewDto.getContent())
                .images(reviewDto.getImages())
                .evaluation(reviewDto.getEvaluation())
                .build();
    }

    public void modifyReview(ReviewDto.Request reviewDto) {

        if (reviewDto.getTitle() != null) {
            this.title = reviewDto.getTitle();
        }
        if (reviewDto.getContent() != null) {
            this.content = reviewDto.getContent();
        }
        if (reviewDto.getImages() != null) {
            this.images = reviewDto.getImages();
        }
        if (reviewDto.getEvaluation() != null) {
            this.evaluation = reviewDto.getEvaluation();
        }
    }
}
