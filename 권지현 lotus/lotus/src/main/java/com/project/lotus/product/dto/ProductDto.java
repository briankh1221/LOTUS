package com.project.lotus.product.dto;

import com.project.lotus.common.enums.DeliveryFee;
import com.project.lotus.common.enums.DeliveryMethod;
import com.project.lotus.common.enums.ProductStatus;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDto {

    @Getter
    @Setter
    @Builder
    public static class Request {

        // 상품 인덱스 *24.01.25 jihyun
        private Long productIdx;

        // 상품 카테고리 *24.01.19 jihyun
        private Long categoryIdx;

        // 상품명 *24.01.19 jihyun
        private String productName;

        // 글 제목 *24.01.24 jihyun
        private String title;

        // 상품 설명 *24.01.19 jihyun
        private String description;

        // 택배비 포함 여부 *24.01.19 jihyun
        private DeliveryFee deliveryFee;

        // 상품 가격 *24.01.19 jihyun
        private String price;

        // 상품 이미지 *24.01.19 jihyun
        private String image;

        // 상품 상태 *24.01.19 jihyun
        private ProductStatus productStatus;

        // 거래 방법 *24.01.19 jihyun
        private DeliveryMethod deliveryMethod;

        // 거래 가능한 주소 *24.01.19 jihyun
        private String address;

        // 생성일 *24.01.19 jihyun
        private String postingDate;

        // 거래 상태 *24.01.19 jihyun
        private TransactionStatus transactionStatus;

        // ProductForm -> ProductDto로 변환 *24.01.26 jihyun
        public static ProductDto.Request from(ProductForm.Request productForm) {

            return Request.builder()
                    .productIdx(productForm.getProductIdx())
                    .categoryIdx(productForm.getCategoryIdx())
                    .productName(productForm.getProductName())
                    .title(productForm.getTitle())
                    .description(productForm.getDescription())
                    .deliveryFee(productForm.getDeliveryFee())
                    .price(productForm.getPrice())
                    .image(productForm.getImage())
                    .productStatus(productForm.getProductStatus())
                    .deliveryMethod(productForm.getDeliveryMethod())
                    .address(productForm.getAddress())
                    .postingDate(productForm.getPostingDate().toString())
                    .build();
        }
    }

    // ProductForm -> ProductDto.Request로 변환 *24.01.24 jihyun
    @Builder
    @Getter
    public static class Response {

        // 상품 인덱스 *24.01.24 jihyun
        private Long productIdx;

        private Long categoryIdx;

        private String productName;

        private String title;

        private String description;

        private DeliveryFee deliveryFee;

        private String price;

        private String image;

        private ProductStatus productStatus;

        private DeliveryMethod deliveryMethod;

        private String address;

        private String postingDate;

        private TransactionStatus transactionStatus;

        // ProductForm -> ProductDto.Response로 변환 *24.01.24 jihyun
        public static ProductDto.Response from(ProductForm.Response productForm) {

            return Response.builder()
                    .productIdx(productForm.getProductIdx())
                    .categoryIdx(productForm.getCategoryId())
                    .productName(productForm.getProductName())
                    .title(productForm.getTitle())
                    .description(productForm.getDescription())
                    .deliveryFee(productForm.getDeliveryFee())
                    .price(productForm.getPrice())
                    .image(productForm.getImage())
                    .productStatus(productForm.getProductStatus())
                    .deliveryMethod(productForm.getDeliveryMethod())
                    .address(productForm.getAddress())
                    .postingDate(productForm.getPostingDate())
                    .transactionStatus(productForm.getTransactionStatus())
                    .build();
        }

        // Product(Entity) -> ProductDto.Response로 변환 *24.01.24 jihyun
        public static ProductDto.Response from(Product product) {

            return Response.builder()
                    .productIdx(product.getProductIdx())
                    .categoryIdx(product.getCategoryIdx())
                    .productName(product.getProductName())
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .deliveryFee(product.getDeliveryFee())
                    .price(product.getPrice())
                    .image(product.getImage())
                    .productStatus(product.getProductStatus())
                    .deliveryMethod(product.getDeliveryMethod())
                    .address(product.getAddress())
                    .postingDate(product.getPostingDate().toString())
                    .transactionStatus(product.getTransactionStatus())
                    .build();
        }
    }
}
