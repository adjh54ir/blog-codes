package com.adjh.springbootoauth2.config.properties.kakao;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : KakaoProviderProperties
 * @since : 11/6/24
 */
@Getter
@ConfigurationProperties(prefix = "spring.security.oauth2.client.provider.kakao.provider")
public class KakaoProviderProperties {
    private String authorizationUri;
    private String tokenUri;
    private String userInfoUri;
    private String userNameAttribute;
}
