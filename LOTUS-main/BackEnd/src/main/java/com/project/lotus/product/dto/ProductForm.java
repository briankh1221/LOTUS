package com.project.lotus.product.dto;

import com.project.lotus.common.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

public class ProductForm {

    @Schema(description = "중고 물품 요청", name = "ProductForm.Request")
    @Getter
    @Builder
    public static class Request {

        // 중고 물품 카테고리 *24.01.19 jihyun
        @Schema(description = "카테고리", example = "FASHION_CLOTHES")
        //  @NotNull(message = "상품카테고리는 필수로 선택해야 합니다.")
        private CategoryName categoryName;

        // 중고 물품명 *24.01.19 jihyun
        @Schema(description = "중고 물품명", example = "나이키 신발")
        @NotBlank(message = "중고 물품명은 필수로 입력해야 합니다.")
        private String productName;

        // 글 제목 *24.01.24 jihyun
        @Schema(description = "글 제목", example = "나이키 신발 포스 260 팝니다")
        @NotBlank(message = "글 제목은 필수로 입력해야 합니다.")
        private String title;

        // 중고 물품 설명 *24.01.19 jihyun
        @Schema(description = "중고 물품 설명", example = "사진만 찍어본 미개봉새상품 하얀색 나이키 신발 포스 260, 직거래 서울 송파구, 택배 가능")
        @NotBlank(message = "중고 물품 설명은 필수로 입력해야 합니다.")
        private String description;

        // 택배비 포함 여부 *24.01.19 jihyun
        @Schema(description = "택배비 포함 여부", example = "INCLUDING_DELIVERY_FEE")
        @NotNull(message = "택비비 포함 여부 필수로 선택해야 합니다.")
        private DeliveryFee deliveryFee;

        // 중고 물품 가격 *24.01.19 jihyun
        @Schema(description = "중고 물품 가격", example = "100,000")
        @NotBlank(message = "중고 물품 가격은 필수로 입력해야 합니다.")
        private String price;

        // 중고 물품 이미지 *24.01.19 jihyun
        @Schema(description = "중고 물품 이미지")
        @NotNull(message = "이미지는 필수로 첨부해야 합니다.")
        private List<MultipartFile> images;

        // 중고 물품 상태 *24.01.19 jihyun
        @Schema(description = "중고 물품 상태", example = "EXCELLENT")
        // @NotNull(message = "상품 상태는 필수로 선택해야 합니다.")
        private ProductStatus productStatus;

        // 거래 방법 *24.01.19 jihyun
        @Schema(description = "거래 방법", example = "DIRECT_DEAL")
        // @NotNull(message = "거래 방법은 필수로 선택해야 합니다.")
        private DeliveryMethod deliveryMethod;

        // 거래 가능한 주소 *24.01.19 jihyun
        @Schema(description = "거래 가능한 주소", example = "서울시 송파구")
        @NotBlank(message = "거래 가능한 주소는 필수로 입력해야 합니다.")
        private String address;

        // 거래 상태 *24.01.19 jihyun
        @Schema(description = "거래 상태", example = "ON_SALE")
        // @NotNull(message = "거래 상태는 필수로 선택해야 합니다.")
        private TransactionStatus transactionStatus;
    }

    @Schema(description = "중고 물품 응답", name = "ProductForm.Response")
    @Getter
    @Builder
    public static class Response {

        // 중고 물품 인덱스 *24.02.01 jihyun
        @Schema(description = "중고 물품 인덱스", example = "1")
        private Long productIdx;

        @Schema(description = "아이디", example = "testUserId")
        private String id;

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
        private List<MultipartFile> images;

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
    }
}
