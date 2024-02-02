package com.project.lotus.favorite.dto;


import com.project.lotus.favorite.entity.Favorite;
import lombok.Builder;
import lombok.Getter;

public class FavoriteDto {

    @Builder
    @Getter
    public static class Request {

        // 이용자 ID *24.01.24 jihyun
        private String id;

        // 상품 인덱스 *24.01.24 jihyun
        private Long productIdx;

        // 상품명 *24.01.24 jihyun
        private String productName;
    }

    @Builder
    @Getter
    public static class Response {

        // 찜 인덱스 *24.01.24 jihyun
        private Long favoriteIdx;

        private Long userIdx;

        private Long productIdx;

        private String productName;

        // Favorite(Entity) -> FavoriteDto.Response로 변환 *24.01.24 jihyun
        public static FavoriteDto.Response from (Favorite favorite) {
            return Response.builder()
                    .favoriteIdx(favorite.getIdx())
                    .userIdx(favorite.getUser().getIdx())
                    .productIdx(favorite.getProduct().getProductIdx())
                    .productName(favorite.getProduct().getProductName())
                    .build();
        }
    }
}
