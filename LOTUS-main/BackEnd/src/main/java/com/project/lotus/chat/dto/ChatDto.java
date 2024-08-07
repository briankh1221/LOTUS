package com.project.lotus.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ChatDto {

    // 채팅 메시지 *24.02.07 jihyun
    private String message;

    // 구매자 이메일 *24.02.07 jihyun
    private String buyerEmail;

    // 상품 인덱스 *24.02.07 jihyun
    private Long productIdx;

    // 구매자 이메일 확인 여부 (False이면 판매자 이메일임) *24.02.07 jihyun
    private Boolean isBuyerEmail;

}
