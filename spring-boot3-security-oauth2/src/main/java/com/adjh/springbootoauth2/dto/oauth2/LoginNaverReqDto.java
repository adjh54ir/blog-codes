package com.adjh.springbootoauth2.dto.oauth2;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * OAUth 2.0 네이버 로그인 요청 겍체 DTO 구성
 *
 * @author : jonghoon
 * @fileName : LoginNaverReqDto
 * @since : 11/1/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginNaverReqDto {
    private String responseType;  // 인증 과정에 대한 내부 구분값으로 'code'로 전송해야 함
    private String clientId;      // 애플리케이션 등록 시 발급받은 Client ID 값
    private String redirectUri;   // 애플리케이션을 등록 시 입력한 Callback URL 값으로 URL 인코딩을 적용한 값
    private String state;         // 사이트 간 요청 위조(cross-site request forgery) 공격을 방지하기 위해 애플리케이션에서 생성한 상태 토큰값으로 URL 인코딩을 적용한 값을 사용

    @Builder
    public LoginNaverReqDto(String responseType, String clientId, String redirectUri, String state) {
        this.responseType = responseType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.state = state;
    }
}
