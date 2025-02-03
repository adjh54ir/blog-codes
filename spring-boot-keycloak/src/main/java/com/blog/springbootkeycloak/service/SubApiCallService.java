package com.blog.springbootkeycloak.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * spring-boot-app-sub로 데이터를 전달합니다.
 *
 * @author : jonghoon
 * @fileName : SubApiCallService
 * @since : 25. 1. 30.
 */
@Service
@FeignClient(name = "keycloak-sub-call", url = "http://localhost:8081/api/v1/keycloak/receive")
public interface SubApiCallService {

    /**
     * spring-boot-app에서 발급된 토큰을 spring-boot-sub로 전달합니다.
     *
     * @param bearerToken
     * @return
     */
    @GetMapping("/token")
    boolean sendAccessTokenToSubApi(@RequestHeader("Authorization") String bearerToken);
}
