package com.project.lotus.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class AdminsignupForm {

    @Schema(description = "관리자 회원가입 요청", name="AdminsignupForm.Request")
    @Getter @Setter
    @Builder
    public static class Request {

        @Schema(description = "관리자 아이디", example = "testAdminId")
        @Size(min = 5, max = 10, message = "아이디는 5~10 자리로 입력해야 합니다.")
        @NotBlank(message = "아이디는 필수로 입력해야 합니다.")
        private String id;

        @Schema(description = "관리자 인증 코드", example = "579138")
        @Size(min = 6, max = 6, message = "인증코드는 숫자 6자리입니다.")
        @NotBlank(message = "관리자 인증코드는 필수로 입력해야 합니다.")
        private String adminCode;

        @Schema(description = "관리자 비밀번호", example = "testAdminPassword")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$./])[A-Za-z\\d~!@#$./]{8,}$"
                , message = "비밀번호는 영어 대소문자, 숫자, 특수문자(~!@#$)를 포함한 8 자리이상으로 입력해야 합니다.")
        @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
        private String password;

        @Schema(description = "관리자 이름", example = "testAdminName")
        @Pattern(regexp = "^[가-힣]*$", message = "이름은 한글로 입력해야 합니다.")
        @Size(min = 2, max = 6, message = "이름은 2~6 자리로 입력해야 합니다.")
        @NotBlank(message = "이름은 필수로 입력해야 합니다.")
        private String name;

        @Schema(description = "관리자 전화번호", example = "01023456789")
        @Size(min = 11, max = 13, message = "전화번호는 11~13 자리로 입력해야 합니다.")
        @NotBlank(message = "전화번호는 필수로 입력해야 합니다.")
        private String phone;

        @Schema(description = "관리자 이메일", example = "testAdmin@test.com")
        @Email(message = "이메일 형식을 확인해주세요.")
        @NotBlank(message = "이메일은 필수로 입력해야 합니다.")
        private String email;

        // 관리자 프로필 이미지 *24.02.03
        @Schema(description = "관리자 프로필 이미지")
        private MultipartFile image;
    }

    @Schema(description = "관리자 회원가입 응답", name="AdminsignupForm.Response")
    @Getter @Setter
    @Builder
    public static class Response {

        @Schema(description = "관리자 아이디", example = "testAdminId")
        private String id;

        @Schema(description = "관리자 비밀번호", example = "testAdminPassword")
        private String name;
    }
}
