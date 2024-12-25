package com.adjh.springbootwebsocket.controller;

import com.adjh.springbootwebsocket.dto.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocket 데이터를 처리를 수행한 Controller입니다.
 *
 * @author : jonghoon
 * @fileName : ChatController
 * @since : 8/15/24
 */
@RestController
public class ChatController {
    private final SimpMessagingTemplate template;       // 특정 사용자에게 메시지를 보내는데 사용되는 STOMP을 이용한 템플릿입니다.

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * Message 엔드포인트로 데이터와 함께 호출을 하면 "/sub/message"를 수신하는 사용자에게 메시지를 전달합니다.
     *
     * @param chatMessageDto
     * @return
     */
    @MessageMapping("/messages")
    public ChatMessageDto sendToMessage(@RequestBody ChatMessageDto chatMessageDto) {
        template.convertAndSend("/sub/message", chatMessageDto);       // 구독중인 모든 사용자에게 메시지를 전달합니다.
        return chatMessageDto;
    }
}
