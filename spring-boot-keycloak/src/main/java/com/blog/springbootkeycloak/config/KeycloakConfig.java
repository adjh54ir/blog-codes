package com.blog.springbootkeycloak.config;

import com.blog.springbootkeycloak.config.properties.KeycloakProperties;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : KeycloakConfig
 * @since : 25. 1. 25.
 */
@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

    final private KeycloakProperties properties;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(properties.getAuthServerUrl())
                .realm(properties.getRealm())
                .clientId(properties.getResource())
                .clientSecret(properties.getCredentials().getSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
