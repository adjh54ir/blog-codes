package com.adjh.springbootkeycloaksub.controller;

import com.adjh.springbootkeycloaksub.config.properties.KeycloakProperties;
import com.adjh.springbootkeycloaksub.dto.KeycloakUserDto;
import com.adjh.springbootkeycloaksub.dto.TokenIntrospectionReqDto;
import com.adjh.springbootkeycloaksub.dto.TokenIntrospectionResDto;
import com.adjh.springbootkeycloaksub.service.KeycloakAdminService;
import com.adjh.springbootkeycloaksub.service.KeycloakService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : ReceiveController
 * @since : 2025. 1. 31.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/keycloak/receive")
public class ReceiveController {

    private final KeycloakService keycloakService;
    private final KeycloakAdminService keycloakAdminService;
    private final KeycloakProperties properties;

    private final Keycloak keycloak;

    /**
     * Direct Access Grants Flow : 토큰을 즉시 요청하는 방법
     *
     * @param bearerToken 전송 객체
     * @return 토큰 값 반환
     */
    @GetMapping("/token")
    public List<KeycloakUserDto> receiveToken(@RequestHeader("Authorization") String bearerToken) {

        log.debug("파라미터로 전달받은 토큰 :: {} ", bearerToken);

        List<KeycloakUserDto> resultList = new ArrayList<>();

        // [Valid] Bearer 토큰여부 확인
        if (!bearerToken.isEmpty()) {

            // 1. [Keycloak] 토큰 유효성 검사
            TokenIntrospectionReqDto tokenIntrospectionReqDto = TokenIntrospectionReqDto.builder()
                    .token(bearerToken)
                    .client_id(properties.getSpringBootApp().getResource())
                    .client_secret(properties.getSpringBootApp().getCredentials().getSecret())
                    .build();
            TokenIntrospectionResDto validTokenDto = keycloakService.tokenIntrospect(tokenIntrospectionReqDto);      // Keycloak : 토큰 유효성 검증
            log.debug("토큰의 유효성 검증 :: {}", validTokenDto);

            // 2. 토큰 활성화 여부 확인
            if (validTokenDto.getActive()) {
                // 3. [Keycloak] 사용자 조회
                resultList = keycloakAdminService.getUserInfo("Bearer " + bearerToken);
                log.debug("사용자 정보 조회 :: {}", resultList);
            }

        }
        return resultList;
    }
}
