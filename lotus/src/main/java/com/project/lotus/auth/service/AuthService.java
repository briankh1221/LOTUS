package com.project.lotus.auth.service;

import com.project.lotus.auth.dto.*;
import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.repository.UserRepository;
import com.project.lotus.common.config.security.JwtTokenProvider;
import com.project.lotus.common.config.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    public SignupDto.Response signup(SignupDto.Request signupDto) {

        User savedUser = userRepository.save(User.from(signupDto, passwordEncoder.encode(signupDto.getPw()), Role.ROLE_USER));

        return SignupDto.Response.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .build();
    }

    public SigninDto.Response singin(SigninDto.Request signinDto) {
        User user = userRepository.getById(signinDto.getId());
        if(passwordEncoder.matches(signinDto.getPw(), user.getPw())) {
            return SigninDto.Response.builder()
                    .idx(user.getIdx())
                    .id(user.getId())
                    .role(user.getRole())
                    .build();
        }
        else {
            return null;
        }
    }

    public GetAuthForm.Response getAuth(String token) {
        //Authentication auth = jwtTokenProvider.getAuthentication(token);

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.getById(id);
    }
}
