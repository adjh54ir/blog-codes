package com.adjh.springbootexternalnetwork.config.custom;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CustomFeignClientConfig
 * @since : 25. 2. 16.
 */
@Configuration
public class CustomFeignClientConfig {


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
