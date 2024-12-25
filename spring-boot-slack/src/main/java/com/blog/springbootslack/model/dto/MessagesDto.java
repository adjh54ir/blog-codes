package com.blog.springbootslack.model.dto;

import com.blog.springbootslack.model.enums.MessageTypeEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessagesDto {
    private String message;
    private String msgType;

    @Builder
    public MessagesDto(String message, String msgType) {
        this.message = message;
        this.msgType = msgType;
    }
}
