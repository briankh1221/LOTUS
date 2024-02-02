package com.project.lotus.auth.service.impl;

import com.project.lotus.auth.entity.User;
import com.project.lotus.auth.repository.UserRepository;
import com.project.lotus.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.lotus.common.enums.Role.ROLE_ADMIN;
import static com.project.lotus.common.enums.Role.ROLE_USER;
import static com.project.lotus.common.exception.ErrorCode.ID_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ID_NOT_EXISTS));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (user.getRole().toString().equals(ROLE_ADMIN.toString())) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (user.getRole().toString().equals(ROLE_USER.toString())) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}
