package com.blog.springbootkeycloak.config;

import com.blog.springbootkeycloak.FeignCustomErrorDecoder;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;

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


    /**
     * 디코딩을 하는 과정에서 발생한 오류에 대한 처리를 수행합니다.
     *
     * @return
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder();
    }

    /**
     * 파라미터 데이터 디코딩
     *
     * @param messageConverters
     * @return
     */
    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        log.debug("feignDecoder message :: {}", messageConverters.toString());
        return new SpringDecoder(messageConverters);
    }

    /**
     * 파라미터 데이터 인코딩
     *
     * @param messageConverters
     * @return
     */
    @Bean
    public Encoder feignEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        log.debug("feignEncoder message :: {}", messageConverters.toString());
        return new SpringEncoder(messageConverters);
    }

}
