package com.adjh.springbootmail.service.impl;

import com.adjh.springbootmail.dto.MailHtmlSendDto;
import com.adjh.springbootmail.dto.MailTxtSendDto;
import com.adjh.springbootmail.service.MailSendService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Base64;

/**
 * 이메일 전송 서비스의 구현체
 *
 * @author : jonghoon
 * @fileName : MailSendServiceImpl
 * @since : 11/11/24
 */
@Service
public class MailSendServiceImpl implements MailSendService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public MailSendServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Value("${spring.mail.username}")
    private String EMAIL_SENDER;


    /**
     * 텍스트 기반 메일 전송
     *
     * @param mailTxtSendDto
     */
    @Override
    public void sendTxtEmail(MailTxtSendDto mailTxtSendDto) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(mailTxtSendDto.getEmailAddr());               // 받는 사람 이메일
        smm.setFrom(EMAIL_SENDER);                              // [해당 부분 추가!!!] 보내는 사람 추가
        smm.setSubject(mailTxtSendDto.getSubject());            // 이메일 제목
        smm.setText(mailTxtSendDto.getContent());               // 이메일 내용
        try {
            mailSender.send(smm);                   // 메일 보내기
            System.out.println("이메일 전송 성공!");
        } catch (MailException e) {
            System.out.println("[-] 이메일 전송중에 오류가 발생하였습니다 " + e.getMessage());
            throw e;
        }
    }

    /**
     * html 기반 메일 전송
     *
     * @param mailHtmlSendDto
     */
    @Override
    public void sendHtmlEmail(MailHtmlSendDto mailHtmlSendDto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            Context context = new Context();
            context.setVariable("subject", mailHtmlSendDto.getSubject());
            context.setVariable("message", mailHtmlSendDto.getContent());
            if (mailHtmlSendDto.getTarget().equals("user")) {
                context.setVariable("userType", "일반 사용자");
            } else if (mailHtmlSendDto.getTarget().equals("admin")) {
                context.setVariable("userType", "관리자");
            }

            // MailSendServiceImpl.java 내부
            String base64Image = getBase64EncodedImage("static/images/logo.png");
            context.setVariable("logoImage", base64Image);

            String htmlContent = templateEngine.process("email-template", context);
            helper.setTo(mailHtmlSendDto.getEmailAddr());
            helper.setSubject(mailHtmlSendDto.getSubject());
            helper.setText(htmlContent, true);
            helper.setFrom(EMAIL_SENDER);
//            helper.setFrom(FROM_USER);
            mailSender.send(message);
            System.out.println("Thymeleaf 템플릿 이메일 전송 성공!");
        } catch (MessagingException e) {
            System.out.println("[-] Thymeleaf 템플릿 이메일 전송 중 오류 발생: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 이미지를 Base64로 인코딩하는 메서드
    private String getBase64EncodedImage(String imagePath) throws IOException {
        Resource resource = new ClassPathResource(imagePath);
        byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
        return Base64.getEncoder().encodeToString(bytes);
    }

}
