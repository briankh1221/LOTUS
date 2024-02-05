package com.project.lotus.common.config.security.controller;


import com.project.lotus.common.config.security.service.JwtService;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/refreshToken")
public class RefreshTokenController {

    private final JwtService jwtService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RefreshTokenResponse> validateRefreshToken (
            @RequestHeader(name = "refreshToken") String refreshToken) {

        Map<String, String> map = jwtService.validateRefreshToken(refreshToken);

        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse(map);

        if(map.get("status").equals("402")) {
            return new ResponseEntity<RefreshTokenResponse>(refreshTokenResponse, HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.status(OK).body(refreshTokenResponse);
    }

    @Getter
    @RequiredArgsConstructor
    @Builder
    @ToString
    public static class RefreshTokenResponse {

        // Refresh Token 상태 *24.01.24 jihyun
        private final String status;
        // Refresh Token 메시지 *24.01.24 jihyun
        private final String message;
        // Access Token 메시지 *24.01.24 jihyun
        private final String accessToken;

        public RefreshTokenResponse(Map<String, String> map) {
            this.status = map.get("status");
            this.message = map.get("message");
            this.accessToken = map.get("accessToken");
        }
    }
}
