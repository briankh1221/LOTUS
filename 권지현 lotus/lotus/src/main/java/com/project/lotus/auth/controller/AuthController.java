package com.project.lotus.auth.controller;


import com.project.lotus.auth.dto.*;
import com.project.lotus.auth.service.impl.SignServiceImpl;
import com.project.lotus.common.config.security.TokenProvider;
import com.project.lotus.common.config.security.dto.TokenDto;
import com.project.lotus.common.config.security.service.JwtService;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.common.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignServiceImpl signServiceImpl;

    private final TokenProvider tokenProvider;

    private final JwtService jwtService;

    @Value("${admin.code}")
    private String ADMIN_CODE;

    // 이용자 회원 가입 *24.01.18 jihyun
    @PostMapping("/signup")
    public ResponseEntity<UsersignupForm.Response> userSignup(
             @Valid UsersignupForm.Request usersignupForm) {

        SignupDto.Response signuptDto = signServiceImpl.userSignup(SignupDto.Request.from(usersignupForm));

        return ResponseEntity.status(CREATED)
                .body(UsersignupForm.Response.builder()
                        .id(signuptDto.getId())
                        .name(signuptDto.getName())
                        .build());
    }

    // 관리자 회원 가입 *24.01.18 jihyun
    @PostMapping("/signup/admin")
    public ResponseEntity<AdminsignupForm.Response> adminSignup(
             @Valid AdminsignupForm.Request adminsignupForm) {

        if (!ADMIN_CODE.equals(adminsignupForm.getAdminCode())) {
            throw new CustomException(ErrorCode.ADMIN_CODE_NOT_MATCH);
        }

        SignupDto.Response signuptDto = signServiceImpl.adminSignup(SignupDto.Request.from(adminsignupForm));

        return ResponseEntity.status(CREATED)
                .body(AdminsignupForm.Response.builder()
                        .id(signuptDto.getId())
                        .name(signuptDto.getName())
                        .build());
    }

    // 공통(이용자/관리자) 로그인 *24.01.19 jihyun
    @PostMapping("/signin")
    public ResponseEntity<SigninForm.Response> signin(
            @RequestBody @Valid SigninForm.Request signinForm) {

        SigninDto.Response signinDto = signServiceImpl.signin(
                SigninDto.Request.from(signinForm));

        // Access Toekn, Refresh Token 생성 *24.01.29 jihyun
        TokenDto tokenDto = tokenProvider.createToken(
                    signinDto.getEmail(), signinDto.getRole());

        // Refresh Token Repository에 저장 *24.01.22 jihyun
        jwtService.save(tokenDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(SigninForm.Response.builder()
                        .tokenDto(tokenDto).build());
        }

    @ExceptionHandler(value=RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {

        HttpHeaders responseHeaders = new HttpHeaders();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
