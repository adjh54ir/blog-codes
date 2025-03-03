package com.blog.springbootkeycloak.dto.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [공통 코드] API 통신에 대한 '에러 코드'를 Enum 형태로 관리를 한다.
 * Global Error CodeList : 전역으로 발생하는 에러코드를 관리한다.
 * Custom Error CodeList : 업무 페이지에서 발생하는 에러코드를 관리한다
 * Error Code Constructor : 에러코드를 직접적으로 사용하기 위한 생성자를 구성한다.
 * OpenFeign Error CodeList : OpenFeign 통신 중 발생하는 에러코드를 관리한다.
 *
 * @author : jonghoon
 * @fileName : ErrorCodeDto
 * @since : 25. 2. 20.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {

    /*
     * ******************************* Global Error CodeList ***************************************
     * IN Server HTTP Status Code
     * 400 : Bad Request
     * 401 : Unauthorized
     * 403 : Forbidden
     * 404 : Not Found
     * 500 : Internal Server Error
     * *********************************************************************************************
     */

    /************************************************************************************************
     ********************************* HTTP Status Error *********************************************
     * HTTP 통신 과정에서 발생하는 오류에 대해서 코드 기반으로 관리합니다.
     *************************************************************************************************/
    // [400] 잘못된 서버 요청
    BAD_REQUEST_ERROR(400, "S001", "Bad Request Exception"),

    // [401] 인증되지 않은 요청
    UNAUTHORIZED_ERROR(401, "S002", "Unauthorized request occurred Exception"),

    // [403] 권한이 없음
    FORBIDDEN_ERROR(403, "S003", "Forbidden Exception"),

    // [404] 서버로 요청한 리소스가 존재하지 않음
    NOT_FOUND_ERROR(404, "S004", "Not Found Exception"),

    // [405] 지원하지 않는 HTTP 메소드 오류
    METHOD_NOT_ALLOWED_ERROR(405, "S005", "The requested HTTP method is not allowed for this endpoint Exception"),

    // [500] 서버 내부 오류
    INTERNAL_SERVER_ERROR(500, "S006", "Internal Server Error Exception"),

    /************************************************************************************************
     ********************************* Custom Transaction Error  ************************************
     * 비즈니스 로직 내에서 트랜잭션을 수행했을때 오류가 발생하는 부분에 대해 코드 기반으로 관리합니다.
     *************************************************************************************************/

    // Business Exception Error
    BUSINESS_EXCEPTION_ERROR(500, "T001", "Business Exception Error"),

    // Transaction Insert Error
    INSERT_ERROR(500, "T002", "Insert Transaction Error Exception"),

    // Transaction Update Error
    UPDATE_ERROR(500, "T003", "Update Transaction Error Exception"),

    // Transaction Delete Error
    DELETE_ERROR(500, "T004", "Delete Transaction Error Exception"),


    /************************************************************************************************
     ********************************* Custom Business Error *****************************************
     * 비즈니스 로직에서 발생하는 오류들을 코드기반으로 관리합니다
     *************************************************************************************************/

    // 요청 본문 누락 오류
    REQUEST_BODY_MISSING_ERROR(400, "G002", "Required request body is missing Exception"),

    // 타입 변환 오류
    INVALID_TYPE_VALUE(400, "G003", "Invalid Type Value Exception"),

    // 요청 파라미터 누락 오류
    MISSING_REQUEST_PARAMETER_ERROR(400, "G004", "Missing Servlet RequestParameter Exception"),

    // 입출력 처리 오류
    IO_ERROR(500, "G008", "I/O Exception"),

    // JSON 파싱 오류 (Gson)
    JSON_PARSE_ERROR(400, "G009", "JsonParseException"),

    // Jackson 처리 오류
    JACKSON_PROCESS_ERROR(500, "G010", "com.fasterxml.jackson.core Exception"),

    // Null 참조 오류
    NULL_POINT_ERROR(500, "G006", "Null Point Exception"),

    // 요청 값 유효성 검증 실패
    NOT_VALID_ERROR(400, "G007", "handle Validation Exception"),

    // 헤더 값 유효성 검증 실패
    NOT_VALID_HEADER_ERROR(400, "G007", "Header에 데이터가 존재하지 않는 경우"),

    // 인증 정보 누락
    AUTH_IS_NULL_ERROR(401, "A404", "AUTH_IS_NULL"),

    // 인증 토큰 실패
    AUTH_TOKEN_FAIL_ERROR(401, "A405", "AUTH_TOKEN_FAIL"),

    // 유효하지 않은 인증 토큰
    AUTH_TOKEN_INVALID_ERROR(401, "A406", "AUTH_TOKEN_INVALID"),

    // 인증 토큰 불일치
    AUTH_TOKEN_NOT_MATCH_ERROR(401, "A407", "AUTH_TOKEN_NOT_MATCH"),

    // 인증 토큰 누락
    AUTH_TOKEN_IS_NULL_ERROR(401, "A408", "AUTH_TOKEN_IS_NULL"),

    /************************************************************************************************
     ********************************* OpenFeign Error CodeList **************************************
     *************************************************************************************************/

    // [400] 잘못된 요청 형식 오류
    FEIGN_BAD_REQUEST_ERROR(400, "F004", "OpenFeign bad request format Exception"),

    // [401] 인증 실패 오류
    FEIGN_UNAUTHORIZED_ERROR(401, "F005", "OpenFeign unauthorized access Exception"),

    // [403] 권한 부족 오류
    FEIGN_FORBIDDEN_ERROR(403, "F003", "OpenFeign forbidden access Exception"),

    // [404] 리소스 찾을 수 없음 오류
    FEIGN_NOT_FOUND_ERROR(404, "F004", "OpenFeign not found Exception"),

    // [405] 지원하지 않는 HTTP 메소드 오류
    FEIGN_METHOD_NOT_ALLOWED_ERROR(405, "F005", "OpenFeign method not allowed Exception"),

    // [499] 요청 취소 오류
    FEIGN_REQUEST_CANCELLED_ERROR(499, "F006", "OpenFeign request cancelled Exception"),

    // [400] 데이터 변환 오류
    FEIGN_SERIALIZATION_ERROR(400, "F007", "OpenFeign data serialization/deserialization Exception"),

    // [500] 서버 내부 오류
    FEIGN_SERVER_ERROR(500, "F008", "OpenFeign server error during request processing"),

    // [500] 서비스 간 통신 오류
    FEIGN_COMMUNICATION_ERROR(500, "F009", "OpenFeign inter-service communication Exception"),

    // [500] 재시도 실패 오류
    FEIGN_RETRY_ERROR(500, "F010", "OpenFeign retry Exception"),

    // [500] 응답 디코딩 오류
    FEIGN_DECODING_ERROR(500, "F011", "OpenFeign decoding Exception"),

    // [503] 연결 타임아웃 오류
    FEIGN_CONNECTION_TIMEOUT_ERROR(503, "F012", "OpenFeign connection timeout Exception"),

    // [500] 응답 처리 오류
    FEIGN_RESPONSE_ERROR(500, "F013", "OpenFeign response processing Exception"),

    // [503] 서비스 이용 불가 오류
    FEIGN_SERVICE_UNAVAILABLE_ERROR(503, "F014", "OpenFeign service unavailable Exception"),

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