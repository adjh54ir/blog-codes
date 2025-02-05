package com.adjh.springbootkeycloaksub.service;

import com.adjh.springbootkeycloaksub.dto.KeycloakUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakAdminService
 * @since : 2025. 2. 5.
 */
@FeignClient(
        name = "keycloak-auth-admin-service",
        url = "http://localhost:9001/admin/realms/dev-realm"
)
@Service
public interface KeycloakAdminService {

    /**
     * 사용자 리스트를 조회합니다.
     *
     * @param bearerToken
     * @return
     */
    @GetMapping(value = "/users")
    List<KeycloakUserDto> getUserInfo(@RequestHeader("Authorization") String bearerToken);

}
