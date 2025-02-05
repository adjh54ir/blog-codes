package com.adjh.springbootkeycloaksub.config.properties;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakConfig
 * @since : 2025. 2. 4.
 */
@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {

    final private KeycloakProperties properties;

    @Bean
    public Keycloak keycloak(KeycloakProperties properties) {
        return KeycloakBuilder.builder()
                .serverUrl(properties.getAuthServerUrl())
                .realm(properties.getRealm())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(properties.getSpringBootAppSub().getResource())
                .clientSecret(properties.getSpringBootAppSub().getCredentials().getSecret())
                .build();
    }
}
