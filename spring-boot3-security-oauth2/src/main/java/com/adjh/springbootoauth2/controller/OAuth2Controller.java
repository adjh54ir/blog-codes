package com.adjh.springbootoauth2.controller;

import com.adjh.springbootoauth2.config.RestTemplateConfig;
import com.adjh.springbootoauth2.dto.UserDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginKakaoResDto;
import com.adjh.springbootoauth2.dto.oauth2.LoginNaverResDto;
import com.adjh.springbootoauth2.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String kakaoAuthorizationUri;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String kakaoTokenUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUri;

    @Value("${spring.security.oauth2.client.provider.kakao.user-name-attribute}")
    private String kakaoUserNameAttribute;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;


    /**
     * [API] 카카오톡 로그인
     *
     * @param
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @GetMapping("/kakao")
    public ResponseEntity<LoginKakaoResDto> kakaoLogin(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String error_description,
            @RequestParam(required = false) String state
    ) {


        if (code != null && error != null) {
            RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", kakaoClientId);
            params.add("redirect_uri", kakaoRedirectUri);
            params.add("code", code);
            params.add("client_secret", kakaoClientSecret);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplateConfig.restTemplate().exchange(
                    kakaoTokenUri,
                    HttpMethod.POST,
                    request,
                    String.class
            );


            System.out.println("응답 값을 확인합니다 :: " + response);

        }


        return ResponseEntity.ok(null);
    }


    /**
     * [API] 네이버 로그인
     *
     * @param userDto UserDto
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping("/naver")
    public ResponseEntity<Object> naverLogin(@RequestBody LoginNaverResDto loginNaverResDto) {

        return new ResponseEntity<>(loginNaverResDto, HttpStatus.OK);
    }


}
