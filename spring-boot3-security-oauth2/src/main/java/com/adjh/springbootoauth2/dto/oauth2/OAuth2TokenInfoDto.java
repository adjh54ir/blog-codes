package com.adjh.springbootoauth2.dto.oauth2;

import lombok.*;

/**
 * OAuth 2.0 토큰 반환 값을 정의한 DTO
 *
 * @author : jonghoon
 * @fileName : OAuth2TokenInfoDto
 * @since : 11/9/24
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuth2TokenInfoDto {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String expiresIn;
    private String error;
    private String errorDescription;


    @Builder
    public OAuth2TokenInfoDto(String accessToken, String refreshToken, String tokenType, String expiresIn, String error, String errorDescription) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.error = error;
        this.errorDescription = errorDescription;
    }
}
