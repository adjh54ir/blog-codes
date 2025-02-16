package com.adjh.springbootexternalnetwork.config;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class OpenFeignConfig {

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
     * 공통 FeignClient Header 정책 설정
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestHeaderInterceptor() {

        log.warn("요청 인터셉터 수행 시점 >>>>>>>>>>>>>>>>>>");
        return requestTemplate -> {
            // Bearer 토큰 추가
//            requestTemplate.header("Authorization", "Bearer " + getTempAccessToken());
//            // 공통 헤더 추가
//            requestTemplate.header("x-api-key", "your-api-key");
//            requestTemplate.header("Custom-Header", "common-value");
        };
    }


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
     * 공통 FeignClient 재시도 정책 설정
     *
     * @return
     */
    @Bean
    public Retryer retryer() {
        // period: 100ms (초기 재시도 간격)
        // maxPeriod: 1000ms (최대 재시도 간격)
        // maxAttempts: 3 (최대 재시도 횟수)
        return new Retryer.Default(100, 1000, 3);
    }

}
