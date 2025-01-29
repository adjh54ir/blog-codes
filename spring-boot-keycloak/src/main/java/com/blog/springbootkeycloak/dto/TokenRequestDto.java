package com.blog.springbootkeycloak.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Access Token을 발급받기 위해 필요한 데이터
 *
 * @author : jonghoon
 * @fileName : TokenRequestDto
 * @since : 25. 1. 25.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenRequestDto {

    private String code;
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String username;
    private String password;
    private String redirect_uri;

    @Builder
    public TokenRequestDto(String code, String grant_type, String client_id, String client_secret, String username, String password, String redirect_uri) {
        this.code = code;
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.username = username;
        this.password = password;
        this.redirect_uri = redirect_uri;
    }
}
