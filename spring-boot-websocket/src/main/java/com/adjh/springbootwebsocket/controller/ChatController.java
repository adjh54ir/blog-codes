package com.adjh.springbootwebsocket.controller;

import com.adjh.springbootwebsocket.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ChatController
 * @since : 8/15/24
 */
@RestController
public class ChatController {
    private final SimpMessagingTemplate template;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/messages")
    public ChatMessage send2(@RequestBody ChatMessage chatMessage) {
        System.out.println("결과 값은 :: " + chatMessage.toString());
//        template.convertAndSend("/sub/message", chatMessage.getContent());       // 구독중인 모든 사용자에게 메시지를 전달합니다.
        System.out.println("결과 ::" + chatMessage.getContent());
        return chatMessage;
    }

}
