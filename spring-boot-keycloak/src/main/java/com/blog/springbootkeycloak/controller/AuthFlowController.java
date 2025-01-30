package com.blog.springbootkeycloak.controller;

import com.blog.springbootkeycloak.dto.TokenRequestDto;
import com.blog.springbootkeycloak.dto.StandardFlowDto;
import com.blog.springbootkeycloak.service.AuthFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * OIDC 인증 플로우 구성
 *
 * @author : jonghoon
 * @fileName : AuthFlowController
 * @since : 25. 1. 28.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/keycloak/authFlow")
public class AuthFlowController {

    private final AuthFlowService authFlowService;

    public AuthFlowController(AuthFlowService authFlowService) {
        this.authFlowService = authFlowService;
    }

    /**
     * Direct Access Grants Flow : 토큰을 즉시 요청하는 방법
     *
     * @param tokenRequestDto 전송 객체
     * @return 토큰 값 반환
     */
    @PostMapping("/directAccess")
    public ResponseEntity<Object> getDirectAccessToken(@RequestBody TokenRequestDto tokenRequestDto) {
        try {
            Object resultToken = authFlowService.getAccessToken(tokenRequestDto);
            return new ResponseEntity<>(resultToken, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Token request failed", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Standard Flow : 로그인 페이지 출력 및 로그인
     *
     * @param response_type
     * @param client_id
     * @param redirect_uri
     * @param scope
     * @param state
     * @return
     */
    @GetMapping("/standardFlow")
    public ResponseEntity<Object> getStandardFlow(
            @RequestParam String response_type,
            @RequestParam String client_id,
            @RequestParam String redirect_uri,
            @RequestParam(required = false) String scope,
            @RequestParam(required = false) String state
    ) {
        StandardFlowDto standardFlowDto = StandardFlowDto.builder()
                .response_type(response_type)
                .client_id(client_id)
                .redirect_uri(redirect_uri)
                .scope(scope)
                .state(state)
                .build();
        try {
            String authorizationUrl = authFlowService.getStandardFlowLoginView(standardFlowDto);
            return new ResponseEntity<>(authorizationUrl, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Standard flow failed", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
