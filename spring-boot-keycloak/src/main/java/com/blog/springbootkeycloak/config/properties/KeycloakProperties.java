package com.blog.springbootkeycloak.config.properties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : KeycloakProperties
 * @since : 25. 1. 25.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {
    private String authServerUrl;
    private String realm;
    private String resource;
    private String redirectUri;
    private Credentials credentials;

    @Getter
    @Setter
    public static class Credentials {
        private String secret;
    }

    @Builder
    public KeycloakProperties(String authServerUrl, String realm, String resource, String redirectUri, Credentials credentials) {
        this.authServerUrl = authServerUrl;
        this.realm = realm;
        this.resource = resource;
        this.redirectUri = redirectUri;
        this.credentials = credentials;
    }
}
