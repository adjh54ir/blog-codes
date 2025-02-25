package com.blog.springbootkeycloak.dto.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [공통 코드] API 통신에 대한 '에러 코드'를 Enum 형태로 관리를 한다.
 * Global Error CodeList : 전역으로 발생하는 에러코드를 관리한다.
 * Custom Error CodeList : 업무 페이지에서 발생하는 에러코드를 관리한다
 * Error Code Constructor : 에러코드를 직접적으로 사용하기 위한 생성자를 구성한다.
 *
 * @author : jonghoon
 * @fileName : ErrorCodeDto
 * @since : 25. 2. 20.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {

    /**
     * ******************************* Global Error CodeList ***************************************
     * HTTP Status Code
     * 400 : Bad Request
     * 401 : Unauthorized
     * 403 : Forbidden
     * 404 : Not Found
     * 500 : Internal Server Error
     * *********************************************************************************************
     */

    /* ***********************************************************************************************
     ********************************* HTTP Status Error ***************************************
     *************************************************************************************************/
    // 잘못된 서버 요청
    BAD_REQUEST_ERROR(400, "S001", "Bad Request Exception"),

    // 인증되지 않은 요청
    UNAUTHORIZED_ERROR(401, "S002", "Unauthorized request occurred Exception"),

    // 권한이 없음
    FORBIDDEN_ERROR(403, "S003", "Forbidden Exception"),

    // 서버로 요청한 리소스가 존재하지 않음
    NOT_FOUND_ERROR(404, "S004", "Not Found Exception"),

    // Method Not Allowed error
    METHOD_NOT_ALLOWED_ERROR(405, "S005", "The requested HTTP method is not allowed for this endpoint Exception"),

    // 서버가 처리 할 방법을 모르는 경우 발생
    INTERNAL_SERVER_ERROR(500, "S006", "Internal Server Error Exception"),


    /************************************************************************************************
     ********************************* Custom Transaction Error  ***************************************
     *************************************************************************************************/

    // Business Exception Error
    BUSINESS_EXCEPTION_ERROR(200, "T001", "Business Exception Error"),

    // Transaction Insert Error
    INSERT_ERROR(200, "T002", "Insert Transaction Error Exception"),

    // Transaction Update Error
    UPDATE_ERROR(200, "T003", "Update Transaction Error Exception"),

    // Transaction Delete Error
    DELETE_ERROR(200, "T004", "Delete Transaction Error Exception"),


    /* ***********************************************************************************************
     ********************************* Custom Error ***************************************
     *************************************************************************************************/

    // @RequestBody 데이터 미 존재
    REQUEST_BODY_MISSING_ERROR(400, "G002", "Required request body is missing Exception"),

    // 유효하지 않은 타입
    INVALID_TYPE_VALUE(400, "G003", " Invalid Type Value Exception"),

    // Request Parameter 로 데이터가 전달되지 않을 경우
    MISSING_REQUEST_PARAMETER_ERROR(400, "G004", "Missing Servlet RequestParameter Exception"),

    // 입력/출력 값이 유효하지 않음
    IO_ERROR(400, "G008", "I/O Exception"),

    // com.google.gson JSON 파싱 실패
    JSON_PARSE_ERROR(400, "G009", "JsonParseException"),

    // com.fasterxml.jackson.core Processing Error
    JACKSON_PROCESS_ERROR(400, "G010", "com.fasterxml.jackson.core Exception"),

    // NULL Point Exception 발생
    NULL_POINT_ERROR(404, "G006", "Null Point Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_ERROR(404, "G007", "handle Validation Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_HEADER_ERROR(404, "G007", "Header에 데이터가 존재하지 않는 경우 "),


    AUTH_IS_NULL(200, "A404", "AUTH_IS_NULL"),                // A404
    AUTH_TOKEN_FAIL(200, "A405", "AUTH_TOKEN_FAIL"),            // A405
    AUTH_TOKEN_INVALID(200, "A406", "AUTH_TOKEN_INVALID"),            // A406
    AUTH_TOKEN_NOT_MATCH(200, "A407", "AUTH_TOKEN_NOT_MATCH"),        // A407
    AUTH_TOKEN_IS_NULL(200, "A408", "AUTH_TOKEN_IS_NULL"),        // A408


    ; // End

    /**
     * ******************************* Error Code Constructor ***************************************
     */
    // 에러 코드의 '코드 상태'을 반환한다.
    private int status;

    // 에러 코드의 '코드간 구분 값'을 반환한다.
    private String code;

    // 에러 코드의 '코드 메시지'을 반환한다.
    private String message;

    // 생성자 구성
    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}