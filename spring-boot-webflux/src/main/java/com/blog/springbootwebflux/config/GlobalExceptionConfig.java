package com.blog.springbootwebflux.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

/**
 * 전역으로 발생하는 Exception을 등록하는 환경설정 파일
 *
 * @author : jonghoon
 * @fileName : GlobalExceptionConfig
 * @since : 24. 12. 28.
 */
@Slf4j
@Configuration
public class GlobalExceptionConfig {

    /**
     * GlobalErrorAttributes 을 등록합니다.
     *
     * @return ErrorAttributes
     */
    @Bean
    public ErrorAttributes errorAttributes() {
        return new GlobalErrorAttributes();
    }

    /**
     * 에러 핸들러에 필요한 리소스를 제공합니다.
     *
     * @return WebProperties.Resources
     */
    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    /**
     * GlobalErrorWebExceptionHandler를 위한 Bean 추가
     *
     * @return
     */
    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }
}
