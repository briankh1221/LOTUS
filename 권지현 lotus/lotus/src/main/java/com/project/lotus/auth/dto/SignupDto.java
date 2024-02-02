package com.project.lotus.auth.dto;


import lombok.Builder;
import lombok.Getter;


public class SignupDto {

    @Getter
    @Builder
    public static class Request {

        // 가입자 ID *24.01.19 jihyun
        private String id;

        // 가입자 PW *24.01.19 jihyun
        private String password;

        // 가입자 이름 *24.01.19 jihyun
        private String name;

        // 가입자 휴대전화 *24.01.19 jihyun
        private String phone;

        // 가입자 이메일 *24.01.19 jihyun
        private String email;

        // UserSignForm.Request -> SignDto.Request로 변환 *24.01.19 jihyun
        public static SignupDto.Request from(UsersignupForm.Request usersignupForm) {
            return SignupDto.Request.builder()
                    .id(usersignupForm.getId())
                    .password(usersignupForm.getPassword())
                    .name(usersignupForm.getName())
                    .phone(usersignupForm.getPhone())
                    .email(usersignupForm.getEmail())
                    .build();
        }

        // AdminsignupForm.Request -> SignupDto.Request로 변환 *24.01.19 jihyun
        public static SignupDto.Request from(AdminsignupForm.Request adminsignupForm) {
            return SignupDto.Request.builder()
                    .id(adminsignupForm.getId())
                    .password(adminsignupForm.getPassword())
                    .name(adminsignupForm.getName())
                    .phone(adminsignupForm.getPhone())
                    .email(adminsignupForm.getEmail())
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
