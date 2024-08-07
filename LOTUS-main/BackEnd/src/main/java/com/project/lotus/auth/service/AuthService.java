package com.project.lotus.auth.service;

import com.project.lotus.auth.dto.*;
import java.io.IOException;

public interface AuthService {

    // 이용자 회원가입 *24.01.18 jihyun
    public SignupDto.Response userSignup (UsersignupForm.Request usersignupForm) throws IOException;

    // 관리자 회원가입 *24.01.18 jihyun
    public SignupDto.Response adminSignup (AdminsignupForm.Request adminsignupForm) throws IOException;

    // 공통(이용자/관리자) 로그인 *24.01.18 jihyun
    public SigninDto.Response signin (SigninForm.Request signinForm);
}
