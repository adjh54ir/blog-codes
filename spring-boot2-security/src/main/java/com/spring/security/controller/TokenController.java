package com.spring.security.controller;

import com.spring.security.common.utils.TokenUtils;
import com.spring.security.model.common.ApiResponse;
import com.spring.security.model.common.SuccessCode;
import com.spring.security.model.constant.AuthConstants;
import com.spring.security.model.dto.UserDto;
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
    public ResponseEntity<ApiResponse> selectCodeList(@RequestBody UserDto userDto) {

        String resultToken = TokenUtils.generateJwtToken(userDto);

        ApiResponse ar = ApiResponse.builder()
                // BEARER {토큰} 형태로 반환을 해줍니다.
                .result(AuthConstants.TOKEN_TYPE + " " + resultToken)
                .resultCode(SuccessCode.SELECT.getStatus())
                .resultMsg(SuccessCode.SELECT.getMessage())
                .build();

        return new ResponseEntity<>(ar, HttpStatus.OK);
    }
}
