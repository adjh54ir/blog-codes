package com.adjh.springbootmail.controller;

import com.adjh.springbootmail.dto.MailHtmlSendDto;
import com.adjh.springbootmail.dto.MailTxtSendDto;
import com.adjh.springbootmail.service.MailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : MailSendController
 * @since : 11/11/24
 */
@Controller
@RequestMapping("api/v1/email")
public class MailSendController {

    private final MailSendService mailSendService;

    public MailSendController(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }

    /**
     * 텍스트 이메일을 전송합니다.
     *
     * @param mailTxtSendDto
     * @return
     */
    @PostMapping("/txtEmail")
    public ResponseEntity<Object> sendTxtEmail(@RequestBody MailTxtSendDto mailTxtSendDto) {
        mailSendService.sendTxtEmail(mailTxtSendDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * HTML 구성 기반 이메일을 전송합니다.
     *
     * @param mailHtmlSendDto
     * @return
     */
    @PostMapping("/htmlEmail")
    public ResponseEntity<Object> sendHtmlEmail(@RequestBody MailHtmlSendDto mailHtmlSendDto) {
        mailSendService.sendHtmlEmail(mailHtmlSendDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
