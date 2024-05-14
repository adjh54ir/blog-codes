package model.common;

import lombok.Getter;

/**
 * [공통 코드] API 통신에 대한 '에러 코드'를 Enum 형태로 관리를 한다.
 * Success CodeList : 성공 코드를 관리한다.
 * Success Code Constructor: 성공 코드를 사용하기 위한 생성자를 구성한다.
 *
 * @author lee
 */
@Getter
public enum SuccessCode {

    /**
     * ******************************* Success CodeList ***************************************
     */
    // 조회 성공 코드 (HTTP Response: 200 OK)
    SELECT_SUCCESS(200, "200", "SELECT SUCCESS"),
    // 삭제 성공 코드 (HTTP Response: 200 OK)
    DELETE_SUCCESS(200, "200", "DELETE SUCCESS"),

    // 전송 성공 코드 (HTTP Response: 200 OK)
    SEND_SUCCESS(200, "200", "SEND SUCCESS"),


    // 삽입 성공 코드 (HTTP Response: 201 Created)
    INSERT_SUCCESS(201, "201", "INSERT SUCCESS"),
    // 수정 성공 코드 (HTTP Response: 201 Created)
    UPDATE_SUCCESS(204, "204", "UPDATE SUCCESS"),

    ; // End

    /**
     * ******************************* Success Code Constructor ***************************************
     */
    // 성공 코드의 '코드 상태'를 반환한다.
    private final int status;

    // 성공 코드의 '코드 값'을 반환한다.
    private final String code;

    // 성공 코드의 '코드 메시지'를 반환한다.s
    private final String message;

    // 생성자 구성
    SuccessCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
