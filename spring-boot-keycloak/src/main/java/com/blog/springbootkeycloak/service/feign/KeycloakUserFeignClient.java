package com.blog.springbootkeycloak.service.feign;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.MappingsRepresentation;
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
     * 아이디 기반 특정 사용자 조회
     */
    @GetMapping("/{id}}")
    void selectKeycloakUserDetail(
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

    /**
     * 사용자의 그룹 목록 조회
     */
    @GetMapping("/users/{id}/groups")
    List<GroupRepresentation> getUserGroups(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String id
    );

    /**
     * 사용자를 그룹에 추가
     */
    @PutMapping("/users/{id}/groups/{groupId}")
    void addUserToGroup(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String id,
            @PathVariable String groupId
    );

    /**
     * 사용자의 역할 매핑 조회
     */
    @GetMapping("/users/{id}/role-mappings")
    MappingsRepresentation getRoleMappings(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String id
    );

    /**
     * 사용자 로그아웃
     */
    @PostMapping("/users/{id}/logout")
    void logoutUser(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String id
    );


}
