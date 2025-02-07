package com.blog.springbootkeycloak.dto;

import lombok.*;

/**
 * Access Token 발급 요청 객체
 *
 * @author : jonghoon
 * @fileName : AccessTokenReqDto
 * @since : 25. 1. 25.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenReqDto {

    private String code;
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String username;
    private String password;
    private String redirect_uri;

    @Builder
    public AccessTokenReqDto(String code, String grant_type, String client_id, String client_secret, String username, String password, String redirect_uri) {
        this.code = code;
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.username = username;
        this.password = password;
        this.redirect_uri = redirect_uri;
    }
}
