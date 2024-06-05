package com.adjh.springbootrabbitmq.dto;

import lombok.*;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ErrorMessageDto
 * @since : 24. 6. 5.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorMessageDto {

    private String errorMsg;

    private String errorCode;

    private MessageDto messageDto;

    @Builder(toBuilder = true)
    public ErrorMessageDto(String errorMsg, String errorCode, MessageDto messageDto) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.messageDto = messageDto;
    }
}
