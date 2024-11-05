package com.adjh.springbootoauth2.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@Getter
@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class OAuth2ClientProperties {
    private final ProviderProperties provider;
    private final RegistrationProperties registration;

    public OAuth2ClientProperties(ProviderProperties provider, RegistrationProperties registration) {
        this.provider = provider;
        this.registration = registration;
    }

    @Getter
    public record ProviderProperties(OAuth2ClientProperties.ProviderProperties.ProviderConfig kakao, OAuth2ClientProperties.ProviderProperties.ProviderConfig naver) {
        @Getter
        public record ProviderConfig(String authorizationUri, String tokenUri, String userInfoUri, String userNameAttribute) {
        }
    }

    @Getter
    public record RegistrationProperties(OAuth2ClientProperties.RegistrationProperties.RegistrationConfig kakao,
                                         OAuth2ClientProperties.RegistrationProperties.RegistrationConfig naver) {

        @Getter
        public record RegistrationConfig(String clientId, String clientSecret, String redirectUri, String authorizationGrantType, String clientAuthenticationMethod,
                                         String clientName, List<String> scope) {
        }
    }
}