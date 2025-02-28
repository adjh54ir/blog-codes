package com.blog.springbootkeycloak.config.feign.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * OpenFeign Client Error 지역 예외처리를 관리합니다.
 *
 * @author : leejonghoon
 * @fileName : FeignClientCustomErrorDecoder
 * @since : 2025. 2. 28.
 */
public class FeignClientCustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return null;
    }
}
