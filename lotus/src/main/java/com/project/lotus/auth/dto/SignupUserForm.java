package com.project.lotus.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


public class SignupUserForm {

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

        //@Pattern(regexp = "^[가-힣]*$", message = "이름은 한글로 입력해야 합니다.")
        //@Size(min = 2, max = 6, message = "이름은 2~6 자리로 입력해야 합니다.")
        @NotBlank(message = "닉네임은 필수로 입력해야 합니다.")
        private String name;

        //@Size(min = 11, max = 13, message = "전화번호는 11~13 자리로 입력해야 합니다.")
        @NotBlank(message = "전화번호는 필수로 입력해야 합니다.")
        private String phone;

        //@Email(message = "이메일 형식을 확인해주세요.")
        @NotBlank(message = "이메일은 필수로 입력해야 합니다.")
        private String email;

    }

    @Getter
    @Builder
    @ToString
    public static class Response {

        private String id;
        private String name;

    }



}
