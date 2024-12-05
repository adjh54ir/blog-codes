package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.model.dto.UserDto;
import com.blog.springbootwebflux.service.MonoService;
import org.springframework.stereotype.Service;
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

    @Override
    public Mono<UserDto> getUserByUserId(String userId) {

        // 빈 Mono 생성
        Mono<String> emptyMono = Mono.empty();

        // 값으로 Mono 생성
        Mono<String> mono = Mono.just("Hello");

        // 에러로 Mono 생성
        Mono<String> errorMono = Mono.error(new RuntimeException("Error"));

        // defer를 사용한 지연 생성
        Mono<String> deferredMono = Mono.defer(() -> Mono.just("Deferred Value"));
        return null;
    }
}
