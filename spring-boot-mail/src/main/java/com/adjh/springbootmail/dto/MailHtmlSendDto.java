package com.adjh.springbootmail.dto;

import lombok.Getter;

/**
 * MimeMessageHelper 기반의 HTML 메일 전송
 *
 * @author : jonghoon
 * @fileName : MailHtmlSendDto
 * @since : 11/11/24
 */
@Getter
public class MailHtmlSendDto {

    private String emailAddr;                   // 수신자 이메일

    private String subject;                     // 이메일 제목

    private String content;                     // 이메일 내용

    private String target;                      // 이메일 대상 타겟을 지정합니다.

    public MailHtmlSendDto(String emailAddr, String subject, String content, String target) {
        this.emailAddr = emailAddr;
        this.subject = subject;
        this.content = content;
        this.target = target;
    }
}
