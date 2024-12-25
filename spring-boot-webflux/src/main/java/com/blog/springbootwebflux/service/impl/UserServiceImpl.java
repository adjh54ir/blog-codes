package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.model.dto.MailTxtSendDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.repository.UserRepository;
import com.blog.springbootwebflux.service.EmailService;
import com.blog.springbootwebflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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

        // [Service] 사용자 아이디를 조회합니다.
        return this.findUserByUserId(userEntity.getUserId())

                // [CASE1] 아이디가 존재하면, 0의 결과값을 반환합니다.
                .flatMap(existingUser -> Mono.just(0))  // 이미 존재하는 사용자인 경우 0 반환

                // [CASE2] 아이디가 존재하지 않는다면, 사용자를 등록합니다.
                .switchIfEmpty(

                        // [Service] 사용자를 등록합니다.
                        userRepository.save(userEntity)
                                .flatMap(savedUser -> {
                                    MailTxtSendDto mailDto = MailTxtSendDto.builder()
                                            .emailAddr(savedUser.getUserEmail())
                                            .subject(savedUser.getUserId() + "님 회원가입을 축하합니다!")
                                            .content("환영합니다. 회원가입이 완료되었습니다.")
                                            .build();
                                    // 이메일 전송을 별도로 실행하고 결과를 기다리지 않음
                                    emailService.sendTxtEmail(mailDto)
                                            .subscribe(
                                                    null,
                                                    error -> log.error("이메일 전송 실패: {}", error.getMessage())
                                            );

                                    // 즉시 성공 응답 반환
                                    return Mono.just(1);
                                })
                )
                // [CASE3] 회원가입 실패시, 오류메시지와 0의 값을 반환합니다.
                .onErrorResume(e -> {
                    log.debug("회원가입 처리 중 오류 발생: {}", e.getMessage());
                    return Mono.just(0);  // 에러 발생 시 0 반환
                });
    }

    @Override
    @Transactional(readOnly = true)
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

    @Override
    @Transactional(readOnly = true)
    public Flux<UserEntity> findTbUserByUserNm(String userNm) {
        System.out.println("[+] findTbUserByUserNm 실행 ....");
        Flux<UserEntity> userInfo = userRepository.findTbUserByUserNm(userNm);

        userInfo.subscribe(
                data -> System.out.println("User data: " + data),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        System.out.println("findTbUserByUserNm :: " + userInfo.toString());

        return userInfo;
    }
}
