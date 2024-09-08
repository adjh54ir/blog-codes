package com.adjh.springbootwebsocket.dto;

import lombok.Data;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ChatMessage
 * @since : 8/16/24
 */
@Data
public class ChatMessage {
    private String content;
    private String sender;

    public ChatMessage(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }
}
