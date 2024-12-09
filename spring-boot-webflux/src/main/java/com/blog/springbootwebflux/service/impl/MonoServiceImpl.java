package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.model.dto.UserDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.repository.UserRepository;
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

    public MonoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
