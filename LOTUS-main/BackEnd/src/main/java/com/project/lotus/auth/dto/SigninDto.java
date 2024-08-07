package com.project.lotus.auth.dto;

import com.project.lotus.common.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class SigninDto {

    @Schema(description = "SigninDto.Request")
    @Getter
    @Builder
    public static class Request {

        // 공통(이용자/관리자) ID *24.01.19 jihyun
        @Schema(description = "아이디", example = "testId")
        private String id;

        // 공통(이용자/관리자) PW *24.01.19 jihyun
        @Schema(description = "비밀번호", example = "testPassword")
        private String password;

        // SignForm.Request -> SignDto.Request로 변환 *24.01.24 jihyun
        public static SigninDto.Request from(SigninForm.Request signinForm) {
            return Request.builder()
                    .id(signinForm.getId())
                    .password(signinForm.getPassword())
                    .build();
        }
    }

    @Schema(description = "SigninDto.Response")
    @Getter
    @Builder
    public static class Response {

        // 공통(이용자/관리자) email *24.01.19 jihyun
        @Schema(description = "이메일", example = "test@test.com")
        private String email;

        @Schema(description = "권한", example = "ROLE_USER")
        // 공통(이용자/관리자) 권한 *24.01.19 jihyun
        private Role role;
    }
}
