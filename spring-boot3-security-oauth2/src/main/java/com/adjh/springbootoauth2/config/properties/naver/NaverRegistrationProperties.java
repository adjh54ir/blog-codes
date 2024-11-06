package com.adjh.springbootoauth2.config.properties.naver;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : NaverRegistrationProperties
 * @since : 11/6/24
 */
@Getter
@ConfigurationProperties(prefix = "spring.security.oauth2.client.provider.naver.registration")
public class NaverRegistrationProperties {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String authorizationGrantType;
    private String clientAuthenticationMethod;
    private String clientName;
    private List<String> scope;
}
