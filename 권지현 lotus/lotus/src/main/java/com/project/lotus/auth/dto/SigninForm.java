package com.project.lotus.auth.dto;

import com.project.lotus.common.config.security.dto.TokenDto;
import lombok.Builder;
import lombok.Getter;

public class SigninForm {

    @Getter
    public static class Request {

        // 로그인 ID *24.01.19 jihyun
        private String id;

        // 로그인 패스워드 *24.01.19 jihyun
        private String password;
    }

    @Getter
    @Builder
    public static class Response {

        // 이용자 토큰 *24.01.19 jihyun
        private TokenDto tokenDto;
    }
}
