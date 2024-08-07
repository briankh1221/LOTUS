package com.project.lotus.auth.controller;

import com.project.lotus.auth.dto.*;
import com.project.lotus.auth.service.impl.AuthServiceImpl;
import com.project.lotus.common.config.security.TokenProvider;
import com.project.lotus.common.config.security.dto.TokenDto;
import com.project.lotus.common.config.security.service.JwtService;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Auth-Controller", description = "회원가입, 로그인 관련 API")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;

    private final JwtService jwtService;
    private final AuthServiceImpl authService;

    @Value("${admin.code}")
    private String ADMIN_CODE;

    // 이용자 회원 가입 *24.01.18 jihyun
    @Operation(summary = "이용자 회원가입", description = "이용자는 아이디, 비밀번호, 이름, 전화번호, 이메일로 회원가입합니다.")
    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsersignupForm.Response> userSignup(
             @Valid UsersignupForm.Request usersignupForm) {

        SignupDto.Response signuptDto = authService.userSignup(usersignupForm);

        return ResponseEntity.status(CREATED)
                .body(UsersignupForm.Response.builder()
                        .id(signuptDto.getId())
                        .name(signuptDto.getName())
                        .build());
    }

    // 관리자 회원 가입 *24.01.18 jihyun
    @Operation(summary = "관리자 회원가입", description = "관리자는 인증번호, 아이디, 비밀번호, 이름, 전화번호, 이메일로 회원가입합니다.")
    @PostMapping(value = "/signup/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdminsignupForm.Response> adminSignup(
             @Valid AdminsignupForm.Request adminsignupForm) {

        if (!ADMIN_CODE.equals(adminsignupForm.getAdminCode())) {
            throw new CustomException(ErrorCode.ADMIN_CODE_NOT_MATCH);
        }

        SignupDto.Response signuptDto = authService.adminSignup(adminsignupForm);

        return ResponseEntity.status(CREATED)
                .body(AdminsignupForm.Response.builder()
                        .id(signuptDto.getId())
                        .name(signuptDto.getName())
                        .build());
    }

    // 공통(이용자/관리자) 로그인 *24.01.19 jihyun
    @Operation(summary = "이용자, 관리자 로그인", description = "이용자, 관리자 모두 아이디와 비밀번호로 로그인합니다.")
    @PostMapping(value = "/signin")
    public ResponseEntity<SigninForm.Response> signin(
            @Valid @RequestBody SigninForm.Request signinForm) {

        SigninDto.Response signinDto = authService.signin(signinForm);

        // Access Token, Refresh Token 생성 *24.01.29 jihyun
        TokenDto tokenDto = tokenProvider.createToken(
                    signinDto.getEmail(), signinDto.getRole());

        // Refresh Token Repository에 저장 *24.01.22 jihyun
        jwtService.save(tokenDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(SigninForm.Response.builder()
                        .tokenDto(tokenDto)
                        .build());
    }

    @ExceptionHandler (value=RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {

        HttpHeaders responseHeaders = new HttpHeaders();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러가 발생했습니다");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
