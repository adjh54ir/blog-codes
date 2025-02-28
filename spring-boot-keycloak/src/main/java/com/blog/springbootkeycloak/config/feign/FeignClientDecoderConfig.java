package com.blog.springbootkeycloak.config.feign;

import com.blog.springbootkeycloak.config.feign.exception.FeignClientGlobalErrorDecoder;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign의 에러 처리를 위한 Configuration 클래스입니다
 *
 * @author : leejonghoon
 * @fileName : FeignClientDecoderConfig
 * @since : 2025. 2. 26.
 */
@Slf4j
//@Configuration
public class FeignClientDecoderConfig {

//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new FeignClientGlobalErrorDecoder();
//    }
}
