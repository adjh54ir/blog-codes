package com.blog.springbootkeycloak.service;

import org.springframework.stereotype.Service;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakClientService
 * @since : 2025. 1. 31.
 */
@Service
public interface KeycloakClientService {

    String getAccessToken();

    String callProtectedApi();
}
