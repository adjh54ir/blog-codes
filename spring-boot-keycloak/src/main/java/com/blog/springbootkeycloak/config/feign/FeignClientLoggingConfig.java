package com.blog.springbootkeycloak.config.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 로깅 정책 설정 환경 파일
 *
 * @author : jonghoon
 * @fileName : FeignClientLoggingConfig
 * @since : 25. 2. 16.
 */
@Configuration
public class FeignClientLoggingConfig {

    /**
     * 공통 FeignClient Logging 정책 설정
     *
     * @return
     */
    @Bean
    Logger.Level customFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 특정 FeignClient Logging 정책 설정
     */
    @Bean
    public Logger.Level defaultFeignLoggerLevel() {
        return Logger.Level.BASIC;  // 특정 Feign 클라이언트에 적용될 기본 로깅 레벨
    }

}