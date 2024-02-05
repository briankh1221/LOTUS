package com.project.lotus.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Controller @PathVariable TypeMismatch *24.01.26 jihyun
    METHOD_ARGUMENT_TYPE_MISMATCH("메서드 매개변수의 타입이 맞지 않습니다.", BAD_REQUEST),

    // Auth *24.01.18 jihyun
    ID_ALREADY_EXISTS("이미 존재하는 아이디입니다.", BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("이미 존재하는 이메일입니다.", BAD_REQUEST),
    ADMIN_CODE_NOT_MATCH("관리자 인증코드가 일치하지 않습니다.", BAD_REQUEST),
    ID_NOT_EXISTS("존재하지 않는 아이디입니다.", BAD_REQUEST),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", BAD_REQUEST),

    // Product *24.01.24 jihyun
    PRODUCT_NOT_EXISTS("존재하지 않는 상품입니다.", BAD_REQUEST),

    // Category *24.01.24 jihyun
    PRODUCTCATEGORY_NOT_EXISTS("존재하지 않는 상품 카테고리입니다.", BAD_REQUEST),

    // Review *24.01.27 jihyun
    REVIEW_NOT_EXISTS("존재하지 않는 리뷰입니다.", BAD_REQUEST),

    // User *24.0.1.25 jihyun
    USER_NOT_EXISTS("존재하지 않는 이용자입니다.", BAD_REQUEST),
    QNA_NOT_EXISTS("존재하지 않는 질문입니다.", BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;
}
