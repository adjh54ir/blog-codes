package com.blog.springbootkeycloak.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 설정 클래스
 *
 * @author : jonghoon
 * @fileName : FeignClientConfig
 * @since : 25. 2. 13.
 */
@Configuration
public class FeignClientConfig {

    /**
     * 로깅 설정 : 전역
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
