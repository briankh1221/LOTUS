package com.project.lotus.auth.dto;

import com.project.lotus.common.config.security.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class SigninDto {

    @Getter
    @Builder
    @ToString
    public static class Request {
        private String id;
        private String pw;

        public static SigninDto.Request from(SigninUserForm.Request signinUserForm) {
            return Request.builder()
                    .id(signinUserForm.getId())
                    .pw(signinUserForm.getPw())
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class Response {

        private Long idx;
        private String id;
        private Role role;

    }




}
