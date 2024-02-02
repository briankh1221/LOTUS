package com.project.lotus.product.dto;

import com.project.lotus.common.enums.DeliveryFee;
import com.project.lotus.common.enums.DeliveryMethod;
import com.project.lotus.common.enums.ProductStatus;
import com.project.lotus.common.enums.TransactionStatus;
import com.project.lotus.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;


public class ProductForm {

    @Getter
    @Builder
    public static class Request {

        // 상품 인덱스 *24.01.25 jihyun
        private Long productIdx;

        // 상품 카테고리 *24.01.19 jihyun
        @NotBlank(message = "상품카테고리는 필수로 선택해야 합니다.")
        private Long categoryIdx;

        // 상품명 *24.01.19 jihyun
        @NotBlank(message = "상품명은 필수로 입력해야 합니다.")
        private String productName;

        // 글 제목 *24.01.24 jihyun
        @NotBlank(message = "글 제목은 필수로 입력해야 합니다.")
        private String title;

        // 상품 설명 *24.01.19 jihyun
        @NotBlank(message = "상품 설명은 필수로 입력해야 합니다.")
        private String description;

        // 택배비 포함 여부 *24.01.19 jihyun
        @NotBlank(message = "택비비 포함 여부 필수로 선택해야 합니다.")
        private DeliveryFee deliveryFee;

        // 상품 가격 *24.01.19 jihyun
        @NotBlank(message = "가격은 필수로 입력해야 합니다.")
        private String price;

        // 상품 이미지 *24.01.19 jihyun
        @NotBlank(message = "이미지는 필수로 첨부해야 합니다.")
        private String image;

        // 상품 상태 *24.01.19 jihyun
        @NotBlank(message = "상품 상태는 필수로 선택해야 합니다.")
        private ProductStatus productStatus;

        // 거래 방법 *24.01.19 jihyun
        @NotBlank(message = "거래 방법은 필수로 선택해야 합니다.")
        private DeliveryMethod deliveryMethod;

        // 거래 가능한 주소 *24.01.19 jihyun
        @NotBlank(message = "거래 가능한 주소는 필수로 입력해야 합니다.")
        private String address;

        // 생성일 *24.01.19 jihyun
        private String postingDate;

        // 거래 상태 *24.01.19 jihyun
        @NotBlank(message = "거래 상태는 필수로 선택해야 합니다.")
        private TransactionStatus transactionStatus;
    }

    @Getter
    @Builder
    public static class Response {

        private Long productIdx;

        private String id;

        private Long categoryId;

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
    }
}
