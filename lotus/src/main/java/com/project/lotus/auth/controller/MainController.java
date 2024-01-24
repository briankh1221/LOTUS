package com.project.lotus.auth.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MainController {

    @RequestMapping("/")
    public String main(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "pages/main/main";
    }
}
