package com.project.lotus.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


public class SigninUserForm {

    @Getter
    @Builder
    @ToString
    public static class Request {

        //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,11}$"
        //       , message = "아이디는 영어 대소문자, 숫자를 포함한 8~11자리로 입력해야 합니다.")
        @NotBlank
        private String id;

        //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*])[A-Za-z\\d@$!%*]{8,20}$"
        //        , message = "비밀번호는 영어 대소문자, 숫자, 특수문자(@$!%*)를 포함한 8~20자리로 입력해야 합니다.")
        @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
        private String pw;

    }

    @Getter
    @Builder
    @ToString
    public static class Response {

        private String token;

    }



}
