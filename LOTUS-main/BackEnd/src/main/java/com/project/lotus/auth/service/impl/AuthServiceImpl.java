package com.project.lotus.auth.service.impl;

import com.project.lotus.auth.dto.*;
import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.repository.UserRepository;
import com.project.lotus.auth.service.AuthService;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.project.lotus.common.enums.Role.ROLE_ADMIN;
import static com.project.lotus.common.enums.Role.ROLE_USER;
import static com.project.lotus.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    // 이용자 회원가입 *24.01.18 jihyun
    @Override
    public SignupDto.Response userSignup (UsersignupForm.Request usersignupForm)  {

        SignupDto.Request signupDto = SignupDto.Request.from(usersignupForm);

        if (userRepository.findById(signupDto.getId()).isPresent()) {
            throw new CustomException(ID_ALREADY_EXISTS);
        }

        if (userRepository.findByEmail(signupDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = userRepository.save(
                User.from(signupDto, passwordEncoder.encode(signupDto.getPassword()), ROLE_USER));

        return SignupDto.Response.builder()
                .id(user.getId())
                .name((user.getName()))
                .build();
    }

    // 관리자 회원가입 *24.01.18 jihyun
    @Override
    public SignupDto.Response adminSignup (AdminsignupForm.Request adminsignupForm) {

        SignupDto.Request signupDto = SignupDto.Request.from(adminsignupForm);

        userRepository.findByEmail(signupDto.getEmail()).ifPresent(user -> {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);}
        );

        User admin =  userRepository.save(
                User.from(signupDto, passwordEncoder.encode(signupDto.getPassword()), ROLE_ADMIN));

        return SignupDto.Response.builder()
                .id(admin.getId())
                .name((admin.getName()))
                .build();
    }

    // 공통(이용자/관리자) 로그인 *24.01.18 jihyun
    @Override
    public SigninDto.Response signin (SigninForm.Request signinForm) {

        SigninDto.Request signinDto = SigninDto.Request.from(signinForm);

        User user = userRepository.findById(signinDto.getId())
                .orElseThrow(() -> new CustomException(ID_NOT_EXISTS));

        if (!passwordEncoder.matches(signinDto.getPassword(), user.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        return SigninDto.Response.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
