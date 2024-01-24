package com.project.lotus.auth.controller;

import com.project.lotus.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 로그인 회원가입 관련 페이지 이동 컨트롤러 *24.01.19 Hoon
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/signup")
    public String signUpForm() {
        LOGGER.info("signUpForm 매서드가 실행되었습니다");
        return "pages/auth/signup";
    }

    @RequestMapping("/signin")
    public String signInForm() {
        LOGGER.info("signInForm 매서드가 실행되었습니다");
        return "pages/auth/signin";
    }

    @RequestMapping("/signout")
    public String signOut() {
        LOGGER.info("signOut 매서드가 실행되었습니다");
        return "pages/main/main";}

}
