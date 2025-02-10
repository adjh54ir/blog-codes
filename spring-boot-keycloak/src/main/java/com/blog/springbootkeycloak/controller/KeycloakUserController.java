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
    public ResponseEntity<Object> keycloakUsers(@RequestHeader("Authorization") String bearerToken) {
        List<UserRepresentation> result = userService.getKeycloakUsers(bearerToken);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 상세 조회
     *
     * @param bearerToken
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserRepresentation> getUserDetail(@RequestHeader("Authorization") String bearerToken, @PathVariable String userId) {
        return ResponseEntity.ok(userService.getKeycloakUserDetail(bearerToken, userId));
    }


    /**
     * 사용자 등록
     *
     * @param bearerToken
     * @param userRepresentation
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestHeader("Authorization") String bearerToken, @RequestBody UserRepresentation userRepresentation) {
        userService.createUser(bearerToken, userRepresentation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 사용자 수정
     *
     * @param bearerToken
     * @param userId
     * @param userRepresentation
     * @return
     */
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@RequestHeader("Authorization") String bearerToken, @PathVariable String userId, @RequestBody UserRepresentation userRepresentation) {
        userService.updateUser(bearerToken, userId, userRepresentation);
        return ResponseEntity.ok().build();
    }

    /**
     * 사용자 삭제
     *
     * @param bearerToken
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String bearerToken, @PathVariable String userId) {
        userService.deleteUser(bearerToken, userId);
        return ResponseEntity.ok().build();
    }
}
