package com.project.lotus.auth.dto;

import com.project.lotus.auth.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class SignupDto {

    @Schema(description = "이용자, 관리자 회원가입 요청", name = "SignupDto.Request")
    @Getter @Setter
    @Builder
    public static class Request {

        // 가입자 ID *24.01.19 jihyun
        @Schema(description = "아이디", example = "testUserId")
        private String id;

        // 가입자 PW *24.01.19 jihyun
        @Schema(description = "비밀번호", example = "testUserPassword")
        private String password;

        // 가입자 이름 *24.01.19 jihyun
        @Schema(description = "이용자 이름", example = "testUserName")
        private String name;

        // 가입자 휴대전화 *24.01.19 jihyun
        @Schema(description = "이용자 전화번호", example = "01012345678")
        private String phone;

        // 가입자 이메일 *24.01.19 jihyun
        @Schema(description = "이용자 이메일", example = "testUser@test.com")
        private String email;

        // 가입자 프로필 사진 (정보 수정시만 업데이트 가능함) *24.02.03 jihyun
        @Schema(description = "이용자 프로필 이미지", example = "")
        private String image;

        // UserSignForm.Request -> SignDto.Request로 변환 *24.01.19 jihyun
        public static SignupDto.Request from (UsersignupForm.Request usersignupForm) {

            return Request.builder()
                    .id(usersignupForm.getId())
                    .password(usersignupForm.getPassword())
                    .name(usersignupForm.getName())
                    .phone(usersignupForm.getPhone())
                    .email(usersignupForm.getEmail())
                    .image(usersignupForm.getImage().toString())
                    .build();
        }

        // AdminsignupForm.Request -> SignupDto.Request로 변환 *24.01.19 jihyun
        public static SignupDto.Request from (AdminsignupForm.Request adminsignupForm) {

            return Request.builder()
                    .id(adminsignupForm.getId())
                    .password(adminsignupForm.getPassword())
                    .name(adminsignupForm.getName())
                    .phone(adminsignupForm.getPhone())
                    .email(adminsignupForm.getEmail())
                    .image(adminsignupForm.getImage().toString())
                    .build();
        }
    }

    @Schema(description = "이용자, 관리자 회원가입 응답", name = "SignupDto.Response")
    @Getter
    @Builder
    public static class Response {

        @Schema(description = "이용자, 관리자 인덱스", example = "1")
        private Long idx;

        @Schema(description = "아이디", example = "testUserId")
        private String id;

        @Schema(description = "비밀번호", example = "testUserPassword")
        private String password;

        @Schema(description = "이용자 이름", example = "testUserName")
        private String name;

        @Schema(description = "이용자 전화번호", example = "01012345678")
        private String phone;

        @Schema(description = "이용자 이메일", example = "testUser@test.com")
        private String email;

        @Schema(description = "이용자 프로필 이미지", example = "")
        private String image;

        public static SignupDto.Response from(User user) {

            return Response.builder()
                    .idx(user.getIdx())
                    .id(user.getId())
                    .password(user.getPassword())
                    .name(user.getName())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .image(user.getImage())
                    .build();
        }
    }
}
