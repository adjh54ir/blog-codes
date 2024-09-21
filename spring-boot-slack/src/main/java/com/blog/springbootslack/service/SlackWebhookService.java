package com.blog.springbootslack.service;

import com.blog.springbootslack.model.enums.MessageTypeEnum;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Attachment;
import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.ButtonElement;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Slack Webhook 서비스
 */
@Service
public class SlackWebhookService {

    @Value("${slack.webhook.url}")
    private String webhookUrl;                          // application.properties 내에서 정의한 환경 정보를 가져옵니다.

    private final Slack slack = Slack.getInstance();    // Slack 인스턴스를 생성합니다.


    /**
     * 간단한 Slack 메시지 전송 서비스
     *
     * @param paramText 전송 메시지
     * @return Slack 응답 값
     */
    public WebhookResponse sendToSimpleText(String paramText) {
        Payload payload = Payload.builder().text(paramText).build();        // Slack에 전달할 Payload 구성
        WebhookResponse response;
        try {
            response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
            // WebhookResponse(code=200, message=OK, body=ok)
            System.out.println(response);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
