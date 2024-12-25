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

    private String name;                    // [Biz] 사용자 이름

    private String ageRange;                // [Biz] 사용자 나이 범위

    private String birthday;                // [Biz] 사용자 생일

    private String gender;                  // [Biz] 사용자 성별


    @Builder
    public OAuth2KakaoUserInfoDto(String id, int statusCode, String email, String nickname, String profileImageUrl, String thumbnailImageUrl, String name, String ageRange, String birthday, String gender) {
        this.id = id;
        this.statusCode = statusCode;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.name = name;
        this.ageRange = ageRange;
        this.birthday = birthday;
        this.gender = gender;
    }
}
