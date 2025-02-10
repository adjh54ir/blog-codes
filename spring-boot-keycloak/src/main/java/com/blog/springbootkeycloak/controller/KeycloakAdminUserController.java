package com.blog.springbootkeycloak.controller;

import com.blog.springbootkeycloak.service.KeycloakAdminClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakAdminUserController
 * @since : 2025. 2. 10.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/admin")
public class KeycloakAdminUserController {

    private final KeycloakAdminClientService keycloakAdminClientService;

    /**
     * [관리자] 토큰없이 즉시 호출을 수행합니다.
     *
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<Object> keycloakAdminUsers() {
        List<UserRepresentation> result = keycloakAdminClientService.getKeycloakUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
