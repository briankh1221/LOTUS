package com.project.lotus.common.config.security.service;

import com.project.lotus.common.config.security.TokenProvider;
import com.project.lotus.common.config.security.dto.TokenDto;
import com.project.lotus.common.config.security.repository.RefreshTokenRepository;
import com.project.lotus.common.config.security.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    // RefreshTokenRepository에 저장함 *24.01.29 jihyun
    public void save(TokenDto tokenDto) {

        RefreshToken refreshToken = RefreshToken.builder()
                .keyEmail(tokenDto.getKey())
                .refreshToken(tokenDto.getRefreshToken())
                .build();

        String email = refreshToken.getKeyEmail();

        if (refreshTokenRepository.existsByKeyEmail(email)) {
            refreshTokenRepository.deleteByKeyEmail(email);
        }
        refreshTokenRepository.save(refreshToken);
    }

    //  RefreshTokenRepository(DB)에서 Refresh Token 가져오기 위한 메서드 *24.01.29 jihyun
    public Optional<RefreshToken> getRefreshToken(String refreshToken) {

        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    public Map<String, String> validateRefreshToken(String refreshToken){

        RefreshToken refreshToken1 = getRefreshToken(refreshToken).get();

        String createdAccessToken = tokenProvider.validateRefreshToken(refreshToken1);

        return createRefreshJson(createdAccessToken);
    }

    public Map<String, String> createRefreshJson(String createdAccessToken) {

        Map<String, String> map = new HashMap<>();

        if (createdAccessToken == null) {
            map.put("errortype", "Unauthorized");
            map.put("status", "401");
            map.put("message", "리프레시 토큰(Refresh Token)이 만료되었습니다. 로그인이 필요합니다.");

            return map;
        }

        map.put("status", "200");
        map.put("message", "리프레시 토큰(Refresh Token)을 통한 엑세스 토큰(Access Token) 생성이 완료되었습니다.");
        map.put("accessToken", createdAccessToken);

        return map;
    }
}
