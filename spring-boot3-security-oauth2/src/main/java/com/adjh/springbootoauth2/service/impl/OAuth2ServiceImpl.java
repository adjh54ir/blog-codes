package com.adjh.springbootoauth2.service.impl;

import com.adjh.springbootoauth2.config.RestTemplateConfig;
import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoResDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverResDto;
import com.adjh.springbootoauth2.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuth2ServiceImpl
 * @since : 11/1/24
 */
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String authorizationUrl;


    /**
     * 제공자(Kakao) 로그인을 수행하고 정보를 반환 받는 서비스입니다.
     *
     * @param loginKakaoReqDto
     * @return
     */
    @Override
    public LoginKakaoResDto kakaoLogin(LoginKakaoReqDto loginKakaoReqDto) {
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
        String url = "https://example.com/api";
        HttpEntity<String> entity = null;
        restTemplateConfig.restTemplate().exchange(url, HttpMethod.POST, entity, String.class);
        return null;
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
