package com.blog.springbootkeycloak.config.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign Custom 설정 클래스
 *
 * @author : jonghoon
 * @fileName : CustomFeignClientConfig
 * @since : 25. 2. 13.
 */
@Configuration
public class CustomFeignClientConfig {

    /**
     * 로깅 설정 : 커스텀 설정
     *
     * @return
     */
    @Bean
    Logger.Level customFeignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
