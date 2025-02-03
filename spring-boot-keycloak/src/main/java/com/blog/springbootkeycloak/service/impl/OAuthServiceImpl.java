package com.blog.springbootkeycloak.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuthService
 * @since : 25. 1. 30.
 */
@Service
@RequiredArgsConstructor
public class OAuthServiceImpl {

//    private final OAuth2AuthorizedClientManager authorizedClientManager;
//
//    public String getAccessToken() {
//        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
//                .withClientRegistrationId("keycloak")
//                .principal("service-account")
//                .build();
//
//        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
//
//        if (authorizedClient != null) {
//            return authorizedClient.getAccessToken().getTokenValue();
//        }
//
//        throw new RuntimeException("Failed to obtain access token");
//    }
}
