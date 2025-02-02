package com.adjh.springbootkeycloaksub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ReceiveController
 * @since : 25. 2. 2.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/keycloak/receive")
public class ReceiveController {

    /**
     * Direct Access Grants Flow : 토큰을 즉시 요청하는 방법
     *
     * @param accessTokenReqDto 전송 객체
     * @return 토큰 값 반환
     */
    @GetMapping("/token")
    public boolean receiveToken(@RequestHeader("Authorization") String bearerToken) {

        boolean isReceive = false;

        if (!bearerToken.isEmpty()) {
            // 1. 토큰 검증
            // bearerToken에서 "Bearer " 접두사 제거
            String token = bearerToken.replace("Bearer ", "");


            System.out.println(token);

            // 2. 토큰 유효성 검사
            // Keycloak 서버를 통해 토큰 검증

            // 3. 토큰에 포함된 권한 확인
            // 필요한 권한이 있는지 확인

            // 4. 요청 처리
            isReceive = true;
        }

        return isReceive;


    }
}
