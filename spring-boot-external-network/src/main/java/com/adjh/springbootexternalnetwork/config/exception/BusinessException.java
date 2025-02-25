package com.adjh.springbootexternalnetwork.config.exception;

import com.adjh.springbootexternalnetwork.dto.common.ErrorCode;
import lombok.Builder;
import lombok.Getter;

/**
 * Bussiness 발생하는 에러들을 관리하는 Handler
 *
 * @author : jonghoon
 * @fileName : BusinessException
 * @since : 25. 2. 20.
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Builder
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
