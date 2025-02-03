package com.blog.springbootkeycloak.controller;

import com.blog.springbootkeycloak.dto.TokenRequestDto;
import com.blog.springbootkeycloak.service.AuthFlowService;
import com.blog.springbootkeycloak.service.KeycloakClientService;
import com.blog.springbootkeycloak.service.impl.KeycloakClientServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Keycloak에서 공통 데이터를 관리합니다.
 *
 * @author : jonghoon
 * @fileName : KeycloakController
 * @since : 25. 1. 25.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/keycloak")
public class KeycloakController {

    private final AuthFlowService authFlowService;
    private final KeycloakClientService keycloakClientService;


    /**
     * Keycloak 로그인 성공 이후 정보 수신 리다이렉트 URL
     *
     * @param code
     * @param state
     * @param session_state
     * @param error
     * @param error_description
     * @return
     */
    @GetMapping("/callback")
    public ResponseEntity<Object> loginCallback(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String session_state,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String error_description
    ) {

        log.debug("code :: {}", code);
        log.debug("state :: {}", state);
        log.debug("session_state :: {}", session_state);
        log.debug("error :: {}", error);
        log.debug("error_description :: {}", error_description);

        TokenRequestDto tokenRequestDto = TokenRequestDto.builder()
                .grant_type("authorization_code")
                .client_id("spring-boot-app")
                .client_secret("WQpTOpHkWwuTsvaiCwVVrs8vMvKLNom0")
                .code(code)
                .redirect_uri("http://localhost:8080/api/v1/keycloak/callback")
                .build();

        Object result = authFlowService.getAccessToken(tokenRequestDto);


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Keycloak 로그아웃 이후 정보 수신 리다이렉트 URL
     *
     * @param state
     * @param error
     * @param error_description
     * @return
     */
    @GetMapping("/logout")
    public ResponseEntity<String> logoutCallback(
            @RequestParam String state,
            @RequestParam String error,
            @RequestParam String error_description
    ) {
        log.debug("state :: {}", state);
        log.debug("error :: {}", error);
        log.debug("error_description :: {}", error_description);
        return new ResponseEntity<>(state, HttpStatus.OK);
    }

    /**
     * 토큰을 발급받습니다.
     *
     * @return
     */
    @PostMapping("/token")
    public ResponseEntity<Object> getToken() {
        String token = keycloakClientService.callProtectedApi();
        // token을 사용하여 필요한 작업 수행
        return ResponseEntity.ok("Access Token: " + token);
    }

}
