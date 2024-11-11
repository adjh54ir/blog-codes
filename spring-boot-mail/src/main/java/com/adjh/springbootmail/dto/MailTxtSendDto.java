package com.adjh.springbootmail.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * SimpleMailMessage 기반 텍스트 메일 전송 DTO
 *
 * @author : jonghoon
 * @fileName : MailTxtSendDto
 * @since : 11/11/24
 */
@Getter
public class MailTxtSendDto {

    private String emailAddr;                   // 수신자 이메일

    private String subject;                     // 이메일 제목

    private String content;                     // 이메일 내용

    @Builder
    public MailTxtSendDto(String emailAddr, String subject, String content) {
        this.emailAddr = emailAddr;
        this.subject = subject;
        this.content = content;
    }
}
