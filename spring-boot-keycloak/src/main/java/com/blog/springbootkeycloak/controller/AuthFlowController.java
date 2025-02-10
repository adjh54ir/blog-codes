package com.blog.springbootkeycloak.controller;

import com.blog.springbootkeycloak.dto.AccessTokenReqDto;
import com.blog.springbootkeycloak.dto.AccessTokenResDto;
import com.blog.springbootkeycloak.dto.AuthCodeDto;
import com.blog.springbootkeycloak.dto.KeycloakUserDto;
import com.blog.springbootkeycloak.service.feign.KeycloakAuthFeignClient;
import com.blog.springbootkeycloak.service.feign.SubApiCallFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OIDC 인증 플로우 구성
 *
 * @author : jonghoon
 * @fileName : AuthFlowController
 * @since : 25. 1. 28.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/keycloak/authFlow")
public class AuthFlowController {

    private final KeycloakAuthFeignClient keycloakAuthFeignClient;
    private final SubApiCallFeignClient subApiCallFeignClient;

    /**
     * Direct Access Grants Flow : 토큰을 즉시 요청하는 방법
     *
     * @param accessTokenReqDto 전송 객체
     * @return 토큰 값 반환
     */
    @PostMapping("/directAccess")
    public ResponseEntity<Object> getDirectAccessToken(@RequestBody AccessTokenReqDto accessTokenReqDto) {
        try {
            AccessTokenResDto resultToken = keycloakAuthFeignClient.getAccessToken(accessTokenReqDto);
            return new ResponseEntity<>(resultToken, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Token request failed", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Standard Flow : 로그인 페이지 출력 및 로그인
     *
     * @param response_type
     * @param client_id
     * @param redirect_uri
     * @param scope
     * @param state
     * @return
     */
    @GetMapping("/standardFlow")
    public ResponseEntity<Object> getStandardFlow(
            @RequestParam String response_type,
            @RequestParam String client_id,
            @RequestParam String redirect_uri,
            @RequestParam(required = false) String scope,
            @RequestParam(required = false) String state
    ) {
        AuthCodeDto authCodeDto = AuthCodeDto.builder()
                .response_type(response_type)
                .client_id(client_id)
                .redirect_uri(redirect_uri)
                .scope(scope)
                .state(state)
                .build();
        try {
            String authorizationUrl = keycloakAuthFeignClient.getAuthCode(authCodeDto);
            return new ResponseEntity<>(authorizationUrl, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Standard flow failed", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Client Credentials : 토큰 발급
     *
     * @return
     */
    @PostMapping("/clientCredentials")
    public ResponseEntity<Object> callProtectedApi(@RequestBody AccessTokenReqDto accessTokenReqDto) {
        try {

            AccessTokenResDto resultToken = keycloakAuthFeignClient.getAccessToken(accessTokenReqDto);      // Keycloak 통신 : 접근 토큰 발급
            String accessToken = resultToken.getAccess_token();

            // 토큰 생성 실패 시
            if (accessToken.isEmpty()) {
                log.error("Token is empty");
                return new ResponseEntity<>("", HttpStatus.OK);
            }

            // 토큰 생성 성공 => 전달
            List<KeycloakUserDto> getUser = subApiCallFeignClient.sendAccessTokenToSubApi(accessToken);             // 외부 서비스 통신 : 접근 토큰 전달
            log.debug("성공적으로 전달되었는가 ? : {}", getUser);
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Token request failed", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
