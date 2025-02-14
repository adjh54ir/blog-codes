package com.blog.springbootkeycloak.controller;

import com.blog.springbootkeycloak.dto.KeycloakUserResetPwDto;
import com.blog.springbootkeycloak.dto.KeycloakUserSearchDto;
import com.blog.springbootkeycloak.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.MappingsRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Keycloak 사용자 엔드포인트
 *
 * @author : leejonghoon
 * @fileName : KeycloakUserController
 * @since : 2025. 2. 10.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class KeycloakUserController {

    private final KeycloakUserService userService;

    /**
     * 사용자 목록을 조회합니다.
     *
     * @param bearerToken
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<Object> selectKeycloakUsers(
            @RequestBody KeycloakUserSearchDto keycloakUserSearchDto
    ) {
        List<UserRepresentation> result = userService.selectKeycloakUserList( keycloakUserSearchDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 등록
     *
     * @param bearerToken
     * @param userRepresentation
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<Integer> createUser(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody UserRepresentation userRepresentation
    ) {
        int result = userService.createUser(bearerToken, userRepresentation);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 수정
     *
     * @param bearerToken
     * @param userRepresentation
     * @return
     */
    @PutMapping("/user")
    public ResponseEntity<Integer> updateUser(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody UserRepresentation userRepresentation
    ) {
        int result = userService.updateUser(bearerToken, userRepresentation);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 삭제
     *
     * @param bearerToken
     * @param userRepresentation
     * @return
     */
    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteUser(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody UserRepresentation userRepresentation
    ) {
        int result = userService.deleteUser(bearerToken, userRepresentation);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 비밀번호 변경
     *
     * @param bearerToken
     * @param keycloakUserResetPwDto
     * @return
     */
    @GetMapping("/change-password")
    public ResponseEntity<Integer> changePassword(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody KeycloakUserResetPwDto keycloakUserResetPwDto
    ) {
        int result = userService.resetPassword(bearerToken, keycloakUserResetPwDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 사용자 그룹 조회
     *
     * @param bearerToken
     * @param username
     * @return
     */
    @PostMapping("/user-groups")
    public ResponseEntity<List<GroupRepresentation>> selectUserGroups(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam String username
    ) {
        List<GroupRepresentation> result = userService.getUserGroups(bearerToken, username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 사용자를 그룹에 추가합니다.
     *
     * @param bearerToken
     * @param username
     * @return
     */
    @PostMapping("/user-group")
    public ResponseEntity<Integer> addGroups(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam String username,
            @RequestParam String groupId
    ) {
        int result = userService.addUserToGroup(bearerToken, username, groupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 사용자 그룹 조회
     *
     * @param bearerToken
     * @param username
     * @return
     */
    @GetMapping("/user-role-mapping")
    public ResponseEntity<MappingsRepresentation> selectUserRoleMapping(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("username") String username
    ) {
        MappingsRepresentation result = userService.getRoleMappings(bearerToken, username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
