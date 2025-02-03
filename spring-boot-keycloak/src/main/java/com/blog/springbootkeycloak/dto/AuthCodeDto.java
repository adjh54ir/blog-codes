package com.blog.springbootkeycloak.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인가 코드 DTO
 *
 * @author : jonghoon
 * @fileName : AuthCodeDto
 * @since : 25. 1. 28.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthCodeDto {
    private String response_type;
    private String client_id;
    private String redirect_uri;
    private String scope;
    private String state;

    @Builder
    public AuthCodeDto(String response_type, String client_id, String redirect_uri, String scope, String state) {
        this.response_type = response_type;
        this.client_id = client_id;
        this.redirect_uri = redirect_uri;
        this.scope = scope;
        this.state = state;
    }
}
