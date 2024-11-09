package com.adjh.springbootoauth2.controller;

import com.adjh.springbootoauth2.dto.oauth2.OAuth2KakaoUserInfoDto;
import com.adjh.springbootoauth2.dto.oauth2.OAuth2NaverUserInfoDto;
import com.adjh.springbootoauth2.dto.oauth2.OAuth2AuthInfoDto;
import com.adjh.springbootoauth2.service.OAuth2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * OAuth 2.0 Controller
 *
 * @author : jonghoon
 * @fileName : OAuth2Controller
 * @since : 11/1/24
 */
@Slf4j
@RestController
@RequestMapping("api/v1/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    public OAuth2Controller(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    /**
     * [API] 카카오톡 로그인 : Redirect URL
     *
     * @param code              토큰 받기 요청에 필요한 인가 코드
     * @param error             인증 실패 시 반환되는 에러 코드
     * @param error_description 인증 실패 시 반환되는 에러 메시지
     * @param state             요청 시 전달한 state 값과 동일한 값
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     * @Refrence : https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#kakaologin
     */
    @GetMapping("/kakao")
    public ResponseEntity<OAuth2KakaoUserInfoDto> kakaoLogin(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String error_description,
            @RequestParam(required = false) String state
    ) {

        log.debug("Kakao Login - code: {}, error: {}, error_description: {}, state: {}", code, error, error_description, state);
        OAuth2AuthInfoDto kakaoReqDto = OAuth2AuthInfoDto.builder()
                .code(code)
                .error(error)
                .errorDescription(error_description)
                .state(state)
                .build();

        OAuth2KakaoUserInfoDto resultObj = oAuth2Service.kakaoLogin(kakaoReqDto);

        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }


    /**
     * [API] 네이버 로그인 : : Redirect URL
     *
     * @param code              네이버 로그인 인증에 성공하면 반환받는 인증 코드, 접근 토큰(access token) 발급에 사용
     * @param error             네이버 로그인 인증에 실패하면 반환받는 에러 코드
     * @param error_description 네이버 로그인 인증에 실패하면 반환받는 에러 메시지
     * @param state             사이트 간 요청 위조 공격을 방지하기 위해 애플리케이션에서 생성한 상태 토큰으로 URL 인코딩을 적용한 값
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     * @refrence : https://developers.naver.com/docs/login/api/api.md#4-1--%EB%84%A4%EC%9D%B4%EB%B2%84-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%9D%B8%EC%A6%9D-%EC%9A%94%EC%B2%AD
     */
    @GetMapping("/naver")
    public ResponseEntity<OAuth2NaverUserInfoDto> naverLogin(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String error_description,
            @RequestParam(required = false) String state
    ) {

        log.debug("Naver Login - code: {}, error: {}, error_description: {}, state: {}", code, error, error_description, state);
        OAuth2AuthInfoDto naverReqDto = OAuth2AuthInfoDto.builder()
                .code(code)
                .error(error)
                .errorDescription(error_description)
                .state(state)
                .build();

        OAuth2NaverUserInfoDto resultObj = oAuth2Service.naverLogin(naverReqDto);

        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }
}
