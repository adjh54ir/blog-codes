package com.adjh.springbootoauth2.service.impl;

import com.adjh.springbootoauth2.config.RestTemplateConfig;
import com.adjh.springbootoauth2.config.properties.OAuth2ProviderProperties;
import com.adjh.springbootoauth2.config.properties.OAuth2RegistrationProperties;
import com.adjh.springbootoauth2.dto.oauth2.*;
import com.adjh.springbootoauth2.service.OAuth2Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.sql.SQLOutput;
import java.util.*;

/**
 * OAuth 2.0을 처리하는 서비스 구현체
 *
 * @author : jonghoon
 * @fileName : OAuth2ServiceImpl
 * @since : 11/1/24
 */
@Slf4j
@Service("OAuth2ServiceImpl")
public class OAuth2ServiceImpl implements OAuth2Service {

    private final RestTemplateConfig restTemplateConfig;
    private final OAuth2ProviderProperties oAuthProvider;
    private final OAuth2RegistrationProperties oAuthRegistration;

    public OAuth2ServiceImpl(RestTemplateConfig restTemplateConfig, OAuth2ProviderProperties oAuthProvider, OAuth2RegistrationProperties oAuthRegistration) {
        this.restTemplateConfig = restTemplateConfig;
        this.oAuthProvider = oAuthProvider;
        this.oAuthRegistration = oAuthRegistration;
    }

