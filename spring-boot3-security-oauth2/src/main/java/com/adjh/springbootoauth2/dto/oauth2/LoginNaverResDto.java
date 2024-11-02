package com.adjh.springbootoauth2.dto.oauth2;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * OAUth 2.0 네이버 로그인 응답 겍체 DTO 구성
 *
 * @author : jonghoon
 * @fileName : LoginNaverResDto
 * @since : 11/1/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginNaverResDto {
    private String code;              // 네이버 로그인 인증에 성공하면 반환받는 인증 코드, 접근 토큰(access token) 발급에 사용
    private String state;             // 사이트 간 요청 위조 공격을 방지하기 위해 애플리케이션에서 생성한 상태 토큰으로 URL 인코딩을 적용한 값
    private String error;             // 네이버 로그인 인증에 실패하면 반환받는 에러 코드
    private String errorDescription;  // 네이버 로그인 인증에 실패하면 반환받는 에러 메시지

    @Builder
    public LoginNaverResDto(String code, String state, String error, String errorDescription) {
        this.code = code;
        this.state = state;
        this.error = error;
        this.errorDescription = errorDescription;
    }
}
