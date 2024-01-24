package com.project.lotus.auth.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/product-list")
    public String productList() {
        return "pages/user/product-list";
    }
}
