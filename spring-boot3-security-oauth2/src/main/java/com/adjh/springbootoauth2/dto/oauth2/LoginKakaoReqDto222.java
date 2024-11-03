package com.adjh.springbootoauth2.dto.oauth2;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * OAUth 2.0 카카오 로그인 요청 겍체 DTO 구성
 *
 * @author : jonghoon
 * @fileName : LoginKakaoReqDto
 * @since : 11/1/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginKakaoReqDto222 {

    private String clientId;        // 필수: 앱 REST API 키, [내 애플리케이션] > [앱 키]에서 확인 가능
    private String redirectUri;     // 필수: 인가 코드를 전달받을 서비스 서버의 URI
    private String responseType;    // 필수: "code"로 고정
    private String scope;           // 선택: 동의 요청할 동의항목 ID 목록, 쉼표(,)로 구분
    private String prompt;          // 선택: 추가 상호작용 요청 (예: "login", "none", "create", "select_account")
    private String loginHint;       // 선택: 카카오계정 로그인 페이지의 ID란에 자동 입력할 값
    private String serviceTerms;    // 선택: 동의받을 서비스 약관 태그 목록, 쉼표(,)로 구분
    private String state;           // 선택: CSRF 공격 방지를 위한 임의의 문자열
    private String nonce;           // 선택: ID 토큰 재생 공격 방지를 위한 임의의 문자열


    @Builder
    public LoginKakaoReqDto222(String clientId, String redirectUri, String responseType, String scope, String prompt, String loginHint, String serviceTerms, String state, String nonce) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.responseType = responseType;
        this.scope = scope;
        this.prompt = prompt;
        this.loginHint = loginHint;
        this.serviceTerms = serviceTerms;
        this.state = state;
        this.nonce = nonce;
    }
}
