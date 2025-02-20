package com.blog.springbootkeycloak.service.feign;

import com.blog.springbootkeycloak.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * OpenFeign 통해서 Keycloak 서비스 통신을 수행합니다.
 *
 * @author : jonghoon
 * @fileName : KeycloakAuthService
 * @since : 11/23/24
 */
@FeignClient(
        name = "keycloak-auth-client",
        url = "http://localhost:9001/realms/dev-realm/protocol/openid-connect",
        configuration = CustomFeignClientConfig.class
)
@Service
public interface KeycloakAuthFeignClient {
    /**
     * 인가 코드(Authentication Code) 발급
     *
     * @param authCodeDto 전송 객체 값 (form 데이터 형태로 전송)
     * @return 토큰 값 반환
     */
    @GetMapping(value = "/auth", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String getAuthCode(@ModelAttribute AuthCodeDto authCodeDto);


    /**
     * 접근 토큰(Access Token) 발급
     *
     * @param accessTokenReqDto 전송 객체 값 (form 데이터 형태로 전송)
     * @return 토큰 값 반환
     */
    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AccessTokenResDto getAccessToken(@ModelAttribute AccessTokenReqDto accessTokenReqDto);

    /**
     * 접근 토큰(Access Token) 유효성 검증
     *
     * @param tokenIntrospectionReqDto
     * @return
     */
    @PostMapping(value = "/token/introspect", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenIntrospectionResDto tokenIntrospect(@ModelAttribute TokenIntrospectionReqDto tokenIntrospectionReqDto);
}
