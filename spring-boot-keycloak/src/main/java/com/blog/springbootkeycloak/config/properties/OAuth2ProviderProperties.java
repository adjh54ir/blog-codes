package com.blog.springbootkeycloak.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuthProperties
 * @since : 25. 1. 30.
 */


@ConfigurationProperties(prefix = "spring.security.oauth2.client.provider")
public record OAuth2ProviderProperties(ProviderConfig keycloak) {

    public record ProviderConfig(
            String authorizationUri,
            String tokenUri,
            String userInfoUri,
            String jwkSetUri
    ) {

    }
}
