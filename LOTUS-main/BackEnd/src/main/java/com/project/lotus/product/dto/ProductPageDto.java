package com.project.lotus.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "중고 물품 페이지", name = "PageDto")
@Getter @Setter
@Builder
public class ProductPageDto {

    // 현재 중고 물품 페이지 기준 시작 페이지 값 24.02.20 jihyun
    @Schema(description = "중고 물품 시작 페이지")
    private int startPage;

    @Schema(description = "중고 물품 마지막 페이지")
    // 현재 중고 물품 페이지 기준 마지막 페이지 값 24.02.20 jihyun
    private int endPage;
}
