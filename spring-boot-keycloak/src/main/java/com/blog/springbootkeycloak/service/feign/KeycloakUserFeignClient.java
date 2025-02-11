package com.blog.springbootkeycloak.service.feign;

import com.blog.springbootkeycloak.dto.KeycloakUserResetPwDto;
import feign.QueryMap;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * 전체 조회 및 필터링
     *
     * @param bearerToken
     * @return
     */
    @GetMapping("/users")
    List<UserRepresentation> selectKeycloakUserDetail(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "first", required = false) Integer first,
            @RequestParam(value = "max", required = false) Integer max,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "exact", required = false) Boolean exact
    );

    /**
     * 전체 조회 중 필터링
     *
     * @param bearerToken
     * @return
     */
    @GetMapping("/users")
    List<UserRepresentation> selectKeycloakUsersByUsername(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam String username
    );

    /**
     * 사용자 상세 조회
     */
    @GetMapping("/users/{id}")
    UserRepresentation getKeycloakUserDetail(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String id
    );

    /**
     * 사용자 등록
     */
    @PostMapping("/users")
    void createUser(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody UserRepresentation userRepresentation
    );

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
    void deleteUser(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String id
    );

    /**
     * 비밀번호 재설정
     *
     * @param bearerToken
     * @param credentialRepresentation
     */
    @PutMapping("/users/{id}/reset-password")
    void resetPassword(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String id,
            @RequestBody CredentialRepresentation credentialRepresentation
    );
}
