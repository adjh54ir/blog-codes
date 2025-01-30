package com.blog.springbootkeycloak.config;

import com.blog.springbootkeycloak.config.properties.OAuth2ProviderProperties;
import com.blog.springbootkeycloak.config.properties.OAuth2RegistrationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuthClientConfig
 * @since : 25. 1. 30.
 */
@Configuration
@RequiredArgsConstructor
public class OAuthClientConfig {

    private final OAuth2ProviderProperties provider;
    private final OAuth2RegistrationProperties registration;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ProviderProperties oAuth2ProviderProperties) {
        ClientRegistration keycloakRegistration = ClientRegistration
                .withRegistrationId("keycloak")
                .clientId(registration.keycloak().clientId())
                .clientSecret(registration.keycloak().clientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("openid")
                .tokenUri(provider.keycloak().tokenUri())
                .build();

        return new InMemoryClientRegistrationRepository(keycloakRegistration);
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository,
                        authorizedClientRepository);

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
}
