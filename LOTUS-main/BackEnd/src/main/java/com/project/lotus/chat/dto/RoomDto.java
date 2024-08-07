package com.project.lotus.chat.dto;

import com.project.lotus.auth.entity.User;
import com.project.lotus.chat.entity.Room;
import com.project.lotus.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RoomDto {

    @Getter @Setter
    @Builder
    public static class Request {

        // 이용자 이메일 *24.02.07 jihyun
        private String buyerEmail;

        // 상품 인덱스 *24.02.07 jihyun
        private Long productIdx;

    }

    @Getter @Setter
    @Builder
    public static class Response {

        // 채팅방 번호 *24.02.07 jihyun
        private Long roomIdx;

        // 구매자 *24.02.07 jihyun
        private User buyer;

        // 상품 *24.02.07 jihyun
        private Product product;

        // Room (Entity) -> RoomDto로 변환 *24.02.07 jihyun
        public static RoomDto.Response from(Room room) {

            return Response.builder()
                    .roomIdx(room.getRoomIdx())
                    .buyer(room.getBuyer())
                    .product(room.getProduct())
                    .build();
        }
    }
}
