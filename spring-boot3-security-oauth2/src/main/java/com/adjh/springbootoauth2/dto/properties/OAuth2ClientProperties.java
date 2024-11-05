package com.adjh.springbootoauth2.dto.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuth2ClientProperties
 * @since : 11/5/24
 */
@Getter
//@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class OAuth2ClientProperties {
    private final ProviderProperties provider;
    private final RegistrationProperties registration;

    public OAuth2ClientProperties(ProviderProperties provider, RegistrationProperties registration) {
        this.provider = provider;
        this.registration = registration;
    }

    @Getter
    public static class ProviderProperties {
        private final ProviderConfig kakao;
        private final ProviderConfig naver;

        public ProviderProperties(ProviderConfig kakao, ProviderConfig naver) {
            this.kakao = kakao;
            this.naver = naver;
        }

        @Getter
        public static class ProviderConfig {
            private final String authorizationUri;
            private final String tokenUri;
            private final String userInfoUri;
            private final String userNameAttribute;

            public ProviderConfig(String authorizationUri, String tokenUri, String userInfoUri, String userNameAttribute) {
                this.authorizationUri = authorizationUri;
                this.tokenUri = tokenUri;
                this.userInfoUri = userInfoUri;
                this.userNameAttribute = userNameAttribute;
            }
        }
    }

    @Getter
    public static class RegistrationProperties {
        private final RegistrationConfig kakao;
        private final RegistrationConfig naver;

        public RegistrationProperties(RegistrationConfig kakao, RegistrationConfig naver) {
            this.kakao = kakao;
            this.naver = naver;
        }

        @Getter
        public static class RegistrationConfig {
            private final String clientId;
            private final String clientSecret;
            private final String redirectUri;
            private final String authorizationGrantType;
            private final String clientAuthenticationMethod;
            private final String clientName;
            private final String[] scope;

            public RegistrationConfig(String clientId, String clientSecret, String redirectUri,
                                      String authorizationGrantType, String clientAuthenticationMethod,
                                      String clientName, String[] scope) {
                this.clientId = clientId;
                this.clientSecret = clientSecret;
                this.redirectUri = redirectUri;
                this.authorizationGrantType = authorizationGrantType;
                this.clientAuthenticationMethod = clientAuthenticationMethod;
                this.clientName = clientName;
                this.scope = scope;
            }
        }
    }
}