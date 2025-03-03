package com.blog.springbootkeycloak.dto.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * OpenFeign 에러 응답 처리 클래스
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;             // 상태 코드
    private String errorCode;       // 에러 구분 코드
    private String errorMsg;        // 에러 메시지
    private String errorDtlMsg;     // 에러 상세 메시지


    /**
     * 기본 생성자 - ErrorCode만 받는 경우
     *
     * @param code
     */
    @Builder
    protected ErrorResponse(final ErrorCode code) {
        this.errorMsg = code.getMessage();
        this.status = code.getStatus();
        this.errorCode = code.getCode();
    }


    /**
     * 생성자 - ErrorCode와 reason을 받는 경우
     *
     * @param code
     * @param reason
     */
    @Builder
    protected ErrorResponse(final ErrorCode code, final String reason) {
        this.errorMsg = code.getMessage();
        this.status = code.getStatus();
        this.errorCode = code.getCode();
        this.errorDtlMsg = reason;
    }


    /**
     * 생성자 - ErrorCode와 필드 에러 목록을 받는 경우
     *
     * @param code
     * @param errors
     */
    @Builder
    protected ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
        this.errorMsg = code.getMessage();
        this.status = code.getStatus();
        this.errorCode = code.getCode();
    }

    /**
     * 팩토리 메소드 - BindingResult에서 ErrorResponse 생성
     *
     * @param code
     * @param bindingResult
     * @return
     */
    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }


    /**
     * 팩토리 메소드 - BindingResult에서 ErrorResponse 생성
     *
     * @param code
     * @return
     */
    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    /**
     * 팩토리 메소드 - ErrorCode와 상세 이유로 ErrorResponse 생성
     *
     * @param code
     * @param reason
     * @return
     */
    public static ErrorResponse of(final ErrorCode code, final String reason) {
        return new ErrorResponse(code, reason);
    }

    /**
     * 유효성 검증 오류 정보를 담는 내부 클래스
     */
    @Getter
    public static class FieldError {
        private final String field;     // 오류 필드명
        private final String value;     // 오류 값
        private final String reason;    // 오류 이유

        // 단일 필드 오류 생성
        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        // BindingResult에서 필드 오류 목록 추출
        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .toList();
        }

        @Builder
        FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }
}