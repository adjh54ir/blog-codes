package com.adjh.springbootoauth2.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuth2ClientPropertiesSource
 * @since : 11/6/24
 */
@Getter
@Setter
//@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class OAuth2ClientPropertiesSource {

    private Map<String, Provider> provider = new HashMap<>();
    private Map<String, Registration> registration = new HashMap<>();

    @Getter
    @Setter
    public static class Provider {
        private String authorizationUri;
        private String tokenUri;
        private String userInfoUri;
        private String userNameAttribute;
    }

    @Getter
    @Setter
    public static class Registration {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
        private String authorizationGrantType;
        private String clientAuthenticationMethod;
        private String clientName;
        private List<String> scope;
    }
}