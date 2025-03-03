package com.blog.springbootkeycloak.dto.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCode {
    private String code;        // 성공 코드의 '코드 메시지'를 반환한다.
    private String message;     // 성공 코드의 '코드 상태'를 반환한다.
    private int status;

    @Builder
    ResponseCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
