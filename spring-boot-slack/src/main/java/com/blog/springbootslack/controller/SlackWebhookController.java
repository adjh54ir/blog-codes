package com.blog.springbootslack.controller;

import com.blog.springbootslack.dto.MessagesDto;
import com.blog.springbootslack.service.SlackWebhookService;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.webhook.WebhookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/slack")
public class SlackWebhookController {

    private final SlackWebhookService slackWebhookService;

    public SlackWebhookController(SlackWebhookService slackWebhookService) {
        this.slackWebhookService = slackWebhookService;
    }

    /**
     * 간단한 Slack 메시지 전송
     *
     * @param messageDto 서비스로 전달할 요청 객체
     * @return
     */
    @PostMapping("/message")
    public ResponseEntity<Object> sendToMessage(@RequestBody MessagesDto messageDto) {
        WebhookResponse wr = slackWebhookService.sendToSimpleText(messageDto.getMessage());
        return new ResponseEntity<>(wr, HttpStatus.OK);
    }


}
