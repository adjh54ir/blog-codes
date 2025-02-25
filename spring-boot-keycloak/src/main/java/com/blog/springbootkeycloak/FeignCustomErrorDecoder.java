package com.blog.springbootkeycloak;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author : leejonghoon
 * @fileName : FeignCustomErrorDecoder
 * @since : 2025. 2. 14.
 */
//@Component
public class FeignCustomErrorDecoder {

    /**
     * 반환되는 상태 값에 따라 Exception을 사용합니다.
     *
     * @param methodKey
     * @param response
     * @return
     */
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        switch (response.status()) {
//            case 400:
//                return new BadRequestException("잘못된 요청입니다.");
////            case 401:
////                return new UnauthorizedException("인증되지 않은 요청입니다.");
//            case 403:
//                return new ForbiddenException("접근 권한이 없습니다.");
//            case 404:
//                return new NotFoundException("요청한 리소스를 찾을 수 없습니다.");
////            case 500:
////                return new InternalServerException("서버 내부 오류가 발생했습니다.");
////            case 408: // Request Timeout
////                return new RequestTimeoutException("요청 시간이 초과되었습니다.");
////            case 504: // Gateway Timeout
////                return new GatewayTimeoutException("게이트웨이 시간이 초과되었습니다.");
//            default:
//                return new Exception("알 수 없는 오류가 발생했습니다.");
//        }
//    }
}
