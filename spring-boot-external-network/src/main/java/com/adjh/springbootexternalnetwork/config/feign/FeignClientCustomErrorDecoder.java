package com.adjh.springbootexternalnetwork.config.feign;

import com.adjh.springbootexternalnetwork.dto.common.ErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * OpenFeign Client Error 예외처리를 관리합니다.
 * 해당 예외는 Bussiness Exception으로 처리하도록 반환합니다.
 *
 * @author : jonghoon
 * @fileName : FeignClientCustomErrorDecoder
 * @since : 25. 2. 20.
 */
@Slf4j
//@Component
public class FeignClientCustomErrorDecoder
//        implements ErrorDecoder
{

//    @Override
//    public Exception decode(String methodKey, Response response) {
//        switch (response.status()) {
//            case 400:
//                log.debug("[-] 통신 중 잘못된 요청이 발생되었습니다.");
//                return new BusinessException(ErrorCode.BAD_REQUEST_ERROR);
//            case 401:
//                log.debug("[-] 인가되지 않은 요청이 발생되었습니다.");
//                return new BusinessException(ErrorCode.UNAUTHORIZED_ERROR);
//            case 403:
//                log.debug("[-] 리소스 접근 권한이 없습니다.");
//                return new BusinessException(ErrorCode.FORBIDDEN_ERROR);
//            case 404:
//                log.debug("[-] 통신 중 잘못된 경로로 호출되었습니다.");
//                return new BusinessException(ErrorCode.NOT_FOUND_ERROR);
//            case 405:
//                log.debug("[-] 통신 중 잘못된 HTTP Method로 호출되었습니다.");
//                return new BusinessException(ErrorCode.METHOD_NOT_ALLOWED_ERROR);
//            case 500:
//                log.debug("[-] 내부 시스템 오류가 발생하였습니다.");
//                return new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
//            default:
//                log.debug("[-] 기타 오류가 발생하였습니다.");
//                return new BusinessException(ErrorCode.BUSINESS_EXCEPTION_ERROR);
//        }
//    }
}
