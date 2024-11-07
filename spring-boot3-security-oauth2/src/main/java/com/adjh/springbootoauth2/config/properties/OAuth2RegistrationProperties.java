package com.adjh.springbootoauth2.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration")
public record OAuth2RegistrationProperties(
        RegistrationConfig kakao,
        RegistrationConfig naver
) {
    public record RegistrationConfig(
            String clientId,
            String clientSecret,
            String redirectUri,
            String authorizationGrantType,
            String clientAuthenticationMethod,
            String clientName,
            List<String> scope
    ) {
    }
}