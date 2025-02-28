package com.blog.springbootkeycloak.config.exception;

import com.blog.springbootkeycloak.dto.common.ErrorResponse;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Controller 내에서 발생하는 Exception 대해서 Catch 하여 응답값(Response)을 보내주는 기능을 수행함.
 */
@Slf4j
@RestControllerAdvice
public class GlobalFeignExceptionHandler {

    private final HttpStatus httpStatusOK = HttpStatus.OK;

    /**
     * 클라이언트 오류: 400 Bad Request
     */
    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(FeignException.BadRequest ex) {
        log.error("잘못된 요청 형식 오류: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "잘못된 요청",
                "요청 형식이 올바르지 않거나 유효하지 않은 파라미터입니다"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * 클라이언트 오류: 401 Unauthorized
     */
    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(FeignException.Unauthorized ex) {
        log.error("인증 오류: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "인증 실패",
                "유효한 인증 정보가 필요합니다"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 클라이언트 오류: 403 Forbidden
     */
    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(FeignException.Forbidden ex) {
        log.error("권한 없음: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "접근 거부",
                "요청한 리소스에 접근할 권한이 없습니다"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * 클라이언트 오류: 404 Not Found
     */
    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(FeignException.NotFound ex) {
        log.error("리소스를 찾을 수 없음: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "리소스 없음",
                "요청한 리소스가 존재하지 않습니다: " + ex.request().url()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * 클라이언트 오류: 405 Method Not Allowed
     */
    @ExceptionHandler(FeignException.MethodNotAllowed.class)
    public ResponseEntity<ErrorResponse> handleMethodNotAllowedException(FeignException.MethodNotAllowed ex) {
        log.error("허용되지 않는 메서드: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                "메서드 불허용",
                "요청한 HTTP 메서드가 이 리소스에서 지원되지 않습니다"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 서버 오류: 503 Service Unavailable
     */
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<ErrorResponse> handleServiceUnavailableException(FeignException.ServiceUnavailable ex) {
        log.error("서비스 일시 중단: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "서비스 이용 불가",
                "현재 서비스를 이용할 수 없습니다. 잠시 후 다시 시도해주세요."
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * 재시도 가능한 예외 처리
     */
    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ErrorResponse> handleRetryableException(RetryableException ex) {
        log.error("재시도 가능한 오류 발생: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "일시적 서비스 장애",
                "외부 서비스와 통신 중 일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * 기본 FeignException 처리 (위에서 처리되지 않은 모든 Feign 예외)
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
        log.error("Feign 클라이언트 오류: {}", ex.getMessage());

        // HTTP 상태 코드 추출
        int status = ex.status();
        HttpStatus httpStatus = HttpStatus.valueOf(status != -1 ? status : 500);

        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                "외부 서비스 통신 오류",
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    /**
     * 응답 디코딩 오류
     */
    @ExceptionHandler(feign.codec.DecodeException.class)
    public ResponseEntity<ErrorResponse> handleDecodeException(feign.codec.DecodeException ex) {
        log.error("응답 디코딩 오류: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "응답 처리 오류",
                "외부 서비스의 응답을 처리하는 데 문제가 발생했습니다"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
