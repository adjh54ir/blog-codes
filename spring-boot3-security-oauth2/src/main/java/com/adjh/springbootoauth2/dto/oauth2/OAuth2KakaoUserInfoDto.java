package com.adjh.springbootoauth2.dto.oauth2;

import lombok.*;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuth2KakaoUserInfoDto
 * @since : 11/8/24
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuth2KakaoUserInfoDto {
    private String id;

    private int statusCode;                 // 상태 코드

    private String email;                   // 이메일

    private String nickname;                // 닉네임

    private String profileImageUrl;         // 프로필 이미지 URL

    private String thumbnailImageUrl;       // 썸네일 이미지 URL

    @Builder
    public OAuth2KakaoUserInfoDto(String id, int statusCode, String email, String nickname, String profileImageUrl, String thumbnailImageUrl) {
        this.id = id;
        this.statusCode = statusCode;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
}
