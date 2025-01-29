package com.blog.springbootkeycloak.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : KeycloakProperties
 * @since : 25. 1. 25.
 */
@Getter
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {

    private String authServerUrl;
    private String realm;
    private String clientId;
    private String clientSecret;

    @Getter
    public static class url {
        private String getTokenUrl;
    }

}
