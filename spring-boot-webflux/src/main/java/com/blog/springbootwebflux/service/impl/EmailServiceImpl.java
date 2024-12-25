package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.model.dto.MailTxtSendDto;
import com.blog.springbootwebflux.service.EmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : EmailServiceImpl
 * @since : 2024. 12. 19.
 */
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Mono<Void> sendTxtEmail(MailTxtSendDto mailTxtSendDto) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(mailTxtSendDto.getEmailAddr());      // 받는 사람 이메일
        smm.setSubject(mailTxtSendDto.getSubject());            // 이메일 제목
        smm.setText(mailTxtSendDto.getContent());               // 이메일 내용
        try {
            mailSender.send(smm);                   // 메일 보내기
            System.out.println("이메일 전송 성공!");
        } catch (MailException e) {
            System.out.println("[-] 이메일 전송중에 오류가 발생하였습니다 " + e.getMessage());
            throw e;
        }
        return null;
    }
}
