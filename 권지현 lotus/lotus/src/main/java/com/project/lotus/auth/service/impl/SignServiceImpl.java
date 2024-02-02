package com.project.lotus.auth.service.impl;


import com.project.lotus.auth.dto.AdminsignupForm;
import com.project.lotus.auth.dto.SigninDto;
import com.project.lotus.auth.dto.SignupDto;
import com.project.lotus.auth.dto.UsersignupForm;
import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.repository.UserRepository;
import com.project.lotus.auth.service.SignService;
import com.project.lotus.common.exception.CustomException;
import com.project.lotus.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.lotus.common.exception.ErrorCode.*;
import static com.project.lotus.common.enums.Role.ROLE_ADMIN;
import static com.project.lotus.common.enums.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    // 이용자 회원가입 *24.01.18 jihyun
    @Override
    public SignupDto.Response userSignup(SignupDto.Request signupDto) {

        if (userRepository.findById(signupDto.getId()).isPresent()) {
            throw new CustomException(ID_ALREADY_EXISTS);
        }

        if (userRepository.findByEmail(signupDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User savedUser = userRepository.save(
                User.from(signupDto, passwordEncoder.encode(signupDto.getPassword()), ROLE_USER));
        return SignupDto.Response.builder()
                .id(savedUser.getId())
                .name((savedUser.getName()))
                .build();
    }

    // 관리자 회원가입 *24.01.18 jihyun
    @Override
    public SignupDto.Response adminSignup(SignupDto.Request signupDto) {

        userRepository.findByEmail(signupDto.getEmail()).ifPresent(user -> {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        });

        User savedAdmin =  userRepository.save(
                User.from(signupDto, passwordEncoder.encode(signupDto.getPassword()), ROLE_ADMIN));

        return SignupDto.Response.builder()
                .id(savedAdmin.getId())
                .name((savedAdmin.getName()))
                .build();
    }

    // 공통(이용자/관리자) 로그인 *24.01.18 jihyun
    @Override
    public SigninDto.Response signin(SigninDto.Request signinDto) {
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
