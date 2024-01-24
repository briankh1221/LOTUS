package com.project.lotus.auth.controller;

import com.project.lotus.auth.dto.*;
import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.service.AuthService;
import com.project.lotus.common.config.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 로그인 회원가입 관련 컨트롤러 *24.01.19 Hoon
@RestController
@RequestMapping("/auth-api")
public class AuthRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthRestController.class);
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthRestController(AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 회원가입 매서드 *24.01.19 Hoon
    @RequestMapping("/signup")
    public ResponseEntity<SignupUserForm.Response> signup(SignupUserForm.Request signupUserForm) {
        LOGGER.debug("signup 매서드가 실행되었습니다");

        SignupDto.Response response = authService.signup(SignupDto.Request.from(signupUserForm));

        return ResponseEntity.status(HttpStatus.OK)
                .body(SignupUserForm.Response.builder()
                        .id(response.getId())
                        .name(response.getName())
                        .build());
    }

    // 로그인 매서드, id, pw 기반으로 토큰 생성 후 사용자에게 전달 *24.01.19 Hoon
    // 생성한 토큰으로 authentication 생성 후 set *24.01.19 Hoon
    @PostMapping("/signin")
    public String signin(SigninUserForm.Request signinUserFrom) {
        LOGGER.info("signin 매서드가 실행되었습니다");

        SigninDto.Response response = authService.singin(SigninDto.Request.from(signinUserFrom));

        String token = jwtTokenProvider.createToken(response.getId(), response.getRole());

        if(token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            SecurityContextHolder.getContext();
        }

        return "/pages/auth/signup";
    }

    // 각 페이지 이동후 header에서 요청됨. 토큰을 기반으로 사용자 정보 반환 *24.01.19 Hoon
    @RequestMapping("/getAuth")
    public ResponseEntity getAuth(@RequestHeader("token") String token) {
        LOGGER.info("getAuth 매서드가 실행되었습니다");
        Authentication auth = jwtTokenProvider.getAuthentication(token);
        User user = (User)auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(GetAuthForm.Response.builder()
                        .idx(user.getIdx())
                        .role(user.getRole())
                        .build());



    }


}
