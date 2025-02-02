package com.blog.springbootkeycloak.config;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : OAuthClientConfig
 * @since : 25. 1. 30.
 */
//@Configuration
//@RequiredArgsConstructor
public class OAuthClientConfig {

//    private final OAuth2ProviderProperties provider;
//    private final OAuth2RegistrationProperties registration;
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ProviderProperties oAuth2ProviderProperties) {
//        ClientRegistration keycloakRegistration = ClientRegistration
//                .withRegistrationId("keycloak")
//                .clientId(registration.keycloak().clientId())
//                .clientSecret(registration.keycloak().clientSecret())
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .scope("openid")
//                .tokenUri(provider.keycloak().tokenUri())
//                .build();
//
//        return new InMemoryClientRegistrationRepository(keycloakRegistration);
//    }
//
//    @Bean
//    public OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
//
//        OAuth2AuthorizedClientProvider authorizedClientProvider =
//                OAuth2AuthorizedClientProviderBuilder.builder()
//                        .clientCredentials()
//                        .build();
//
//        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
//                new DefaultOAuth2AuthorizedClientManager(
//                        clientRegistrationRepository,
//                        authorizedClientRepository);
//
//        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
//
//        return authorizedClientManager;
//    }
}
