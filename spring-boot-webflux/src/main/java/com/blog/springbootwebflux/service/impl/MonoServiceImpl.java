package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.model.dto.MailTxtSendDto;
import com.blog.springbootwebflux.model.dto.UserDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.repository.UserRepository;
import com.blog.springbootwebflux.service.EmailService;
import com.blog.springbootwebflux.service.MonoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : MonoServiceImpl.java
 * @since : 2024. 12. 4.
 */
@Service
public class MonoServiceImpl implements MonoService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public MonoServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public Mono<UserEntity> findUserByUserId(String userId) {
        System.out.println("[+] findUserByUserId 실행 ....");

        return userRepository.findTbUserByUserId(userId)
                .doOnNext(user -> {
                    MailTxtSendDto mailTxtSendDto = MailTxtSendDto.builder()
                            .emailAddr(user.getUserEmail())
                            .subject("회원가입을 축하합니다,")
                            .content("아주 축하합니다.")
                            .build();
                    emailService.sendTxtEmail(mailTxtSendDto)
                            .subscribe();
                }); // 비동기적으로 이메일 전송
    }
}
