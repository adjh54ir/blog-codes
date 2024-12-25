package com.adjh.springbootoauth2.service;

import com.adjh.springbootoauth2.dto.oauth2.OAuth2KakaoUserInfoDto;
import com.adjh.springbootoauth2.dto.oauth2.OAuth2NaverUserInfoDto;
import com.adjh.springbootoauth2.dto.oauth2.OAuth2AuthInfoDto;
import org.springframework.stereotype.Service;

/**
 * OAuth 2.0 기반 로그인 수행
 *
 * @author : jonghoon
 * @fileName : OAuth2Service
 * @since : 11/1/24
 */
@Service("OAuth2Service")
public interface OAuth2Service {

    OAuth2KakaoUserInfoDto kakaoLogin(OAuth2AuthInfoDto oAuth2AuthInfoDto);

    OAuth2NaverUserInfoDto naverLogin(OAuth2AuthInfoDto oAuth2AuthInfoDto);
}
