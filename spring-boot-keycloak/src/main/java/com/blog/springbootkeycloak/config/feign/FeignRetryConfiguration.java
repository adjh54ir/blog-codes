package com.blog.springbootkeycloak.config.feign;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 재시도 정책 설정 환경 파일
 *
 * @author : leejonghoon
 * @fileName : FeignRetryConfiguration
 * @since : 2025. 2. 14.
 */
@Configuration
public class FeignRetryConfiguration {

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
