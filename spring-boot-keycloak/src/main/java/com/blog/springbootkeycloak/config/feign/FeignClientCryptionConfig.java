package com.blog.springbootkeycloak.config.feign;

import lombok.extern.slf4j.Slf4j;

/**
 * OpenFeign Encoding/Decoding 설정 환경 파일
 *
 * @author : leejonghoon
 * @fileName : FeignClientCryptionConfig
 * @since : 2025. 2. 18.
 */
@Slf4j
//@Configuration
public class FeignClientCryptionConfig {

    /**
     * 파라미터 데이터 인코딩 및 암호화
     *
     * @param messageConverters HTTP 메시지 변환기
     * @return Feign Encoder
     */
//    @Bean
//    public Encoder feignEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
//        log.debug("feignEncoder message :: {}", messageConverters.toString());
//        return new EncryptionEncoder(new SpringEncoder(messageConverters));
//        return new JacksonEncoder();
//    }

    /**
     * 파라미터 데이터 디코딩 및 복호화
     *
     * @param messageConverters HTTP 메시지 변환기
     * @return Feign Decoder
     */
//    @Bean
//    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
//        log.debug("feignDecoder message :: {}", messageConverters.toString());
//        return new DecryptionDecoder(new SpringDecoder(messageConverters));
//        return new JacksonDecoder();
//    }
}