    /**
     * 기본적으로 사용하는 Header를 구성하여 반환합니다.
     *
     * @return
     */
    private HttpHeaders defaultHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }

    /**
     * 제공자(Kakao) 로그인을 수행하고 정보를 반환 받는 서비스입니다.
     *
     * @param authInfo
     * @return
     */
    @Override
    public OAuth2KakaoUserInfoDto kakaoLogin(OAuth2AuthInfoDto authInfo) {


        log.debug("[+] 카카오 로그인이 성공하여 리다이렉트 되었습니다.", authInfo);
        log.debug("코드 값 확인 : {}", authInfo.getCode());
        log.debug("에러 값 확인 : {}", authInfo.getError());
        log.debug("에러 설명 값 확인 : {}", authInfo.getErrorDescription());
        log.debug("상태 값 확인 : {}", authInfo.getState());

        // [STEP1] 리다이렉트로 반환 받은 인증 코드의 존재여부를 체크합니다.
        if (authInfo.getCode() == null || authInfo.getCode().isEmpty()) {
            log.error("[-] 카카오 로그인 리다이렉션에서 문제가 발생하였습니다.");
            return null;
        }

        // [STEP2] 카카오로 토큰을 요청합니다.(접근 토큰, 갱신 토큰)
        OAuth2TokenInfoDto kakaoTokenInfo = this.getKakaoTokenInfo(authInfo.getCode());
        log.debug("토큰 정보 전체를 확인합니다 :: {}", kakaoTokenInfo);

        // [STEP3] 접근 토큰을 기반으로 사용자 정보를 요청합니다.
        OAuth2KakaoUserInfoDto userInfo = this.getKakaoUserInfo(kakaoTokenInfo.getAccessToken());
        log.debug("userInfo :: {}", userInfo);
        return userInfo;
    }

    /**
     * Convert Object To Map
     *
     * @param obj
     * @return
     */
    private Map<String, Object> cvtObjectToMap(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(obj, new TypeReference<>() {
        });
    }


    /**
     * KAKAO TOKEN 토큰(접근토큰, 갱신토큰)을 발급 받습니다.
     *
     * @param authCode 인증 코드
     * @return OAuth2TokenInfoDto
     * @refrence https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#get-token-info
     */
    private OAuth2TokenInfoDto getKakaoTokenInfo(String authCode) {
        log.debug("[+] getKakaoTokenInfo 함수가 실행 됩니다. :: {}", authCode);

        OAuth2TokenInfoDto resultDto = null;
        ResponseEntity<Map<String, Object>> responseTokenInfo = null;

        // [STEP1] 카카오 토큰 URL로 전송할 데이터 구성
        MultiValueMap<String, Object> requestParamMap = new LinkedMultiValueMap<>();
        requestParamMap.add("grant_type", "authorization_code");
        requestParamMap.add("client_id", oAuthRegistration.kakao().clientId());
        requestParamMap.add("redirect_uri", oAuthRegistration.kakao().redirectUri());
        requestParamMap.add("code", authCode);
        requestParamMap.add("client_secret", oAuthRegistration.kakao().clientSecret());
        HttpEntity<MultiValueMap<String, Object>> requestMap = new HttpEntity<>(requestParamMap, this.defaultHeader());

        try {
            // [STEP2] 카카오 토큰 URL로 RestTemplate 이용하여 데이터 전송
            responseTokenInfo = restTemplateConfig
                    .restTemplate()
                    .exchange(oAuthProvider.kakao().tokenUri(), HttpMethod.POST, requestMap, new ParameterizedTypeReference<>() {
                    });
        } catch (Exception e) {
            log.error("[-] 토큰 요청 중에 오류가 발생하였습니다. {}", e.getMessage());
        }
        // [STEP3] 토큰 반환 값 결과값으로 구성
        if (responseTokenInfo != null && responseTokenInfo.getBody() != null && responseTokenInfo.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> body = responseTokenInfo.getBody();
            if (body != null) {
                resultDto = OAuth2TokenInfoDto.builder()
                        .accessToken(body.get("access_token").toString())
                        .refreshToken(body.get("refresh_token").toString())
                        .tokenType(body.get("token_type").toString())
                        .build();
            }
        } else {
            log.error("[-] 토큰 정보가 존재하지 않습니다.");
        }
        return resultDto;
    }


    /**
     * 카카오 로그인 사용자 정보를 가져옵니다.
     *
     * @param accessToken
     * @return
     * @refrence https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info
     */
    private OAuth2KakaoUserInfoDto getKakaoUserInfo(String accessToken) {
        log.debug("[+] getKakaoUserInfo을 수행합니다 :: {}", accessToken);

        ResponseEntity<Map<String, Object>> responseUserInfo = null;
        OAuth2KakaoUserInfoDto resultDto = null;


        // [STEP1] 필수 요청 Header 값 구성 : ContentType, Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Authorization", "Bearer " + accessToken);        // accessToken 추가


        // [STEP2] 요청 파라미터 구성 : 원하는 사용자 정보
        MultiValueMap<String, Object> userInfoParam = new LinkedMultiValueMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userInfoParam.add("property_keys", objectMapper.writeValueAsString(oAuthRegistration.kakao().scope()));   // 불러올 데이터 조회 (리스트 to 문자열 변환)
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<MultiValueMap<String, Object>> userInfoReq = new HttpEntity<>(userInfoParam, headers);

        // [STEP3] 요청 Header, 파라미터를 포함하여 사용자 정보 조회 URL로 요청을 수행합니다.
        try {
            responseUserInfo = restTemplateConfig
                    .restTemplate()
                    .exchange(oAuthProvider.kakao().userInfoUri(), HttpMethod.POST, userInfoReq, new ParameterizedTypeReference<>() {
                    });
            log.debug("결과 값 :: {}", responseUserInfo);

        } catch (Exception e) {
            log.error("[-] 사용자 정보 요청 중에 오류가 발생하였습니다.{}", e.getMessage());
        }


        // [STEP4] 사용자 정보가 존재한다면 값을 불러와서 OAuth2KakaoUserInfoDto 객체로 구성하여 반환합니다.
        if (responseUserInfo != null && responseUserInfo.getBody() != null && responseUserInfo.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> body = responseUserInfo.getBody();

            if (body != null) {
                Map<String, Object> kakaoAccount = this.cvtObjectToMap(body.get("kakao_account"));
                Map<String, Object> profile = this.cvtObjectToMap(kakaoAccount.get("profile"));

                System.out.println("Profile :: " + profile);
                System.out.println("ID: " + body.get("id"));
                System.out.println("Status Code: " + responseUserInfo.getStatusCode().value());
                System.out.println("Profile Image URL: " + profile.get("profile_image_url"));
                System.out.println("Thumbnail Image URL: " + profile.get("thumbnail_image_url"));
                System.out.println("Email: " + kakaoAccount.get("email"));
                System.out.println("Nickname: " + profile.get("nickname"));
                System.out.println("Name: " + kakaoAccount.get("name"));
                System.out.println("Age Range: " + kakaoAccount.get("age_range"));
                System.out.println("Birthday: " + kakaoAccount.get("birthday"));
                System.out.println("Gender: " + kakaoAccount.get("gender"));

                resultDto = OAuth2KakaoUserInfoDto.builder()
                        .id(Optional.ofNullable(body.get("id")).map(Object::toString).orElse(""))
                        .statusCode(responseUserInfo.getStatusCode().value())
                        .nickname(Optional.ofNullable(profile.get("nickname")).map(Object::toString).orElse(""))
                        .profileImageUrl(Optional.ofNullable(profile.get("profile_image_url")).map(Object::toString).orElse(""))
                        .thumbnailImageUrl(Optional.ofNullable(profile.get("thumbnail_image_url")).map(Object::toString).orElse(""))
                        .email(Optional.ofNullable(kakaoAccount.get("email")).map(Object::toString).orElse(""))
                        .name(Optional.ofNullable(kakaoAccount.get("name")).map(Object::toString).orElse(""))
                        .ageRange(Optional.ofNullable(kakaoAccount.get("age_range")).map(Object::toString).orElse(""))
                        .birthday(Optional.ofNullable(kakaoAccount.get("birthday")).map(Object::toString).orElse(""))
                        .gender(Optional.ofNullable(kakaoAccount.get("gender")).map(Object::toString).orElse(""))
                        .build();

                log.debug("최종 구성 결과 :: {}", resultDto);

            }
        }
        return resultDto;
    }


    /**
     * 제공자(Kakao) 로그인을 수행하고 정보를 반환 받는 서비스입니다.
     *
     * @param authInfo
     * @return
     */
    @Override
    public OAuth2NaverUserInfoDto naverLogin(OAuth2AuthInfoDto authInfo) {

        OAuth2NaverUserInfoDto resultUserInfo = null;

        log.debug("[+] 네이버 로그인이 성공하여 리다이렉트 되었습니다.");
        log.debug("코드 값 확인2 : {}", authInfo.getCode());
        log.debug("에러 값 확인2 : {}", authInfo.getError());
        log.debug("에러 설명 값 확인2 : {}", authInfo.getErrorDescription());
        log.debug("상태 값 확인2 : {}", authInfo.getState());

        // [STEP1] 리다이렉트로 반환 받은 인증 코드의 존재여부를 체크합니다.
        if (authInfo.getCode() == null || authInfo.getCode().isEmpty()) {
            log.error("[-] 카카오 로그인 리다이렉션에서 문제가 발생하였습니다.");
            return null;
        }

        // [STEP2] 전달받은 인증코드를 기반으로 토큰정보를 조회합니다.
        OAuth2TokenInfoDto naverTokenInfo = this.getNaverTokenInfo(authInfo.getCode(), authInfo.getState());

        // [STEP3] 토큰 정보가 존재하는 경우 사용자 정보를 조회합니다.
        // [STEP3] 접근 토큰을 조회합니다.
        String accessToken = naverTokenInfo.getAccessToken();
        String refreshToken = naverTokenInfo.getRefreshToken();
        log.debug("naverTokenInfo :: {} ,  {}", accessToken, refreshToken);

        resultUserInfo = this.getNaverUserInfo(accessToken);

        return resultUserInfo;
    }

    /**
     * NAVER TOKEN 토큰(접근토큰, 갱신토큰)을 발급 받습니다.
     *
     * @param authCode 인증 코드
     * @return OAuth2TokenInfoDto 토큰 결과값
     * @refrence : https://developers.naver.com/docs/login/api/api.md#1--%EC%A4%80%EB%B9%84%EC%82%AC%ED%95%AD
     */
    private OAuth2TokenInfoDto getNaverTokenInfo(String authCode, String state) {
        log.debug("[+] getNaverTokenInfo 함수가 실행 됩니다. :: {}", authCode);
        OAuth2TokenInfoDto resultDto = null;
        ResponseEntity<Map<String, Object>> responseTokenInfo = null;

        // [STEP1] 네이버 토큰 URL로 전송할 데이터 구성
        MultiValueMap<String, String> requestParamMap = new LinkedMultiValueMap<>();
        requestParamMap.add("grant_type", "authorization_code");                          // 인증 과정에 대한 구분값: 1. 발급:'authorization_code', 2. 갱신:'refresh_token', 3. 삭제: 'delete'
        requestParamMap.add("client_id", oAuthRegistration.naver().clientId());           // 애플리케이션 등록 시 발급받은 Client ID 값
        requestParamMap.add("client_secret", oAuthRegistration.naver().clientSecret());   // 애플리케이션 등록 시 발급받은 Client secret 값
        requestParamMap.add("code", authCode);                                            // 로그인 인증 요청 API 호출에 성공하고 리턴받은 인증코드값 (authorization code)
        requestParamMap.add("state", state);                                              // 사이트 간 요청 위조(cross-site request forgery) 공격을 방지하기 위해 애플리케이션에서 생성한 상태 토큰값으로 URL 인코딩을 적용한 값을 사용
        requestParamMap.add("redirect_uri", oAuthRegistration.naver().redirectUri());     // 애플리케이션 등록 시 발급받은 Client secret 값
        HttpEntity<MultiValueMap<String, String>> requestMap = new HttpEntity<>(requestParamMap, this.defaultHeader());

        try {
            // [STEP2] 네이버 토큰 URL로 RestTemplate 이용하여 데이터 전송
            responseTokenInfo = restTemplateConfig
                    .restTemplate()
                    .exchange(oAuthProvider.naver().tokenUri(), HttpMethod.POST, requestMap, new ParameterizedTypeReference<>() {
                    });

            log.debug("네이버 로그인 결과 :: {}", responseTokenInfo);
        } catch (Exception e) {
            log.error("[-] 토큰 요청 중에 오류가 발생하였습니다.{}", e.getMessage());
        }

        // [STEP3] 토큰 반환 값 결과값으로 구성
        if (responseTokenInfo != null && responseTokenInfo.getBody() != null && responseTokenInfo.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> body = responseTokenInfo.getBody();
            if (body != null) {
                resultDto = OAuth2TokenInfoDto.builder()
                        .accessToken(body.get("access_token").toString())
                        .refreshToken(body.get("refresh_token").toString())
                        .tokenType(body.get("token_type").toString())
                        .expiresIn(body.get("expires_in").toString())
                        .build();
            }

        } else {
            log.error("[-] 토큰 정보가 존재하지 않습니다.");
        }
        log.debug("최종 결과 값을 확인합니다 : {}", resultDto.toString());

        return resultDto;
    }


    /**
     * Naver의 사용자 정보를 조회합니다.
     *
     * @param accessToken
     * @return
     * @refrence https://developers.naver.com/docs/login/profile/profile.md
     */
    private OAuth2NaverUserInfoDto getNaverUserInfo(String accessToken) {
        log.debug("[+] getNaverUserInfo 함수를 수행합니다 :: {}", accessToken);

        ResponseEntity<Map<String, Object>> responseUserInfo = null;
        OAuth2NaverUserInfoDto resultDto = null;


        // [STEP1] 필수 요청 Header 값 구성 : ContentType, Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessToken);        // accessToekn 추가

        // [STEP2] 요청 파라미터 구성 : 별도의 요청정보는 없음.
        MultiValueMap<String, Object> userInfoParam = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> userInfoReq = new HttpEntity<>(userInfoParam, headers);
        log.debug("요청 값 :: {}", userInfoReq);

        // [STEP3] 요청 Header, 파라미터를 포함하여 사용자 정보 조회 URL로 요청을 수행합니다.
        try {
            responseUserInfo = restTemplateConfig
                    .restTemplate()
                    .exchange(oAuthProvider.naver().userInfoUri(), HttpMethod.POST, userInfoReq, new ParameterizedTypeReference<>() {
                    });

        } catch (Exception e) {
            log.error("[-] 사용자 정보 요청 중에 오류가 발생하였습니다.{}", e.getMessage());
        }

        log.debug("사용자 조회 :: {}", responseUserInfo);

        // [STEP4] 사용자 정보가 존재한다면 값을 불러와서 OAuth2NaverUserInfoDto 객체로 구성하여 반환합니다.
        if (responseUserInfo != null && responseUserInfo.getBody() != null && responseUserInfo.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> body = responseUserInfo.getBody();
            if (body != null && body.get("response") != null) {
                Map<String, Object> resBody = this.cvtObjectToMap(body.get("response"));
                resultDto = OAuth2NaverUserInfoDto.builder()
                        .resultCode(body.get("resultcode").toString())
                        .message(body.get("message").toString())
                        .response(
                                OAuth2NaverUserInfoDto.NaverUserResponse
                                        .builder()
                                        .id(resBody.get("id").toString())
                                        .email(resBody.get("email").toString())
                                        .name(resBody.get("name").toString())
                                        .nickname(resBody.get("nickname").toString())
                                        .build())
                        .build();
                log.debug("userInfo :: {}", resultDto);
            }
        }
        return resultDto;
    }
}
