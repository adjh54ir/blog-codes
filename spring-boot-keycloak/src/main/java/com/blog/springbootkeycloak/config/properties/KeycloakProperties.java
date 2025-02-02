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
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {

    private String authServerUrl;
    private String realm;
    private String clientId;
    private String clientSecret;

    @Getter
    public static class url {
        private String getTokenUrl;

        public url(String getTokenUrl) {
            this.getTokenUrl = getTokenUrl;
        }
    }


    public KeycloakProperties(String authServerUrl, String realm, String clientId, String clientSecret) {
        this.authServerUrl = authServerUrl;
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
