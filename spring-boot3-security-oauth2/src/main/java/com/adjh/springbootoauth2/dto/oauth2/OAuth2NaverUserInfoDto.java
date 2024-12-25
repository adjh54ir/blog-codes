package com.adjh.springbootoauth2.dto.oauth2;

import lombok.*;

/**
 * 카카오 로그인 반환 사용자 정보
 *
 * @author : jonghoon
 * @fileName : OAuth2NaverUserInfoDto
 * @since : 11/3/24
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuth2NaverUserInfoDto {
    private String resultCode;
    private String message;
    private NaverUserResponse response;

    @Getter
    @Builder
    @ToString
    public static class NaverUserResponse {
        private String id;
        private String nickname;
        private String email;
        private String name;
    }

    @Builder
    public OAuth2NaverUserInfoDto(String resultCode, String message, NaverUserResponse response) {
        this.resultCode = resultCode;
        this.message = message;
        this.response = response;
    }
}
