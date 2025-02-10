package com.blog.springbootkeycloak.service.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Keycloak과 통신하여 사용자를 조회하는 서비스 입니다.
 *
 * @author : leejonghoon
 * @fileName : KeycloakGroupService
 * @since : 2025. 2. 10.
 */
@FeignClient(
        name = "keycloak-group-service",
        url = "${keycloak.auth-server-url}"
)
public interface KeycloakGroupFeignClient {
}
