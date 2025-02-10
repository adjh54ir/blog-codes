package com.blog.springbootkeycloak.service.feign;

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
     * 사용자 정보를 전체 조회합니다.
     *
     * @param bearerToken
     * @return
     */
    @GetMapping("/users")
    List<UserRepresentation> getKeycloakUsers(
            @RequestHeader("Authorization") String bearerToken
    );

    /**
     * 사용자 상세 조회
     */
    @GetMapping("/users/{userId}")
    UserRepresentation getKeycloakUserDetail(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String userId
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
    @PutMapping("/users/{userId}")
    void updateUser(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String userId,
            @RequestBody UserRepresentation userRepresentation
    );

    /**
     * 사용자 삭제
     */
    @DeleteMapping("/users/{userId}")
    void deleteUser(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String userId
    );


    /**
     * 사용자 정보 내에 필터링을 포함하여 조회합니다.
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
    @GetMapping("/users")
    List<UserRepresentation> getKeycloakFilterUsers(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean emailVerified,
            @RequestParam(required = false) Boolean enabled,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String username
    );
}
