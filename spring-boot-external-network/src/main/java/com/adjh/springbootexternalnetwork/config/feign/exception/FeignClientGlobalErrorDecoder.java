package com.adjh.springbootexternalnetwork.config.feign.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * OpenFeign Client Error 전역 예외처리를 관리합니다.
 *
 * @author : jonghoon
 * @fileName : FeignClientGlobalErrorDecoder
 * @since : 25. 2. 20.
 */
@Slf4j
//@Component
public class FeignClientGlobalErrorDecoder
//        implements ErrorDecoder
{

//    /**
//     * OpenFeign에서 발생하는 Status Code를 기반으로 오류를 커스텀 처리로 수행합니다.
//     *
//     * @param methodKey
//     * @param response
//     * @return
//     */
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        switch (response.status()) {
//            case 400:
//                return new BadRequestException("잘못된 요청입니다.");
//            case 404:
//                return new NotFoundException("리소스를 찾을 수 없습니다.");
//            case 500:
//                return new InternalServerErrorException("서버 내부 오류가 발생했습니다.");
//            default:
//                return new Exception("알 수 없는 오류가 발생했습니다.");
//        }
//    }
}
