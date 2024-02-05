package com.project.lotus.common.config.security.dto;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    // Access Token *24.01.22 jihyun
    private String accessToken;

    // Refresh Token *24.01.22 jihyun
    private String refreshToken;

    // Email *24.01.22 jihyun
    private String key;
}
