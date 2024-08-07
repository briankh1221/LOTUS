package com.project.lotus.common.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 필터 실행 전 Token 추출함 *24.01.18 jihyun
        String AccessToken = resolveTokenFromRequest(request);

        // Access Token 유효성 검사 *24.01.18 jihyun
        if (StringUtils.hasText(AccessToken) && tokenProvider.validateToken(AccessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(AccessToken);

            // SecurityContextHolder에 Authentication 저장 *24.01.23 jihyun
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            logger.debug("유효한 Access Token이 없습니다.");
        }
        // 다음 filter Chain 실행 *24.01.18 jihyun
        filterChain.doFilter(request, response);
    }

    // HTTP Header에서 Access Token 추출 *24 01 23 jihyun
    private String resolveTokenFromRequest(HttpServletRequest request) {

        String token = request.getHeader(TOKEN_HEADER);

        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length());
        }

        return token;
    }
}
