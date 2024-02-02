package com.project.lotus.common.config.security;


import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//                .cors(corsConfigurer -> corsConfigurer
//                        .configurationSource(request -> {
//                            CorsConfiguration corsConfig = new CorsConfiguration();
//                            corsConfig.applyPermitDefaultValues();
//                            corsConfig.addAllowedOrigin("http://localhost:5173");
//                            return corsConfig;
//                        }))
                .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable).disable())

//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
//                        .requestMatchers("/auth/signin", "/auth/signup","/product/**", "/refreshToken", "/chat").permitAll()
//                        // Vue Permint 하기 위한 경로. 변경 될 수 있음 *24.01.26 jihyun
//                        .requestMatchers("/assets/**","/favicon.ico", "/index.html", "/").permitAll()
//                        .requestMatchers("/user/**").hasRole("USER")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated())

                .exceptionHandling(authenticationManager -> authenticationManager
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomAccessDeniedHandler()))

                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}