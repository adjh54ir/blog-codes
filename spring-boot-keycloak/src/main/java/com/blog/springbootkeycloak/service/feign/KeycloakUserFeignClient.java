package com.blog.springbootkeycloak.service.feign;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Keycloak 서버와 통신하는 OpenFeign
 *
 * @author : leejonghoon
 * @fileName : KeycloakUserFeignClient
 * @since : 2025. 2. 10.
 */
@FeignClient(
        name = "keycloak-user-service",
        url = "${keycloak.auth-server-url}/admin/realms/${keycloak.realm}"
)
@Service
public interface KeycloakUserFeignClient {
    /**
     * 전체 조회
     *
     * @param bearerToken
     * @return
     */
    @GetMapping("/users")
    List<UserRepresentation> getKeycloakUsers(@RequestHeader("Authorization") String bearerToken);

    /**
     * 전체 조회 중 필터링
     *
     * @param bearerToken
     * @return
     */
    @GetMapping("/users")
    List<UserRepresentation> getKeycloakUsers(@RequestHeader("Authorization") String bearerToken, @ModelAttribute UserRepresentation user);

    /**
     * 사용자 상세 조회
     */
    @GetMapping("/users/{id}")
    UserRepresentation getKeycloakUserDetail(@RequestHeader("Authorization") String bearerToken, @PathVariable String id);

    /**
     * 사용자 등록
     */
    @PostMapping("/users")
    void createUser(@RequestHeader("Authorization") String bearerToken, @RequestBody UserRepresentation userRepresentation);

    /**
     * 사용자 수정
     */
    @PutMapping("/users/{id}")
    void updateUser(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String id,
            @RequestBody UserRepresentation userRepresentation
    );

    /**
     * 사용자 삭제
     */
    @DeleteMapping("/users/{id}")
    void deleteUser(@RequestHeader("Authorization") String bearerToken, @PathVariable String id);

    /**
     * 비밀번호 재설정
     *
     * @param bearerToken
     * @param credentialRepresentation
     */
    @PutMapping("users/{id}/reset-password")
    void resetPassword(@RequestHeader("Authorization") String bearerToken, @RequestBody CredentialRepresentation credentialRepresentation);
}
