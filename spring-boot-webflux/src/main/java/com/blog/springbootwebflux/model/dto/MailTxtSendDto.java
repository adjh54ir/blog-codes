package com.blog.springbootwebflux.model.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : MailTxtSendDto
 * @since : 2024. 12. 19.
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
