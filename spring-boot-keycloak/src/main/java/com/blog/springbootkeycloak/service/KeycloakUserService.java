package com.blog.springbootkeycloak.service;

import com.blog.springbootkeycloak.dto.properties.KeycloakProperties;
import com.blog.springbootkeycloak.dto.*;
import com.blog.springbootkeycloak.service.feign.KeycloakAuthFeignClient;
import com.blog.springbootkeycloak.service.feign.KeycloakUserFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.MappingsRepresentation;
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
     * Keycloak 사용자를 전체 조회합니다.
     *
     * @param bearerToken
     * @return
     */
    public List<UserRepresentation> selectKeycloakUserList(KeycloakUserSearchDto kus) {

        // 1. [Keycloak] 토큰 유효성 체크
//        this.validateToken(bearerToken);

        // 2. [Keycloak] 사용자 조회
        return keycloakUserFeignClient.selectKeycloakUserDetail(
                kus.getFirst(),
                kus.getMax(),
                kus.getSearch(),
                kus.getUsername(),
                kus.getEmail(),
                kus.getEnabled(),
                true
        );
    }

    /**
     * 사용자 등록
     *
     * @param bearerToken
     * @param ur
     */
    public int createUser(String bearerToken, UserRepresentation ur) {
        int result = 0;

        // 1. [Keycloak] 토큰 유효성 체크
        this.validateToken(bearerToken);

        try {
            // 2. [Keycloak] 사용자 생성
            keycloakUserFeignClient.createUser(bearerToken, ur);
            result = 1;
        } catch (FeignException.Conflict e) {
            throw new IllegalStateException("이미 존재하는 사용자입니다: " + e.getMessage());
        } catch (FeignException.BadRequest e) {
            throw new IllegalArgumentException("잘못된 사용자 정보입니다: " + e.getMessage());
        } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
            throw new SecurityException("인증/인가 오류가 발생했습니다: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("사용자 생성 중 예기치 않은 오류가 발생했습니다: " + e.getMessage());
        }
        return result;
    }

    /**
     * 사용자 수정
     *
     * @param bearerToken
     * @param ur
     */
    public int updateUser(String bearerToken, UserRepresentation ur) {
        int result = 0;

        // 1. [Keycloak] 토큰 유효성 체크
        this.validateToken(bearerToken);

        // 2. [Keycloak] username 기반 ID 조회
        String id = this.getKeycloakUserId(bearerToken, ur.getUsername());

        try {
            // 3. [Keycloak] 사용자 수정
            keycloakUserFeignClient.updateUser(bearerToken, id, ur);
            result = 1;
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다: " + id);
        } catch (FeignException.BadRequest e) {
            throw new IllegalArgumentException("잘못된 사용자 정보입니다: " + e.getMessage());
        } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
            throw new SecurityException("인증/인가 오류가 발생했습니다: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보 수정 중 예기치 않은 오류가 발생했습니다: " + e.getMessage());
        }
        return result;
    }

    /**
     * 사용자 삭제
     *
     * @param bearerToken
     * @param bearerToken
     * @param ur
     */
    public int deleteUser(String bearerToken, UserRepresentation ur) {
        int result = 0;
        // 1. [Keycloak] 토큰 유효성 체크
        this.validateToken(bearerToken);

        // 2. [Keycloak] username 기반 ID 조회
        String id = this.getKeycloakUserId(bearerToken, ur.getUsername());

        try {
            // 3. [Keycloak] 사용자 삭제
            keycloakUserFeignClient.deleteUser(bearerToken, id);
            result = 1;
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다: " + id);
        } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
            throw new SecurityException("인증/인가 오류가 발생했습니다: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("사용자 삭제 중 예기치 않은 오류가 발생했습니다: " + e.getMessage());
        }
        return result;
    }

    /**
     * 비밀번호를 재설정합니다.
     *
     * @param bearerToken
     * @param keycloakUserResetPwDto
     * @return
     */
    public int resetPassword(String bearerToken, KeycloakUserResetPwDto keycloakUserResetPwDto) {
        int intResult = 0;

        // 1. [Keycloak] 토큰 유효성 체크
        this.validateToken(bearerToken);

        // 2. [Keycloak] username 기반 ID 조회
        String id = this.getKeycloakUserId(bearerToken, keycloakUserResetPwDto.getUsername());

        // 3. [Keycloak] username 기반의 사용자 조회
        CredentialRepresentation result = new CredentialRepresentation();
        result.setValue(keycloakUserResetPwDto.getValue());
        result.setType("password");
        result.setTemporary(false);
        result.setId(id);

        try {
            // 3. [Keycloak] 비밀번호 재설정
            keycloakUserFeignClient.resetPassword(bearerToken, id, result);
            intResult = 1;
        } catch (FeignException e) {
            // 기타 Feign 예외
            throw new RuntimeException("Failed to reset password: " + e.getMessage());
        }

        return intResult;

    }


    /**
     * 사용자 그룹을 조회합니다.
     *
     * @param bearerToken
     * @param username
     * @return
     */
    public List<GroupRepresentation> getUserGroups(String bearerToken, String username) {
        // 1. [Keycloak] 토큰 유효성 체크
        this.validateToken(bearerToken);

        // 2. [Keycloak] username 기반 ID 조회
        String id = this.getKeycloakUserId(bearerToken, username);

        List<GroupRepresentation> result = keycloakUserFeignClient.getUserGroups(bearerToken, id);
        return result;
    }


    /**
     * 사용자를 그룹 내에 추가합니다.
     *
     * @param bearerToken
     * @param username
     * @param groupId
     * @return
     */
    public int addUserToGroup(String bearerToken, String username, String groupId) {
        int result = 0;

        // 1. [Keycloak] 토큰 유효성 체크
        this.validateToken(bearerToken);

        // 2. [Keycloak] username 기반 ID 조회
        String id = this.getKeycloakUserId(bearerToken, username);

        try {
            // 사용자를 그룹에 추가
            keycloakUserFeignClient.addUserToGroup(bearerToken, id, groupId);
            result = 1;
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("사용자 또는 그룹을 찾을 수 없습니다: " + id + ", " + groupId);
        } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
            throw new SecurityException("인증/인가 오류가 발생했습니다: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("그룹 추가 중 예기치 않은 오류가 발생했습니다: " + e.getMessage());
        }
        return result;
    }


    /**
     * 사용자의 역할 매핑 조회
     *
     * @param bearerToken
     * @param username
     * @return
     */
    public MappingsRepresentation getRoleMappings(String bearerToken, String username) {
        // 1. [Keycloak] 토큰 유효성 체크
        this.validateToken(bearerToken);

        // 2. [Keycloak] username 기반 ID 조회
        String id = this.getKeycloakUserId(bearerToken, username);

        try {
            // 사용자의 역할 매핑 조회
            return keycloakUserFeignClient.getRoleMappings(bearerToken, id);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다: " + id);
        } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
            throw new SecurityException("인증/인가 오류가 발생했습니다: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("역할 매핑 조회 중 예기치 않은 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 사용자 로그아웃
     */
    public int logoutUser(String bearerToken, String username) {
        int result = 0;
        // 1. [Keycloak] 토큰 유효성 체크
        this.validateToken(bearerToken);

        // 2. [Keycloak] username 기반 ID 조회
        String id = this.getKeycloakUserId(bearerToken, username);

        try {
            // 사용자 로그아웃 요청
            keycloakUserFeignClient.logoutUser(bearerToken, id);
            result = 1;
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다: " + id);
        } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
            throw new SecurityException("인증/인가 오류가 발생했습니다: " + e.getMessage());
        } catch (FeignException.BadRequest e) {
            throw new IllegalArgumentException("잘못된 요청입니다: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("로그아웃 처리 중 예기치 않은 오류가 발생했습니다: " + e.getMessage());
        }
        return result;
    }


    // ***********************************************************************************************************************************
    // *************************************************** private Method ****************************************************************
    // ***********************************************************************************************************************************

    /**
     * username 기반 id 조회
     *
     * @param bearerToken
     * @param username
     * @return
     */
    private String getKeycloakUserId(String bearerToken, String username) {

        // [Validation] 빈 값 체크
        if (username == null) {
            throw new IllegalArgumentException("username이 존재하지 않습니다.: ");
        }

        // [Validation] 빈 값 체크
        if (username.isEmpty()) {
            throw new IllegalArgumentException("username이 존재하지 않습니다.: ");
        }

        // [Keycloak] username 기반의 사용자 조회
        List<UserRepresentation> result = keycloakUserFeignClient.selectKeycloakUserDetail(
                null,
                null,
                null,
                username,
                null,
                null,
                true
        );

        // [Validation] 유효하지 않은 사용자
        if (result.isEmpty()) {
            throw new IllegalArgumentException("해당 username으로 등록된 사용자를 찾을 수 없습니다: " + username);
        }

        return result.get(0).getId();
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
        log.debug("bearerToken :: {}", bearerToken);
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

        String accessToken = bearerToken.split(" ")[1];     // Bearer를 제외하고 토큰 값만 전달
        TokenIntrospectionReqDto tokenIntrospectionReqDto = TokenIntrospectionReqDto.builder()
                .token(accessToken)
                .client_id(properties.getResource())
                .client_secret(properties.getCredentials().getSecret())
                .build();
        TokenIntrospectionResDto validTokenDto = keycloakAuthFeignClient.tokenIntrospect(tokenIntrospectionReqDto);      // Keycloak : 토큰 유효성 검증
        return validTokenDto.getActive();
    }

}
