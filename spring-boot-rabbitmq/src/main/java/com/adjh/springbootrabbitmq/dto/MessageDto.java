package com.adjh.springbootrabbitmq.dto;

import lombok.*;
import org.springframework.amqp.core.MessageProperties;

/**
 * 메시지 정보를 관리합니다.
 *
 * @author : jonghoon
 * @fileName : MessageDto
 * @since : 10/15/23
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageDto {
    private String title;
    private String message;
    private MessageProperties messageProperties;
    private byte[] messageByte;

    @Builder
    public MessageDto(String title, String message, MessageProperties messageProperties, byte[] messageByte) {
        this.title = title;
        this.message = message;
        this.messageProperties = messageProperties;
        this.messageByte = messageByte;
    }
}