package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.model.dto.MailTxtSendDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.repository.UserRepository;
import com.blog.springbootwebflux.service.EmailService;
import com.blog.springbootwebflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserServiceImpl
 * @since : 2024. 12. 19.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    @Transactional
    @Override
    public Mono<Integer> userRegister(UserEntity userEntity) {
        return this.findUserByUserId(userEntity.getUserId())
                .flatMap(existingUser -> Mono.just(0))  // 이미 존재하는 사용자인 경우 0 반환
                .switchIfEmpty(
                        userRepository.save(userEntity)
                                .flatMap(savedUser -> {
                                    // 이메일 전송을 별도의 비동기 스트림으로 처리
                                    MailTxtSendDto mailDto = MailTxtSendDto.builder()
                                            .emailAddr(savedUser.getUserEmail())
                                            .subject("회원가입을 축하합니다!")
                                            .content("환영합니다. 회원가입이 완료되었습니다.")
                                            .build();
                                    return emailService.sendTxtEmail(mailDto)
                                            .then(Mono.just(1))
                                            .onErrorResume(error -> {
                                                log.error("이메일 전송 실패: {}", error.getMessage());
                                                return Mono.just(1);  // 이메일 실패해도 회원가입은 성공
                                            });
                                })
                )
                .onErrorResume(e -> {
                    log.debug("회원가입 처리 중 오류 발생: {}", e.getMessage());
                    return Mono.just(0);  // 에러 발생 시 0 반환
                });
    }

    @Override
    public Mono<UserEntity> findUserByUserId(String userId) {
        System.out.println("[+] findUserByUserId 실행 ....");

        Mono<UserEntity> userInfo = userRepository.findTbUserByUserId(userId);
        userInfo.subscribe(
                data -> System.out.println("User data: " + data),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );
        System.out.println("findUserByUserId :: " + userInfo.toString());

        return userInfo;
    }
}
