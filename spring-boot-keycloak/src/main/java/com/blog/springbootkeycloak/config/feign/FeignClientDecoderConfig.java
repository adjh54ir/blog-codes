package com.blog.springbootkeycloak.config.feign;

import com.blog.springbootkeycloak.config.feign.exception.FeignClientCustomErrorDecoder;
import feign.Feign;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign의 에러 처리를 위한 Configuration 클래스입니다
 *
 * @author : leejonghoon
 * @fileName : FeignClientDecoderConfig
 * @since : 2025. 2. 26.
 */
@Configuration
public class FeignClientDecoderConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientCustomErrorDecoder();
    }
}
