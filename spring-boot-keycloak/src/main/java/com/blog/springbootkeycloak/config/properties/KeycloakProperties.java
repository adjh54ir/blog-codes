package com.blog.springbootkeycloak.config.properties;

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
    private Credentials credentials;

    @Getter
    @Setter
    public static class Credentials {
        private String secret;
    }

    public KeycloakProperties(String authServerUrl, String realm, String resource, Credentials credentials) {
        this.authServerUrl = authServerUrl;
        this.realm = realm;
        this.resource = resource;
        this.credentials = credentials;
    }
}
