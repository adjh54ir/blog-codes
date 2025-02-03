package com.adjh.springbootkeycloaksub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : ReceiveController
 * @since : 2025. 1. 31.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/receive")
public class ReceiveController {

    /**
     * AccessToken을 수신하여서 리소스에 접근한다.
     *
     * @return
     */
    @PostMapping("/accessToken")
    public ResponseEntity<Object> getAccessToken(
            @RequestHeader("Authorization") String authHeader
    ) {
        // Bearer 토큰 추출
        String token = authHeader.substring(7); // "Bearer " 이후의 토큰 값
        log.debug("Keycloak Server에서 발급된 토큰이 전달되었습니다 :: {}", token);

        return new ResponseEntity<>("보내는 측의 토큰 :: " + token, HttpStatus.OK);
    }

}
