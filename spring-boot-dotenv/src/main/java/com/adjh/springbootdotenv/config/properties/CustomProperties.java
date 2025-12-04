package com.adjh.springbootdotenv.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Properties 파일로 정의
 *
 * @author : leejonghoon
 * @fileName : CustomProperties
 * @since : 25. 12. 2.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
    private String appEnv;
    private String dbHost;
    private String dbUser;
    private String testKey;
}
