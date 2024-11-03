package com.adjh.springbootoauth2.service.impl;

import com.adjh.springbootoauth2.config.RestTemplateConfig;
import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoReqDto222;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverResDto;
import com.adjh.springbootoauth2.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuth2ServiceImpl
 * @since : 11/1/24
 */
@Slf4j
@Service("OAuth2ServiceImpl")
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    private final RestTemplateConfig restTemplateConfig;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String KAKAO_TOKEN_URL = "";

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String KAKAO_USER_INFO_URL = "";

    @Value("${spring.security.oauth2.client.provider.kakao.user-name-attribute}")
    private String KAKAO_USER_NAME_ATTRIBUTE = "";

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID = "";

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET = "";

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URL = "";

//    @Value("${spring.security.oauth2.client.registration.kakao.scope}")
//    private Set<String> KAKAO_USER_INFO_SCOPE;


    /**
     * 제공자(Kakao) 로그인을 수행하고 정보를 반환 받는 서비스입니다.
     *
     * @param loginKakaoReqDto
     * @return
     */
    @Override
    public LoginKakaoReqDto kakaoLogin(LoginKakaoReqDto loginKakaoReqDto) {
        log.debug("[+] 카카오 로그인이 성공하여 리다이렉트 되었습니다.");
        log.debug("코드 값 확인 : {}", loginKakaoReqDto.getCode());
        log.debug("에러 값 확인 : {}", loginKakaoReqDto.getError());
        log.debug("에러 설명 값 확인 : {}", loginKakaoReqDto.getErrorDescription());
        log.debug("상태 값 확인 : {}", loginKakaoReqDto.getState());


        // [STEP1] 리다이렉트로 반환 받는 코드가 존재하는 경우 수행
        if (loginKakaoReqDto.getCode() != null) {
            // [STEP2] 카카오의 토큰 정보들을 반환받습니다.
            Map<String, Object> kakaoTokenInfo = this.getKakaoTokenInfo(loginKakaoReqDto.getCode());
            String accessToken = kakaoTokenInfo.get("accessToken").toString();

            // [STEP3] 카카오로부터 받은 토큰이 존재하는 경우만 수행
            if (!accessToken.isEmpty()) {
                Map<String, Object> userInfo = this.getKakaoUserInfo(accessToken);
                log.debug("userInfo :: {}", userInfo);
            }


        }
        return null;
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
     * KAKAO TOKEN 토큰(접근토큰, 갱신토큰)을 발급 받습니다.
     *
     * @param code
     * @return
     */
    private Map<String, Object> getKakaoTokenInfo(String code) {
        log.debug("[+] getKakaoTokenInfo 함수가 실행 됩니다.", code);
        Map<String, Object> resultMap = new HashMap<>();

        MultiValueMap<String, String> tokenReqParamsMap = new LinkedMultiValueMap<>();
        tokenReqParamsMap.add("grant_type", "authorization_code");
        tokenReqParamsMap.add("client_id", KAKAO_CLIENT_ID);
        tokenReqParamsMap.add("redirect_uri", KAKAO_REDIRECT_URL);
        tokenReqParamsMap.add("code", code);
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
     * @param accessToken
     * @return
     */
    private Map<String, Object> getKakaoUserInfo(String accessToken) {
        log.debug("[+] getKakaoUserInfo을 수행합니다 :: {}", accessToken);
        MultiValueMap<String, Object> userInfoParam = new LinkedMultiValueMap<>();
//        List<String> list = Arrays.asList("name", "profile_nickname", "account_email");
//        userInfoParam.add("property_keys", list);
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
                            }
                    );
            log.debug("사용자 조회 :: {}", response);
        } catch (Exception e) {
            log.error("Error :: {}", e);
        }


        return response.getBody();
    }


    /**
     * 제공자(Kakao) 로그인을 수행하고 정보를 반환 받는 서비스입니다.
     *
     * @param loginNaverReqDto
     * @return
     */
    @Override
    public LoginNaverResDto naverLogin(LoginNaverReqDto loginNaverReqDto) {
        return null;
    }
}
