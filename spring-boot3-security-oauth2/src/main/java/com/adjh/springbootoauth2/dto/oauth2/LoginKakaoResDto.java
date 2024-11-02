package com.adjh.springbootoauth2.dto.oauth2;

import lombok.*;

/**
 * OAUth 2.0 네이버 로그인 응답 겍체 DTO 구성
 *
 * @author : jonghoon
 * @fileName : LoginKakaoResDto
 * @since : 11/1/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LoginKakaoResDto {

    private String code;              // 토큰 받기 요청에 필요한 인가 코드
    private String error;             // 인증 실패 시 반환되는 에러 코드
    private String errorDescription;  // 인증 실패 시 반환되는 에러 메시지
    private String state;             // 요청 시 전달한 state 값과 동일한 값

    @Builder
    public LoginKakaoResDto(String code, String error, String errorDescription, String state) {
        this.code = code;
        this.error = error;
        this.errorDescription = errorDescription;
        this.state = state;
    }
}