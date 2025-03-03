package com.adjh.springbootexternalnetwork.config.exception;

import com.adjh.springbootexternalnetwork.dto.common.ErrorCode;
import com.adjh.springbootexternalnetwork.dto.common.ErrorResponse;
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

        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_BAD_REQUEST_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }

    /**
     * 클라이언트 오류: 401 Unauthorized
     */
    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(FeignException.Unauthorized ex) {
        log.error("인증 오류: {}", ex.getMessage());

        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_UNAUTHORIZED_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }

    /**
     * 클라이언트 오류: 403 Forbidden
     */
    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(FeignException.Forbidden ex) {
        log.error("권한 없음: {}", ex.getMessage());
        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_FORBIDDEN_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }

    /**
     * 클라이언트 오류: 404 Not Found
     */
    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(FeignException.NotFound ex) {
        log.error("리소스를 찾을 수 없음: {}", ex.getMessage());

        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_NOT_FOUND_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }

    /**
     * 클라이언트 오류: 405 Method Not Allowed
     */
    @ExceptionHandler(FeignException.MethodNotAllowed.class)
    public ResponseEntity<ErrorResponse> handleMethodNotAllowedException(FeignException.MethodNotAllowed ex) {
        log.error("허용되지 않는 메서드: {}", ex.getMessage());

        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_METHOD_NOT_ALLOWED_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }

    /**
     * 서버 오류: 503 Service Unavailable
     */
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<ErrorResponse> handleServiceUnavailableException(FeignException.ServiceUnavailable ex) {
        log.error("서비스 일시 중단: {}", ex.getMessage());

        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_SERIALIZATION_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }

    /**
     * 재시도 가능한 예외 처리
     */
    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ErrorResponse> handleRetryableException(RetryableException ex) {
        log.error("재시도 가능한 오류 발생: {}", ex.getMessage());
        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_RETRY_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }

    /**
     * 기본 FeignException 처리 (위에서 처리되지 않은 모든 Feign 예외)
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
        log.error("Feign 클라이언트 오류: {}", ex.getMessage());
        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_COMMUNICATION_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }

    /**
     * 응답 디코딩 오류
     */
    @ExceptionHandler(feign.codec.DecodeException.class)
    public ResponseEntity<ErrorResponse> handleDecodeException(feign.codec.DecodeException ex) {
        log.error("응답 디코딩 오류: {}", ex.getMessage());
        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_DECODING_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, httpStatusOK);
    }
}
