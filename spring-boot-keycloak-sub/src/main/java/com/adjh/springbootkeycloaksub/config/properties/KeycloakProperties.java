package com.adjh.springbootkeycloaksub.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakProperties
 * @since : 2025. 2. 3.
 */

@Getter
@Setter
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {

    private String authServerUrl;
    private String realm;

    private ProviderConfig springBootApp;
    private ProviderConfig springBootAppSub;

    @Setter
    @Getter
    public static class ProviderConfig {
        private String resource;
        private String redirectUrl;
        private Credentials credentials;
    }

    @Getter
    @Setter
    public static class Credentials {
        private String secret;
    }


}
