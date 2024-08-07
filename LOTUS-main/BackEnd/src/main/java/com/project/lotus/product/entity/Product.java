package com.project.lotus.product.entity;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.BaseTimeEntity;
import com.project.lotus.common.enums.*;
import com.project.lotus.product.dto.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseTimeEntity  {

    @Schema(description = "중고 물품 인덱스", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productIdx;

    @Schema(description = "이용자 인덱스", example = "1")
    @NotNull
    @ManyToOne
    @JoinColumn(name ="user_idx")
    private User user;

    @Schema(description = "카테고리", example = "FASHION_CLOTHES")
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @Schema(description = "중고 물품명", example = "나이키 신발")
    @NotNull
    private String productName;

    @Schema(description = "글 제목", example = "나이키 신발 포스 260 팝니다")
    @NotBlank
    private String title;

    @Schema(description = "중고 물품 설명", example = "사진만 찍어본 미개봉새상품 하얀색 나이키 신발 포스 260, 직거래 서울 송파구, 택배 가능")
    @NotNull
    private String description;

    @Schema(description = "택배비 포함 여부", example = "INCLUDING_DELIVERY_FEE")
    // @NotNull
    @Enumerated(EnumType.STRING)
    private DeliveryFee deliveryFee;

    @Schema(description = "중고 물품 가격", example = "100,000")
    @NotNull
    private String price;

    @Schema(description = "중고 물품 이미지")
    @NotNull
    private String images;

    @Schema(description = "중고 물품 상태", example = "EXCELLENT")
    // @NotNull
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Schema(description = "거래 방법", example = "DIRECT_DEAL")
    // @NotNull
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Schema(description = "거래 가능한 주소", example = "서울시 송파구")
    @NotNull
    private String address;

    @Schema(description = "거래 상태", example = "ON_SALE")
    // @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Schema(description = "생성일", example = "5초 전")
    @CreatedDate
    private LocalDateTime postingDate;

    @Schema(description = "buyer_idx")
    @ManyToOne
    @JoinColumn(name ="buyer_idx")
    private User buyer;

    // ProductDto.Request -> Product(Entity)로 변환 *24.01.26 jihyun
    public static Product from(ProductDto.Request productDto, User user) {

        return Product.builder()
                .user(user)
                .categoryName(productDto.getCategoryName())
                .productName(productDto.getProductName())
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .deliveryFee(productDto.getDeliveryFee())
                .price(productDto.getPrice())
                .images(productDto.getImages())
                .productStatus(productDto.getProductStatus())
                .deliveryMethod(productDto.getDeliveryMethod())
                .address(productDto.getAddress())
                .transactionStatus(productDto.getTransactionStatus())
                .build();
    }

    public void modifyProduct(ProductDto.Request productDto) {

        if (productDto.getCategoryName() != null) {
            this.categoryName = productDto.getCategoryName();
        }
        if  (productDto.getProductName() != null) {
            this.productName = productDto.getProductName();
        }
        if (productDto.getTitle() != null) {
            this.title = productDto.getTitle();
        }
        if (productDto.getDescription() != null) {
            this.description = productDto.getDescription();
        }
        if (productDto.getDeliveryFee() != null) {
            this.deliveryFee = productDto.getDeliveryFee();
        }
        if (productDto.getPrice() != null) {
            this.price = productDto.getPrice();
        }
        if (productDto.getImages() != null) {
            this.images = productDto.getImages();
        }
        if (productDto.getProductStatus() != null) {
            this.productStatus = productDto.getProductStatus();
        }
        if (productDto.getDeliveryMethod() != null) {
            this.deliveryMethod = productDto.getDeliveryMethod();
        }
        if (productDto.getAddress() != null) {
            this.address = productDto.getAddress();
        }
        if (productDto.getTransactionStatus() != null) {
            this.transactionStatus = productDto.getTransactionStatus();
        }
    }
}
