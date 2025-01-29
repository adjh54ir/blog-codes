package com.blog.springbootkeycloak.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Auth Flow 중 Implicit 방식 데이터 구성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImplicitFlowDto {
    private String client_id;
    private String response_type;
    private String redirect_uri;
    private String scope;
    private String state;
    private String nonce;

    @Builder
    public ImplicitFlowDto(String client_id, String response_type, String redirect_uri,
                           String scope, String state, String nonce) {
        this.client_id = client_id;
        this.response_type = response_type;
        this.redirect_uri = redirect_uri;
        this.scope = scope;
        this.state = state;
        this.nonce = nonce;
    }
}