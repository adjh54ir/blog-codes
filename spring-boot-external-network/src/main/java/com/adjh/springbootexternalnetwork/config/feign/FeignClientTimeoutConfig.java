package com.adjh.springbootexternalnetwork.config.feign;

import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * OpenFeign 타임아웃 정책 설정 환경 파일
 *
 * @author : jonghoon
 * @fileName : FeignClientTimeoutConfig
 * @since : 25. 2. 16.
 */
@Slf4j
@Configuration
public class FeignClientTimeoutConfig {

    /**
     * 공통 FeignClient Timeout 정책 설정
     *
     * @return
     */
    @Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(
                5000,  // connectTimeout (5초)
                TimeUnit.MILLISECONDS,
                5000,  // readTimeout (5초)
                TimeUnit.MILLISECONDS,
                true   // followRedirects
        );
    }

    /**
     * 특정 FeignClient Timeout 정책 설정
     *
     * @return
     */
    @Bean
    public Request.Options customRequestOptions() {
        return new Request.Options(
                3000,  // connectTimeout (3초)
                TimeUnit.MILLISECONDS,
                3000,  // readTimeout (3초)
                TimeUnit.MILLISECONDS,
                true   // followRedirects
        );
    }
}
