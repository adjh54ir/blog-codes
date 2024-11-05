package com.adjh.springbootoauth2.dto.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuth2ClientRecordProperties
 * @since : 11/5/24
 */

@Getter
@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public record OAuth2ClientRecordProperties(ProviderProperties provider, RegistrationProperties registration) {

    public record ProviderProperties(ProviderConfig kakao, ProviderConfig naver) {
        public record ProviderConfig(String authorizationUri, String tokenUri, String userInfoUri, String userNameAttribute) {
        }
    }

    public record RegistrationProperties(RegistrationConfig kakao, RegistrationConfig naver) {
        public record RegistrationConfig(String clientId, String clientSecret, String redirectUri, String authorizationGrantType,
                                         String clientAuthenticationMethod,
                                         String clientName, List<String> scope) {
        }
    }
}
