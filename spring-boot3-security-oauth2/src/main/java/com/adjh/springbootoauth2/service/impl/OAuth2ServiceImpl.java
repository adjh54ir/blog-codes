package com.adjh.springbootoauth2.service.impl;

import com.adjh.springbootoauth2.config.RestTemplateConfig;
import com.adjh.springbootoauth2.config.properties.OAuth2ClientPropertiesSource;
import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverResDto;
import com.adjh.springbootoauth2.config.properties.OAuth2ClientProperties;
import com.adjh.springbootoauth2.service.OAuth2Service;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
    private final OAuth2ClientProperties oAuth2ClientProperties;

    public OAuth2ServiceImpl(RestTemplateConfig restTemplateConfig, OAuth2ClientProperties properties) {
        this.restTemplateConfig = restTemplateConfig;
        this.oAuth2ClientProperties = properties;
    }

    private String KAKAO_TOKEN_URL;
    private String KAKAO_USER_INFO_URL;
    private String KAKAO_USER_NAME_ATTRIBUTE;
    private String KAKAO_CLIENT_ID;
    private String KAKAO_CLIENT_SECRET;
    private String KAKAO_REDIRECT_URL;
    private String NAVER_TOKEN_URL;
    private String NAVER_USER_INFO_URL;
    private String NAVER_USER_NAME_ATTRIBUTE;
    private String NAVER_CLIENT_ID;
    private String NAVER_CLIENT_SECRET;
    private String NAVER_REDIRECT_URL;

    @PostConstruct
    public void init() {
        KAKAO_TOKEN_URL = oAuth2ClientProperties.getProvider().get("kakao").getTokenUri();
        KAKAO_USER_INFO_URL = oAuth2ClientProperties.getProvider().get("kakao").getUserInfoUri();
        KAKAO_USER_NAME_ATTRIBUTE = oAuth2ClientProperties.getProvider().get("kakao").getUserNameAttribute();
        KAKAO_CLIENT_ID = oAuth2ClientProperties.getRegistration().get("kakao").getClientId();
        KAKAO_CLIENT_SECRET = oAuth2ClientProperties.getRegistration().get("kakao").getClientSecret();
        KAKAO_REDIRECT_URL = oAuth2ClientProperties.getRegistration().get("kakao").getRedirectUri();
        NAVER_TOKEN_URL = oAuth2ClientProperties.getProvider().get("naver").getTokenUri();
        NAVER_USER_INFO_URL = oAuth2ClientProperties.getProvider().get("naver").getUserInfoUri();
        NAVER_USER_NAME_ATTRIBUTE = oAuth2ClientProperties.getProvider().get("naver").getUserNameAttribute();
        NAVER_CLIENT_ID = oAuth2ClientProperties.getRegistration().get("naver").getClientId();
        NAVER_CLIENT_SECRET = oAuth2ClientProperties.getRegistration().get("naver").getClientSecret();
        NAVER_REDIRECT_URL = oAuth2ClientProperties.getRegistration().get("naver").getRedirectUri();
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
     * @param loginKakaoReqDto
     * @return
     */
    @Override
    public LoginKakaoReqDto kakaoLogin(LoginKakaoReqDto loginKakaoReqDto) {
//        log.debug("[+] 카카오 로그인이 성공하여 리다이렉트 되었습니다.");
//        log.debug("코드 값 확인 : {}", loginKakaoReqDto.getCode());
//        log.debug("에러 값 확인 : {}", loginKakaoReqDto.getError());
//        log.debug("에러 설명 값 확인 : {}", loginKakaoReqDto.getErrorDescription());
//        log.debug("상태 값 확인 : {}", loginKakaoReqDto.getState());

        log.debug("KAKAO_TOKEN_URL: {}", KAKAO_TOKEN_URL);
        log.debug("KAKAO_USER_INFO_URL: {}", KAKAO_USER_INFO_URL);
        log.debug("KAKAO_USER_NAME_ATTRIBUTE: {}", KAKAO_USER_NAME_ATTRIBUTE);
        log.debug("KAKAO_CLIENT_ID: {}", KAKAO_CLIENT_ID);
        log.debug("KAKAO_CLIENT_SECRET: {}", KAKAO_CLIENT_SECRET);
        log.debug("KAKAO_REDIRECT_URL: {}", KAKAO_REDIRECT_URL);
        log.debug("NAVER_TOKEN_URL: {}", NAVER_TOKEN_URL);
        log.debug("NAVER_USER_INFO_URL: {}", NAVER_USER_INFO_URL);
        log.debug("NAVER_USER_NAME_ATTRIBUTE: {}", NAVER_USER_NAME_ATTRIBUTE);
        log.debug("NAVER_CLIENT_ID: {}", NAVER_CLIENT_ID);
        log.debug("NAVER_CLIENT_SECRET: {}", NAVER_CLIENT_SECRET);
        log.debug("NAVER_REDIRECT_URL: {}", NAVER_REDIRECT_URL);


        if (loginKakaoReqDto.getCode() == null || loginKakaoReqDto.getCode().isEmpty()) {
            log.error("[-] 카카오 로그인 리다이렉션에서 문제가 발생하였습니다.");
            return null;
        }

        // [STEP2] 카카오의 토큰 정보들을 반환받습니다.
        Map<String, Object> kakaoTokenInfo = this.getKakaoTokenInfo(loginKakaoReqDto.getCode());
        String accessToken = kakaoTokenInfo.get("accessToken").toString();

        if (!accessToken.isEmpty()) {
            log.error("[-] 카카오 로그인 토큰 정보를 조회하는 중에 문제가 발생하였습니다.");
            return null;
        }

        // [STEP3] 카카오로부터 받은 토큰이 존재하는 경우만 수행
        Map<String, Object> userInfo = this.getKakaoUserInfo(accessToken);
        log.debug("userInfo :: {}", userInfo);


        if (userInfo == null) {
            log.error("[-] 카카오 사용자 조회하는 중에 문제가 발생하였습니다.");
            return null;
        }

        Map<String, Object> properties = (Map<String, Object>) userInfo.get("properties");
        log.debug("properties :: {}", properties);

        Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");
        log.debug("kakaoAccount :: {}", kakaoAccount);

        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        log.debug("profile :: {}", profile);
        return null;
    }


    /**
     * KAKAO TOKEN 토큰(접근토큰, 갱신토큰)을 발급 받습니다.
     *
     * @param authCode
     * @return
     * @refrence https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#get-token-info
     */
    private Map<String, Object> getKakaoTokenInfo(String authCode) {
        log.debug("[+] getKakaoTokenInfo 함수가 실행 됩니다. :: {}", authCode);

        Map<String, Object> resultMap = new HashMap<>();

        MultiValueMap<String, String> tokenReqParamsMap = new LinkedMultiValueMap<>();
        tokenReqParamsMap.add("grant_type", "authorization_code");
        tokenReqParamsMap.add("client_id", KAKAO_CLIENT_ID);
        tokenReqParamsMap.add("redirect_uri", KAKAO_REDIRECT_URL);
        tokenReqParamsMap.add("code", authCode);
        tokenReqParamsMap.add("client_secret", KAKAO_CLIENT_SECRET);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(tokenReqParamsMap, this.defaultHeader());

        ResponseEntity<Map<String, Object>> tokenResponse = restTemplateConfig
                .restTemplate()
                .exchange(KAKAO_TOKEN_URL, HttpMethod.POST, request, new ParameterizedTypeReference<>() {
                });

        // 응답에 성공을 하는 경우
        if (tokenResponse.getStatusCode().value() == 200 && tokenResponse.getBody() != null) {
            Map<String, Object> resultBody = tokenResponse.getBody();
            try {
                resultMap.put("accessToken", resultBody.getOrDefault("access_token", "").toString());
                resultMap.put("refreshToken", resultBody.getOrDefault("refresh_token", "").toString());
                resultMap.put("tokenType", resultBody.getOrDefault("token_type", "").toString());
            } catch (NullPointerException e) {
                log.error("exception :: {}", e.getMessage());
            }
        }
        return resultMap;
    }


    /**
     * 카카오 로그인 사용자 정보를 가져옵니다.
     *
     * @param accessToken
     * @return
     * @refrence https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info
     */
    private Map<String, Object> getKakaoUserInfo(String accessToken) {
        log.debug("[+] getKakaoUserInfo을 수행합니다 :: {}", accessToken);
        MultiValueMap<String, Object> userInfoParam = new LinkedMultiValueMap<>();
        ResponseEntity<Map<String, Object>> response = null;
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            headers.add("Authorization", "Bearer " + accessToken);        // accessToekn 추가
            HttpEntity<MultiValueMap<String, Object>> userInfoReq = new HttpEntity<>(userInfoParam, headers);
            log.debug("요청 값 :: {}", userInfoReq);
            response = restTemplateConfig
                    .restTemplate()
                    .exchange(KAKAO_USER_INFO_URL, HttpMethod.POST, userInfoReq, new ParameterizedTypeReference<>() {
                    });
            log.debug("사용자 조회 :: {}", response);
        } catch (Exception e) {
            log.error("Error :: {}", e.getMessage());
        }
        return response != null ? response.getBody() : null;
    }


    /**
     * 제공자(Kakao) 로그인을 수행하고 정보를 반환 받는 서비스입니다.
     *
     * @param loginNaverReqDto
     * @return
     */
    @Override
    public LoginNaverResDto naverLogin(LoginNaverReqDto loginNaverReqDto) {
        log.debug("[+] 네이버 로그인이 성공하여 리다이렉트 되었습니다.");

        log.debug("코드 값 확인2 : {}", loginNaverReqDto.getCode());
        log.debug("에러 값 확인2 : {}", loginNaverReqDto.getError());
        log.debug("에러 설명 값 확인2 : {}", loginNaverReqDto.getErrorDescription());
        log.debug("상태 값 확인2 : {}", loginNaverReqDto.getState());

        // [STEP1] Naver 로그인 코드를 받은 경우 수행이 됩니다.
        if (loginNaverReqDto.getCode() != null) {

            // [STEP2] 전달받은 인증코드를 기반으로 토큰정보를 조회합니다.
            Map<String, Object> naverTokenInfo = this.getNaverTokenInfo(loginNaverReqDto.getCode(), loginNaverReqDto.getState());

            // [STEP3] 접근 토큰을 조회합니다.
            String accessToken = naverTokenInfo.get("accessToken").toString();
            log.debug("naverTokenInfo :: {}", accessToken);

            // [STEP4] 접근 토큰이 존재하는 경우 이후 '사용자 정보'를 조회합니다.
            if (accessToken != null) {

                Map<String, Object> userInfo = this.getNaverUserInfo(accessToken);
                log.debug("사용자 정보를 출력합니다 :: {}", userInfo);

                String resultCode = userInfo.get("resultcode").toString();
                String message = userInfo.get("message").toString();

                if (resultCode.equals("00") && message.equals("success")) {
                    Map<String, Object> response = (Map<String, Object>) userInfo.get("response");
                    String id = response.get("id").toString();
                    String nickname = response.get("nickname").toString();
                    String email = response.get("email").toString();
                    String name = response.get("name").toString();
                }
            } else {
                log.error("Naver 로그인 접근 토큰(Access Token)을 받지 못하였습니다. :: {}", loginNaverReqDto.getError());
            }

        } else {
            log.error("Naver 로그인 인증 코드(Auth Code)를 받지 못하였습니다. :: {}", loginNaverReqDto.getError());
        }
        return null;
    }

    /**
     * NAVER TOKEN 토큰(접근토큰, 갱신토큰)을 발급 받습니다.
     *
     * @param authCode
     * @param state
     * @return
     * @refrence : https://developers.naver.com/docs/login/api/api.md#1--%EC%A4%80%EB%B9%84%EC%82%AC%ED%95%AD
     */
    private Map<String, Object> getNaverTokenInfo(String authCode, String state) {
        log.debug("[+] getNaverTokenInfo 함수가 실행 됩니다. :: {}", authCode);
        Map<String, Object> resultMap = new HashMap<>();

        MultiValueMap<String, String> tokenReqParamsMap = new LinkedMultiValueMap<>();
        tokenReqParamsMap.add("grant_type", "authorization_code");          // 인증 과정에 대한 구분값: 1. 발급:'authorization_code', 2. 갱신:'refresh_token', 3. 삭제: 'delete'
        tokenReqParamsMap.add("client_id", NAVER_CLIENT_ID);                // 애플리케이션 등록 시 발급받은 Client ID 값
        tokenReqParamsMap.add("client_secret", NAVER_CLIENT_SECRET);        // 애플리케이션 등록 시 발급받은 Client secret 값
        tokenReqParamsMap.add("code", authCode);                            // 로그인 인증 요청 API 호출에 성공하고 리턴받은 인증코드값 (authorization code)
        tokenReqParamsMap.add("state", state);                              // 사이트 간 요청 위조(cross-site request forgery) 공격을 방지하기 위해 애플리케이션에서 생성한 상태 토큰값으로 URL 인코딩을 적용한 값을 사용
        tokenReqParamsMap.add("redirect_uri", NAVER_REDIRECT_URL);          // 애플리케이션 등록 시 발급받은 Client secret 값
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(tokenReqParamsMap, this.defaultHeader());

        ResponseEntity<Map<String, Object>> tokenResponse = restTemplateConfig
                .restTemplate()
                .exchange(NAVER_TOKEN_URL, HttpMethod.POST, request, new ParameterizedTypeReference<>() {
                });

        // 응답에 성공을 하는 경우
        if (tokenResponse.getStatusCode().value() == 200 && tokenResponse.getBody() != null) {
            Map<String, Object> resultBody = tokenResponse.getBody();
            try {
                resultMap.put("accessToken", resultBody.getOrDefault("access_token", "").toString());
                resultMap.put("refreshToken", resultBody.getOrDefault("refresh_token", "").toString());
                resultMap.put("tokenType", resultBody.getOrDefault("token_type", "").toString());
                resultMap.put("expiresIn", resultBody.getOrDefault("expires_in", "").toString());
                resultMap.put("error", resultBody.getOrDefault("error", "").toString());
                resultMap.put("errorDescription", resultBody.getOrDefault("error_description", "").toString());
            } catch (NullPointerException e) {
                log.error("exception :: {}", e.getMessage());
            }
        }
        log.debug("최종 결과 값을 확인합니다 : {}", resultMap.toString());

        return resultMap;
    }

    private Map<String, Object> getNaverUserInfo(String accessToken) {
        log.debug("[+] getNaverUserInfo 함수를 수행합니다 :: {}", accessToken);

        MultiValueMap<String, Object> userInfoParam = new LinkedMultiValueMap<>();
        ResponseEntity<Map<String, Object>> response = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Bearer " + accessToken);        // accessToekn 추가
            HttpEntity<MultiValueMap<String, Object>> userInfoReq = new HttpEntity<>(userInfoParam, headers);
            log.debug("요청 값 :: {}", userInfoReq);
            response = restTemplateConfig
                    .restTemplate()
                    .exchange(NAVER_USER_INFO_URL, HttpMethod.POST, userInfoReq, new ParameterizedTypeReference<>() {
                    });
            log.debug("사용자 조회 :: {}", response);
        } catch (Exception e) {
            log.error("Error :: {}", e.getMessage());
        }
        return response != null ? response.getBody() : null;
    }
}
