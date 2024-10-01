package com.adjh.springboot3security.controller;

import com.adjh.springboot3security.common.utils.TokenUtils;
import com.adjh.springboot3security.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : TokenController
 * @since : 2023/01/06
 */
@Slf4j
@RestController
@RequestMapping("api/v1/token")
public class TokenController {

    /**
     * [API] 사용자 정보를 기반으로 JWT를 발급하는 API
     *
     * @param userDto UserDto
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping("/generateToken")
    public ResponseEntity<Object> generateToken(@RequestBody UserDto userDto) {
        System.out.println("토큰을 생성합니다");

        String resultToken = TokenUtils.generateJwtToken(userDto);

        return new ResponseEntity<>(resultToken, HttpStatus.OK);
    }
}
