package com.blog.springbootslack.controller;

import com.blog.springbootslack.model.dto.MessagesDto;
import com.blog.springbootslack.service.SlackWebhookService;
import com.blog.springbootslack.service.exams.ExamSlackWebhookTypeService;
import com.slack.api.webhook.WebhookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/slack")
public class SlackWebhookController {

    private final SlackWebhookService slackWebhookService;
    private final ExamSlackWebhookTypeService examSlackWebhookTypeService;

    public SlackWebhookController(SlackWebhookService slackWebhookService, ExamSlackWebhookTypeService examSlackWebhookTypeService) {
        this.slackWebhookService = slackWebhookService;
        this.examSlackWebhookTypeService = examSlackWebhookTypeService;
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

    /**
     * Slack 메시지 내에 블록 포함
     *
     * @param messageDto 서비스로 전달할 요청 객체
     * @return
     */
    @PostMapping("/exBlock")
    public ResponseEntity<Object> sendToBlockEx1(@RequestBody @Validated MessagesDto messageDto) {
        WebhookResponse wr = examSlackWebhookTypeService.sendToBlockEx1(messageDto.getMessage());
        return new ResponseEntity<>(wr, HttpStatus.OK);
    }

    /**
     * Slack 메시지 내에 블록 포함
     *
     * @param messageDto 서비스로 전달할 요청 객체
     * @return
     */
    @PostMapping("/exAttachment")
    public ResponseEntity<Object> sendToAttachmentEx1(@RequestBody @Validated MessagesDto messageDto) {
        WebhookResponse wr = examSlackWebhookTypeService.sendToAttachmentEx1(messageDto.getMessage());
        return new ResponseEntity<>(wr, HttpStatus.OK);
    }


}
