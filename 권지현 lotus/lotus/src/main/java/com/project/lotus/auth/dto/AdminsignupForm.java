package com.project.lotus.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


public class AdminsignupForm {

    @Getter
    @Builder
    @ToString
    public static class Request {

        //    @Size(min = 5, max = 10, message = "아이디는 5~10 자리로 입력해야 합니다.")
        @NotBlank(message = "아이디는 필수로 입력해야 합니다.")
        private String id;

        //    @Size(min = 6, max = 6, message = "인증코드는 숫자 6자리입니다.")
        // adminCode 사용할 지 보류 중 *24.01.24 jihyun
        @NotBlank(message = "관리자 인증코드는 필수로 입력해야 합니다.")
        private String adminCode;

        //    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$])[A-Za-z\\d~!@#$]{8,11}$"
//            , message = "비밀번호는 영어 대소문자, 숫자, 특수문자(~!@#$)를 포함한 8~11 자리로 입력해야 합니다.")
        @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
        private String password;

        //    @Pattern(regexp = "^[가-힣]*$", message = "이름은 한글로 입력해야 합니다.")
//    @Size(min = 2, max = 6, message = "이름은 2~6 자리로 입력해야 합니다.")
        @NotBlank(message = "이름은 필수로 입력해야 합니다.")
        private String name;

        //    @Size(min = 11, max = 13, message = "전화번호는 11~13 자리로 입력해야 합니다.")
        @NotBlank(message = "전화번호는 필수로 입력해야 합니다.")
        private String phone;

        //    @Email(message = "이메일 형식을 확인해주세요.")
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
