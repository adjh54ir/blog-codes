package com.adjh.springbootkeycloaksub.service;

import com.adjh.springbootkeycloaksub.dto.TokenIntrospectionReqDto;
import com.adjh.springbootkeycloaksub.dto.TokenIntrospectionResDto;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakService
 * @since : 2025. 2. 3.
 */
@FeignClient(
        name = "keycloak-auth-service",
        url = "http://localhost:9001/realms/dev-realm/protocol/openid-connect"
)
@Service
public interface KeycloakService {


    /**
     * 토큰의 유효성을 검증합니다.
     *
     * @param tokenIntrospectionReqDto
     * @return
     */
    @PostMapping(value = "/token/introspect", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenIntrospectionResDto tokenIntrospect(@ModelAttribute TokenIntrospectionReqDto tokenIntrospectionReqDto);


    /**
     * 사용자 리스트를 조회합니다.
     *
     * @param bearerToken
     * @return
     */
    @GetMapping(value = "/users")
    Object getUserInfo(@RequestHeader("Authorization") String bearerToken);

}
