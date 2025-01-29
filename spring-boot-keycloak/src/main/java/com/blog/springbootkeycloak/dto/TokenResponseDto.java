package com.blog.springbootkeycloak.dto;

import lombok.*;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : TokenResponseDto
 * @since : 25. 1. 28.
 */
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponseDto {
    private String access_token;
    private int expires_in;
    private int refresh_expires_in;
    private String refresh_token;
    private String token_type;
    private int not_before_policy;
    private String session_state;
    private String scope;

    @Builder
    public TokenResponseDto(String access_token, int expires_in, int refresh_expires_in, String refresh_token, String token_type, int not_before_policy, String session_state, String scope) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_expires_in = refresh_expires_in;
        this.refresh_token = refresh_token;
        this.token_type = token_type;
        this.not_before_policy = not_before_policy;
        this.session_state = session_state;
        this.scope = scope;
    }
}