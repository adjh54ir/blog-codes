package com.adjh.springbootkeycloaksub.controller;

<<<<<<< HEAD
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
>>>>>>> 6771f23ac33a3ab74444bb7786f5e6ab1a17cec2

/**
 * Please explain the class!!
 *
<<<<<<< HEAD
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

=======
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
>>>>>>> 6771f23ac33a3ab74444bb7786f5e6ab1a17cec2
}
