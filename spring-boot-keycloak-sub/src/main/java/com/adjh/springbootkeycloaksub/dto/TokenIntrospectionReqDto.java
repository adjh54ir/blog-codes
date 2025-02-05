package com.adjh.springbootkeycloaksub.dto;

import lombok.*;

/**
 * 토큰 유효성 검증을 위한 요청 객체
 *
 * @author : leejonghoon
 * @fileName : TokenIntrospectionReqDto
 * @since : 2025. 2. 3.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenIntrospectionReqDto {
    private String token;
    private String client_id;
    private String client_secret;

    @Builder
    public TokenIntrospectionReqDto(String token, String client_id, String client_secret) {
        this.token = token;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }
}
