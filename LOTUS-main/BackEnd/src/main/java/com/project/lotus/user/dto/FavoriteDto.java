package com.project.lotus.user.dto;

import com.project.lotus.product.entity.Product;
import com.project.lotus.user.entity.Favorite;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class FavoriteDto {

    @Schema(description = "찜 요청", name = "FavoriteDto.Request")
    @Getter
    @Builder
    public static class Request {

        @Schema(description = "중고 물품 인덱스", example = "1")
        private Long productIdx;

        @Schema(description = "중고 물품명", example = "나이키 신발")
        private String productName;

    }

    @Schema(description = "찜 요청", name = "FavoriteDto.Response")
    @Getter
    @Builder
    public static class Response {

        // 찜 인덱스 *24.02.01 jihyun
        @Schema(description = "찜 인덱스", example = "1")
        private Long favoriteIdx;

        @Schema(description = "중고 물품")
        private Product product;

        // Favorite(Entity) -> FavoriteDto.Response로 변환 *24.01.24 jihyun
        public static FavoriteDto.Response from (Favorite favorite) {
            return Response.builder()
                    .favoriteIdx(favorite.getFavoriteIdx())
                    .product(favorite.getProduct())
                    .build();
        }
    }
}
