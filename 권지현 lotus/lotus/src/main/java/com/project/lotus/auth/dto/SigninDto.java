package com.project.lotus.auth.dto;

import com.project.lotus.common.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class SigninDto {

    @Getter
    @Builder
    @ToString
    public static class Request {

        // 공통(이용자/관리자) ID *24.01.19 jihyun
        private String id;

        // 공통(이용자/관리자) PW *24.01.19 jihyun
        private String password;

        // SignForm.Request -> SignDto.Request로 변환 *24.01.24 jihyun
        public static SigninDto.Request from(SigninForm.Request signinForm) {
            return Request.builder()
                    .id(signinForm.getId())
                    .password(signinForm.getPassword())
                    .build();
        }
    }
    @Getter
    @Builder
    @ToString
    public static class Response {

        // 공통(이용자/관리자) email *24.01.19 jihyun
        private String email;

        // 공통(이용자/관리자) 권한 *24.01.19 jihyun
        private Role role;
    }
}
