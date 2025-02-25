package com.adjh.springbootexternalnetwork.config.feign;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 설정 클래스
 *
 * @author : jonghoon
 * @fileName : FeignClientConfig
 * @since : 25. 2. 13.
 */
@Slf4j
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
