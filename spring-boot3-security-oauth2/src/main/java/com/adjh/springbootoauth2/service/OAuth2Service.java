package com.adjh.springbootoauth2.service;

import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoResDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverResDto;

/**
 * OAuth 2.0 기반 로그인 수행
 *
 * @author : jonghoon
 * @fileName : OAuth2Service
 * @since : 11/1/24
 */
public interface OAuth2Service {

    public LoginKakaoResDto kakaoLogin(LoginKakaoReqDto loginKakaoReqDto);

    public LoginNaverResDto naverLogin(LoginNaverReqDto loginNaverReqDto);
}
