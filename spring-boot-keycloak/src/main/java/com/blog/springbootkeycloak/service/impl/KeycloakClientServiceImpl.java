package com.blog.springbootkeycloak.service.impl;

import com.blog.springbootkeycloak.service.KeycloakClientService;
import com.blog.springbootkeycloak.service.SubApiCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakClientService
 * @since : 2025. 1. 31.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakClientServiceImpl implements KeycloakClientService {

    private final Keycloak keycloak;
    private final SubApiCallService subApiCallService;

    /**
     * Keycloak Server 통신을 통해 AccessToken을 즉시 발급 받습니다.
     *
     * @return
     */
    @Override
    public String getAccessToken() {
        try {
            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
            return tokenResponse.getToken();
        } catch (Exception e) {
            log.error("Failed to get access token", e);
            throw new RuntimeException("Token acquisition failed");
        }
    }

    /**
     * 발급받은 토큰을 기반으로 Sub API 서버로 통신을 수행합니다.
     *
     * @return
     */
    @Override
    public String callProtectedApi() {
        String token = getAccessToken();
        String apiResponse = subApiCallService.sendAccessToken(token);

        log.debug("나와라 얍 :: {}", apiResponse);
        // token을 사용하여 보호된 API 호출
        // 예: RestTemplate 또는 WebClient 사용
        return apiResponse;
    }
}
