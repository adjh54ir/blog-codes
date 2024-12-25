package com.blog.springbootslack.model.enums;

import lombok.Getter;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : MessageType
 * @since : 9/20/24
 */
@Getter
public enum MessageTypeEnum {
    EMOJI("emoji"),
    LINK("link"),
    MENTION("mention"),
    BLOCK("block"),
    CODE_BLOCK("codeBlock"),
    ATTACHMENT("attachment");

    private final String code;

    MessageTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
