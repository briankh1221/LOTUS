package com.project.lotus.auth.service;

import com.project.lotus.auth.dto.AdminsignupForm;
import com.project.lotus.auth.dto.SigninDto;
import com.project.lotus.auth.dto.SignupDto;

public interface SignService {

    // 이용자 회원가입 *24.01.18 jihyun
    public SignupDto.Response userSignup(SignupDto.Request signupDto);

    // 관리자 회원가입 *24.01.18 jihyun
    public SignupDto.Response adminSignup(SignupDto.Request signupDto);

    // 공통(이용자/관리자) 로그인 *24.01.18 jihyun
    public SigninDto.Response signin(SigninDto.Request signinDto);
}
