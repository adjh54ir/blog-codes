package com.blog.springbootkeycloak.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuth2RegistrationProperties
 * @since : 25. 1. 30.
 */
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration")
public record OAuth2RegistrationProperties(
        RegistrationConfig keycloak
) {
    public record RegistrationConfig(
            String clientId,
            String clientSecret,
            String authorizationGrantType
    ) {
    }

}
