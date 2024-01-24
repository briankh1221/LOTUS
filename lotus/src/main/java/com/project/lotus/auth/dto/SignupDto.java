package com.project.lotus.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupDto {

    @Getter
    @Builder
    public static class Request {
        private String id;
        private String pw;
        private String name;
        private String phone;
        private String email;

        public static SignupDto.Request from(SignupUserForm.Request signupUserForm) {
            return Request.builder()
                    .id(signupUserForm.getId())
                    .pw(signupUserForm.getPw())
                    .name(signupUserForm.getName())
                    .phone(signupUserForm.getPhone())
                    .email(signupUserForm.getEmail())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Response {
        private String id;
        private String name;
    }




}
