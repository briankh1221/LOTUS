package com.project.lotus.common.config.security;

import com.project.lotus.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @Autowired
    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider, AuthService authService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authService = authService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(HttpBasicConfigurer::disable)

                .cors(AbstractHttpConfigurer::disable)

                //.formLogin(Customizer.withDefaults())

                .csrf(CsrfConfigurer::disable)

                .sessionManagement(configurer -> configurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**", "/auth-api/**", "/").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
                        .requestMatchers( "/user/**").authenticated()
                        .requestMatchers("**exception**").permitAll()
                        .anyRequest().hasRole("ADMIN"))
                .formLogin(FormLoginConfigurer -> FormLoginConfigurer
                        .loginPage("/auth/signin")
                        .usernameParameter("id")
                        .passwordParameter("pw")
                        .loginProcessingUrl("/auth-api/signin")
                        .defaultSuccessUrl("/", true))
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutSuccessUrl("/"))
                .userDetailsService(authService)

                .exceptionHandling(authenticationManager -> authenticationManager
                        //.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomAccessDeniedHandler()))

                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    // HttpSecurity 보다 먼저 적용. 인증, 인가 적용 이전. Swagger 관련 예외만 처리
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**",
                "/swagger-ui/index.html", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/auth-api/**", "/auth/**")); // "/favicon.ico"
    }

}
