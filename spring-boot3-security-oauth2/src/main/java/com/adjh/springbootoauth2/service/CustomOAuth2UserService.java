package com.adjh.springbootoauth2.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CustomOAuth2UserService
 * @since : 11/1/24
 */
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 여기서 사용자 정보를 처리하고 필요한 경우 데이터베이스에 저장
        return super.loadUser(userRequest);
    }

}
