package com.blog.springbootkeycloak.service;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : KeycloakService
 * @since : 25. 1. 25.
 */

import com.blog.springbootkeycloak.dto.TokenRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * json placeholder 외부 통신을 하는 예시
 *
 * @author : jonghoon
 * @fileName : OpenFeignService
 * @since : 11/23/24
 */
@FeignClient(
        name = "keycloak-auth-service",
        url = "http://localhost:9001/realms/dev-realm"
)
@Service
public interface KeycloakService {

    @PostMapping(
            value = "/protocol/openid-connect/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE

    )
    Object getAccessToken(@ModelAttribute TokenRequestDto tokenRequestDto);

}
