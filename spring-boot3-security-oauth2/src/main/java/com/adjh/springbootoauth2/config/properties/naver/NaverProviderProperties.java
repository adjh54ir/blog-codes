package com.adjh.springbootoauth2.config.properties.naver;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : NaverProviderProperties
 * @since : 11/6/24
 */
@Getter
@ConfigurationProperties(prefix = "spring.security.oauth2.client.provider.naver.provider")
public class NaverProviderProperties {
    private String authorizationUri;
    private String tokenUri;
    private String userInfoUri;
    private String userNameAttribute;
}
