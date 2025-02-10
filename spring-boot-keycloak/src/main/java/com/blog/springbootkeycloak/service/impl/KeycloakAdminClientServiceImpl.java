package com.blog.springbootkeycloak.service.impl;

import com.blog.springbootkeycloak.config.properties.KeycloakProperties;
import com.blog.springbootkeycloak.service.KeycloakAdminClientService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * keycloak Admin Client 기반 사용자 관리
 *
 * @author : leejonghoon
 * @fileName : KeycloakAdminClientServiceImpl
 * @since : 2025. 2. 10.
 */
@Service
@RequiredArgsConstructor
public class KeycloakAdminClientServiceImpl implements KeycloakAdminClientService {

    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    /**
     * 사용자 정보를 조회합니다.
     *
     * @return
     */
    @Override
    public List<UserRepresentation> getKeycloakUsers() {
        return keycloak
                .realm(keycloakProperties.getRealm())
                .users()
                .list();
    }
}
