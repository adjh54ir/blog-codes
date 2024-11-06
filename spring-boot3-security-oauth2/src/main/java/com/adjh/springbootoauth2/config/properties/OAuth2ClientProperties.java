package com.adjh.springbootoauth2.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public record OAuth2ClientProperties(ProviderProperties provider, RegistrationProperties registration) {

    public record ProviderProperties(ProviderConfig kakao, ProviderConfig naver) {
        public record ProviderConfig(String authorizationUri, String tokenUri, String userInfoUri, String userNameAttribute) {
        }
    }

    public record RegistrationProperties(RegistrationConfig kakao, RegistrationConfig naver) {
        public record RegistrationConfig(String clientId, String clientSecret, String redirectUri, String authorizationGrantType, String clientAuthenticationMethod,
                                         String clientName, List<String> scope) {
        }
    }
}