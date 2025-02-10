package com.blog.springbootkeycloak.service;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakAdminClientService
 * @since : 2025. 2. 10.
 */
@Service
public interface KeycloakAdminClientService {

    List<UserRepresentation> getKeycloakUsers();
}
