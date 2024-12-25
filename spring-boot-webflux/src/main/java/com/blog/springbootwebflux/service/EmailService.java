package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.model.dto.MailTxtSendDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : EmailService
 * @since : 2024. 12. 19.
 */
@Service
public interface EmailService {

    Mono<Void> sendTxtEmail(MailTxtSendDto mailTxtSendDto);       // SimpleMailMessage를 활용하여 텍스트 기반 메일을 전송합니다.
}
