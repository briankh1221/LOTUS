package com.project.lotus.product.dto;

import com.project.lotus.auth.entity.User;
import com.project.lotus.common.enums.*;
import com.project.lotus.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

public class ProductDto {

    @Schema(description = "중고 물품 요청", name = "ProductDto.Request")
    @Getter @Setter
    @Builder
    public static class Request {

        // 중고 물품 카테고리 *24.01.19 jihyun
        @Schema(description = "카테고리", example = "FASHION_CLOTHES")
        private CategoryName categoryName;

        // 중고 물품명 *24.01.19 jihyun
        @Schema(description = "중고 물품명", example = "나이키 신발")
        private String productName;

        // 글 제목 *24.01.24 jihyun
        @Schema(description = "글 제목", example = "나이키 신발 포스 260 팝니다")
        private String title;

        // 중고 물품 설명 *24.01.19 jihyun
        @Schema(description = "중고 물품 설명", example = "사진만 찍어본 미개봉새상품 하얀색 나이키 신발 포스 260, 직거래 서울 송파구, 택배 가능")
        private String description;

        // 택배비 포함 여부 *24.01.19 jihyun
        @Schema(description = "택배비 포함 여부", example = "INCLUDING_DELIVERY_FEE")
        private DeliveryFee deliveryFee;

        // 중고 물품 가격 *24.01.19 jihyun
        @Schema(description = "중고 물품 가격", example = "100,000")
        private String price;

        // 중고 물품 이미지 *24.01.19 jihyun
        @Schema(description = "중고 물품 이미지")
        private String images;

        // 중고 물품 상태 *24.01.19 jihyun
        @Schema(description = "중고 물품 상태", example = "EXCELLENT")
        private ProductStatus productStatus;

        // 거래 방법 *24.01.19 jihyun
        @Schema(description = "거래 방법", example = "DIRECT_DEAL")
        private DeliveryMethod deliveryMethod;

        // 거래 가능한 주소 *24.01.19 jihyun
        @Schema(description = "거래 가능한 주소", example = "서울시 송파구")
        private String address;

        // 거래 상태 *24.01.19 jihyun
        @Schema(description = "거래 상태", example = "ON_SALE")
        private TransactionStatus transactionStatus;

        // ProductForm -> ProductDto로 변환 *24.01.26 jihyun
        public static ProductDto.Request from(ProductForm.Request productForm) {

            return Request.builder()
                    .categoryName(productForm.getCategoryName())
                    .productName(productForm.getProductName())
                    .title(productForm.getTitle())
                    .description(productForm.getDescription())
                    .deliveryFee(productForm.getDeliveryFee())
                    .price(productForm.getPrice())
                    .images(productForm.getImages().toString())
                    .productStatus(productForm.getProductStatus())
                    .deliveryMethod(productForm.getDeliveryMethod())
                    .address(productForm.getAddress())
                    .transactionStatus(productForm.getTransactionStatus())
                    .build();
        }
    }

    @Schema(description = "중고 물품 요청", name = "ProductDto.Response")
    @Getter
    @Builder
    public static class Response {

        // 중고 물품 인덱스 *24.01.24 jihyun
        @Schema(description = "중고 물품 인덱스", example = "1")
        private Long productIdx;

        @Schema(description = "User")
        private User user;

        @Schema(description = "카테고리", example = "FASHION_CLOTHES")
        private CategoryName categoryName;

        @Schema(description = "중고 물품명", example = "나이키 신발")
        private String productName;

        @Schema(description = "글 제목", example = "나이키 신발 포스 260 팝니다")
        private String title;

        @Schema(description = "중고 물품 설명", example = "사진만 찍어본 미개봉새상품 하얀색 나이키 신발 포스 260, 직거래 서울 송파구, 택배 가능")
        private String description;

        @Schema(description = "택배비 포함 여부", example = "INCLUDING_DELIVERY_FEE")
        private DeliveryFee deliveryFee;

        @Schema(description = "중고 물품 가격", example = "100,000")
        private String price;

        @Schema(description = "중고 물품 이미지")
        private String images;

        @Schema(description = "중고 물품 상태", example = "EXCELLENT")
        private ProductStatus productStatus;

        @Schema(description = "거래 방법", example = "DIRECT_DEAL")
        private DeliveryMethod deliveryMethod;

        @Schema(description = "거래 가능한 주소", example = "서울시 송파구")
        private String address;

        @Schema(description = "거래 상태", example = "ON_SALE")
        private TransactionStatus transactionStatus;

        // 중고 물품 등록 생성일 *24.02.01 jihyun
        @Schema(description = "생성일", example = "5초 전")
        private LocalDateTime postingDate;

        @Schema(description = "buyer")
        private User buyer;

        // Product(Entity) -> ProductDto.Response로 변환 *24.01.24 jihyun
        public static ProductDto.Response from(Product product) {

            return Response.builder()
                    .productIdx(product.getProductIdx())
                    .user(product.getUser())
                    .categoryName(product.getCategoryName())
                    .productName(product.getProductName())
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .deliveryFee(product.getDeliveryFee())
                    .price(product.getPrice())
                    .images(product.getImages())
                    .productStatus(product.getProductStatus())
                    .deliveryMethod(product.getDeliveryMethod())
                    .address(product.getAddress())
                    .postingDate(product.getPostingDate())
                    .transactionStatus(product.getTransactionStatus())
                    .buyer(product.getBuyer())
                    .build();
        }
    }
}
