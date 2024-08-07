package com.project.lotus.auth.dto;

import com.project.lotus.common.config.security.dto.TokenDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class SigninForm {

    @Schema(description = "이용자, 관리자 로그인 요청", name = "SigninForm.Request")
    @Getter
    public static class Request {

        @Schema(description = "아이디", example = "testUserId")
        // 로그인 ID *24.01.19 jihyun
        private String id;

        @Schema(description = "비밀번호", example = "testUserPassword")
        // 로그인 패스워드 *24.01.19 jihyun
        private String password;
    }

    @Schema(description = "이용자, 관리자 로그인 응답", name = "SigninForm.Response")
    @Getter
    @Builder
    public static class Response {

        // 이용자 토큰 *24.01.19 jihyun
        @Schema(description = "토큰")
        private TokenDto tokenDto;
    }
}
