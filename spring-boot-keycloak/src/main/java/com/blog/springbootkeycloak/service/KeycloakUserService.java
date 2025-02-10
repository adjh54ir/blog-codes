package com.blog.springbootkeycloak.service;

import com.blog.springbootkeycloak.config.properties.KeycloakProperties;
import com.blog.springbootkeycloak.dto.AccessTokenReqDto;
import com.blog.springbootkeycloak.dto.AccessTokenResDto;
import com.blog.springbootkeycloak.dto.TokenIntrospectionReqDto;
import com.blog.springbootkeycloak.dto.TokenIntrospectionResDto;
import com.blog.springbootkeycloak.service.feign.KeycloakAuthFeignClient;
import com.blog.springbootkeycloak.service.feign.KeycloakUserFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Keycloak 비즈니스 관리를 수행합니다.
 *
 * @author : leejonghoon
 * @fileName : KeycloakUserService
 * @since : 2025. 2. 10.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakUserService {

    private final KeycloakProperties properties;                    // Keycloak 설정 정보
    private final KeycloakUserFeignClient keycloakUserFeignClient;  // Keycloak User 통신
    private final KeycloakAuthFeignClient keycloakAuthFeignClient;  // Keycloak Auth 통신


    /**
     * Keycloak 사용자를 조회합니다.
     *
     * @param bearerToken
     * @return
     */
    public List<UserRepresentation> getKeycloakUsers(String bearerToken) {
        this.validateToken(bearerToken);        // 토큰 유효성 체크
        // 2. [Keycloak] 사용자 리스트 조회
        return keycloakUserFeignClient.getKeycloakUsers(bearerToken);
    }

    /**
     * 사용자 상세 조회
     *
     * @param bearerToken
     * @param userId
     * @return
     */
    public UserRepresentation getKeycloakUserDetail(String bearerToken, String userId) {
        this.validateToken(bearerToken);        // 토큰 유효성 체크
        return keycloakUserFeignClient.getKeycloakUserDetail(bearerToken, userId);
    }

    /**
     * 사용자 등록
     *
     * @param bearerToken
     * @param userRepresentation
     */
    public void createUser(String bearerToken, UserRepresentation userRepresentation) {
        this.validateToken(bearerToken);        // 토큰 유효성 체크
        keycloakUserFeignClient.createUser(bearerToken, userRepresentation);
    }

    /**
     * 사용자 수정
     *
     * @param bearerToken
     * @param userId
     * @param userRepresentation
     */
    public void updateUser(String bearerToken, String userId, UserRepresentation userRepresentation) {
        this.validateToken(bearerToken);        // 토큰 유효성 체크
        keycloakUserFeignClient.updateUser(bearerToken, userId, userRepresentation);
    }

    /**
     * 사용자 삭제
     *
     * @param bearerToken
     * @param userId
     */
    public void deleteUser(String bearerToken, String userId) {
        this.validateToken(bearerToken);        // 토큰 유효성 체크
        keycloakUserFeignClient.deleteUser(bearerToken, userId);
    }


    /**
     * Optional 한 사용자 조회
     *
     * @param bearerToken
     * @param email
     * @param emailVerified
     * @param enabled
     * @param firstName
     * @param lastName
     * @param q
     * @param search
     * @param username
     * @return
     */
    public List<UserRepresentation> getKeycloakFilterUsers(String bearerToken, String email, Boolean emailVerified, Boolean enabled, String firstName, String lastName, String q, String search, String username) {
        this.validateToken(bearerToken);        // 토큰 유효성 체크
        return keycloakUserFeignClient.getKeycloakFilterUsers(bearerToken, email, emailVerified, enabled, firstName, lastName, q, search, username);
    }

    /**
     * TODO: 삭제 예정 - 직접 발급 예정임.
     * Direct Access Grants 방법을 통해서 아이디/비밀번호로 접근 토큰을 발급받습니다.
     *
     * @return
     */
    private String getTempAccessToken() {
        AccessTokenReqDto accessTokenReqDto = AccessTokenReqDto.builder()
                .grant_type("password")
                .client_id("spring-boot-app")
                .client_secret("WnmQEGAOzEJ7Kyr2UCJha5AXKCjwnGpB")
                .username("subAdmin")
                .password("qwer1234")
                .build();
        AccessTokenResDto resultToken = keycloakAuthFeignClient.getAccessToken(accessTokenReqDto);
        return resultToken.getAccess_token();
    }


    /**
     * 토큰 유효성을 검사하고 예외를 발생시킵니다.
     *
     * @param bearerToken
     */
    private void validateToken(String bearerToken) {
        if (!isValidToken(bearerToken)) {
            log.error("토큰이 유효하지 않습니다.");
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }


    /**
     * 토큰이 유효한지 체크를 수행합니다.
     *
     * @param bearerToken
     * @return
     */
    private boolean isValidToken(String bearerToken) {
        TokenIntrospectionReqDto tokenIntrospectionReqDto = TokenIntrospectionReqDto.builder()
                .token(bearerToken)
                .client_id(properties.getResource())
                .client_secret(properties.getCredentials().getSecret())
                .build();
        TokenIntrospectionResDto validTokenDto = keycloakAuthFeignClient.tokenIntrospect(tokenIntrospectionReqDto);      // Keycloak : 토큰 유효성 검증

        return validTokenDto.getActive();
    }


}
