package com.adjh.springbootoauth2.controller;

import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverReqDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverResDto;
import com.adjh.springbootoauth2.service.OAuth2Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuth2Controller
 * @since : 11/1/24
 */
@Slf4j
@RestController
@RequestMapping("api/v1/oauth2")
public class OAuth2Controller {

    @Autowired
    private OAuth2Service oAuth2Service;


    /**
     * [API] 카카오톡 로그인
     *
     * @param
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     * @Refrence : https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#kakaologin
     */
    @GetMapping("/kakao")
    public ResponseEntity<Object> kakaoLogin(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String error_description,
            @RequestParam(required = false) String state
    ) {
        LoginKakaoReqDto kakaoReqDto = LoginKakaoReqDto.builder()
                .code(code)
                .error(error)
                .errorDescription(error_description)
                .state(state)
                .build();

        Object resultObj = oAuth2Service.kakaoLogin(kakaoReqDto);

        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }


    /**
     * [API] 네이버 로그인
     *
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     * @refrence : https://developers.naver.com/docs/login/api/api.md
     */
    @GetMapping("/naver")
    public ResponseEntity<Object> naverLogin(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String error_description,
            @RequestParam(required = false) String state
    ) {
        LoginNaverReqDto naverReqDto = LoginNaverReqDto.builder()
                .code(code)
                .error(error)
                .errorDescription(error_description)
                .state(state)
                .build();

        Object resultObj = oAuth2Service.naverLogin(naverReqDto);

        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }


}
