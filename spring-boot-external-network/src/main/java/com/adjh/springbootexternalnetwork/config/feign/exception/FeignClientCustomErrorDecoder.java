package com.adjh.springbootexternalnetwork.config.feign.exception;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * OpenFeign Client Error 지역 예외처리를 관리합니다.
 *
 * @author : leejonghoon
 * @fileName : FeignClientCustomErrorDecoder
 * @since : 2025. 3. 2.
 */
@Slf4j
@Component
public class FeignClientCustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        /*
         * FeignException 발생 시 이를 그대로 반환합니다.
         * 이렇게 구성하면 GlobalFeignExceptionHandler 내에서 처리하도록 이관합니다.
         */
        return FeignException.errorStatus(methodKey, response);
    }
}
