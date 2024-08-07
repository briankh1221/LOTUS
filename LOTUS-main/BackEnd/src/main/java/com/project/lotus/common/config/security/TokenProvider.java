package com.project.lotus.common.config.security;

import com.project.lotus.auth.service.impl.UserDetailsServiceImpl;
import com.project.lotus.common.config.security.dto.TokenDto;
import com.project.lotus.common.config.security.entity.RefreshToken;
import com.project.lotus.common.enums.Role;
import com.project.lotus.common.exception.CustomException;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import static com.project.lotus.common.exception.ErrorCode.ACCESS_TOKEN_EXPIRED_EXCEPTION;
import static com.project.lotus.common.exception.ErrorCode.REFRESH_TOKEN_EXPIRED_EXCEPTION;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {

    private static final String KEY_ROLE = "role";

    // Access Token 30분 설정 *24.01.21 jihyun
    private static final long ACCESS_TOKEN_EXPIRED_TIME = 1000 * 60 * 30;

    // Refresh Token 1주일 설정 *24.01.22 jihyun
    private static final long REFRESH_TOKEN_EXPIRED_TIME = 1000 * 60 * 60 * 24 * 7;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    // Secret Key 인코딩 *24.01.23 jihyun
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Access Token, Refresh Token 생성(발급) *24.01.18 jihyun
    public TokenDto createToken(String email, Role role) {

        Claims claims = Jwts.claims()
                .setSubject(email);
        claims.put(KEY_ROLE, role);

        Date now = new Date();

        // Access Token 생성 *24.01.21 jihyun
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        // Refresh Token 생성 *24.01.21 jihyun
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .key(email)
                .build();
    }

    // Refresh Token 유효성 확인하면 Access Token 생성함 *24.01.21 jihyun
    public String validateRefreshToken(RefreshToken refreshTokenObj){

        // Refresh Token 객체에서 Refresh Token 추출 *24.01.21 jihyun
        String refreshToken = refreshTokenObj.getRefreshToken();

        try {
            // Refresh Token으로 Claims 추출함 *24.01.21 jihyun
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(refreshToken);

            // Refresh Token 유효하면, 새로운 Access Token을 생성함 *24.01.21 jihyun
            if (!claims.getBody().getExpiration().before(new Date())) {

                return recreateAccessToken(claims.getBody().get("sub").toString(), claims.getBody().get(KEY_ROLE));
            }
        } catch (Exception e) {
            // Refresh Token이 만료되었을 경우, 로그인이 필요함 *24.01.21 jihyun
            log.debug("로그인이 다시 필요합니다");

            throw new CustomException(REFRESH_TOKEN_EXPIRED_EXCEPTION);
        }
        return null;
    }

    // Access Token 재생성 *24.01.21 jihyun
    public String recreateAccessToken(String email, Object role) {

        Claims claims = Jwts.claims()
                .setSubject(email);
        claims.put(KEY_ROLE, role);

        Date now = new Date();

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return accessToken;
    }

    // JWT에서 인증정보 추출 *24.01.18 jihyun
    public Authentication getAuthentication(String token) {

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(getEmail(token));

        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    // Access Token에서 이용자 이메일 추출 *24.01.18 jihyun
    public String getEmail(String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return parseClaims(token).getSubject();
    }

    // Access Token 유효기간 검사 *24.01.18 jihyun
    public boolean validateToken(String token) {

        if (!StringUtils.hasText(token)) {
            return false;
        }

        Claims claims = parseClaims(token);

        return !claims.getExpiration().before(new Date());
    }

    // Access Token에서 클레임 정보 추출 *24.01.18 jihyun
    private Claims parseClaims(String token) {

        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        } catch (ExpiredJwtException e) {

             log.warn("만료된 Access Token 토큰입니다.");

            throw new CustomException(ACCESS_TOKEN_EXPIRED_EXCEPTION);
        }
    }
}
