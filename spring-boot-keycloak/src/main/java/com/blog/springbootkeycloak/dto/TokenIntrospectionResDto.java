package com.blog.springbootkeycloak.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * 토큰 유효성 검증 수행 이후 응답 객체
 *
 * @author : leejonghoon
 * @fileName : TokenIntrospectionResDto
 * @since : 2025. 2. 3.
 */

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenIntrospectionResDto {
    private Long exp;                     // 토큰 만료 시간
    private Long iat;                     // 토큰 발급 시간
    private String jti;                   // JWT ID
    private String iss;                   // 토큰 발급자
    private String[] aud;                 // 토큰 대상
    private String sub;                   // 사용자 ID
    private String typ;                   // 토큰 타입
    private String azp;                   // Authorized party
    private String sid;                   // 세션 ID
    private String acr;                   // Authentication Context Class Reference
    @JsonProperty("allowed-origins")
    private List<String> allowedOrigins;  // 허용된 출처

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class Access {
        private String[] roles;
    }

    @JsonProperty("realm_access")
    private Access realmAccess;                     // Realm 권한

    @JsonProperty("resource_access")
    private Map<String, Access> resourceAccess;     // 리소스 접근 권한
    private String scope;                           // 권한 범위

    @JsonProperty("email_verified")
    private Boolean emailVerified;                  // 이메일 인증 여부
    private String name;                            // 전체 이름

    @JsonProperty("preferred_username")
    private String preferredUsername;               // 선호하는 사용자명
    private String givenName;                       // 이름
    private String familyName;                      // 성
    private String email;                           // 이메일

    @JsonProperty("client_id")
    private String clientId;                        // 클라이언트 ID
    private String username;                        // 사용자명

    @JsonProperty("token_type")
    private String tokenType;                       // 토큰 타입
    private Boolean active;                         // 토큰 활성 상태
}