package com.blog.springbootkeycloak.controller;

import com.blog.springbootkeycloak.service.KeycloakUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody UserRepresentation usp
    ) {
        List<UserRepresentation> result = userService.getKeycloakUsers(bearerToken, usp);
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
     * @param userRepresentation
     * @return
     */
    @GetMapping("/chgPassword")
    public ResponseEntity<Integer> changePassword(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody UserRepresentation userRepresentation
    ) {
        int result = userService.resetPassword(bearerToken, userRepresentation);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
