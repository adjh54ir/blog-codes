package com.blog.springbootslack.service.exams;

import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.ButtonElement;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Slack 메시지 전송을 위해 사용되는 다양한 타입들에 대한 예시들을 담고 있습니다.
 *
 * @author : jonghoon
 * @fileName : ExamSlackWebhookTypeService
 * @since : 9/21/24
 */
@Service
public class ExamSlackWebhookTypeService {

    @Value("${slack.webhook.url}")
    private String webhookUrl;                          // application.properties 내에서 정의한 환경 정보를 가져옵니다.

    private final Slack slack = Slack.getInstance();    // Slack 인스턴스를 생성합니다.

    /**
     * 이모지 사용예시
     *
     * @param paramText
     * @return
     */
    public WebhookResponse sendToEmojiEx1(String paramText) {
        WebhookResponse response;
        Payload payload = Payload.builder()
                .text("안녕하세요! :smile:")
                .build();

        try {
            response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 링크 사용예시
     *
     * @param paramText
     * @return
     */
    public WebhookResponse sendToLinkEx1(String paramText) {
        WebhookResponse response;
        Payload payload = Payload.builder()
                .text("자세한 내용은 <https://adjh54.tistory.com/|Contributor9 블로그>를 참조하세요.")
                .build();

        try {
            response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 멘션 사용예시
     *
     * @param paramText
     * @return
     */
    public WebhookResponse sendToMentionEx1(String paramText) {
        WebhookResponse response;
        Payload payload = Payload.builder()
                .text("안녕하세요, <@adjh54ir>! 채널 <#C07MVNXCUJK|general>을 확인해주세요.")
                .build();

        try {
            response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 코드 블록 예시
     *
     * @param paramText
     * @return
     */
    public WebhookResponse sendToCodeBlockEx1(String paramText) {
        WebhookResponse response;
        Payload payload = Payload.builder()
                .text("```\npublic class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}\n```")
                .build();

        try {
            response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 블록 사용예시
     * - 메시지 내에 버튼을 추가하고 링크를 연결함.
     *
     * @param paramText
     * @return
     */
    public WebhookResponse sendToBlockEx1(String paramText) {
        WebhookResponse response;
        Payload payload = Payload
                .builder()
                .blocks(Arrays.asList(
                        SectionBlock.builder().text(MarkdownTextObject.builder().text("버튼을 눌러주세요:").build()).build(),
                        ActionsBlock.builder().elements(
                                Arrays.asList(ButtonElement.builder()
                                        .text(PlainTextObject.builder().text("클릭").build())
                                        .actionId("button_click")
                                        .url("https://adjh54.tistory.com/")
                                        .build()
                                )).build()
                ))
                .build();
        try {
            response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 첨부 파일을 포함한 예시
     *
     * @param paramText
     * @return
     */
    public WebhookResponse sendToAttachmentEx1(String paramText) {
        WebhookResponse response;
        Payload payload = Payload.builder()
                .text("메시지와 함께 첨부 파일")
                .attachments(Collections.singletonList(
                        Attachment.builder()
                                .fallback("이미지를 불러올 수 없습니다.")
                                .imageUrl("https://avatars.githubusercontent.com/u/70501374?v=4")
                                .build()
                ))
                .build();
        try {
            response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
