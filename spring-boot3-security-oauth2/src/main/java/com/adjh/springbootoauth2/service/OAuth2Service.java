package com.adjh.springbootoauth2.service;

import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoReqDto222;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverResDto;
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

    Object kakaoLogin(LoginKakaoReqDto loginKakaoReqDto);

    LoginNaverResDto naverLogin(LoginNaverReqDto loginNaverReqDto);
}
