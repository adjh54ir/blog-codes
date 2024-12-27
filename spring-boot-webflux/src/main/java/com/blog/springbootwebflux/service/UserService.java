package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.model.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 사용자의 비즈니스 로직을 처리하는 Service
 *
 * @author : leejonghoon
 * @fileName : UserService
 * @since : 2024. 12. 19.
 */
@Service
public interface UserService {

    Mono<Integer> userRegister(UserEntity userEntity);

    Mono<UserEntity> findUserByUserId(String userId);

    Flux<UserEntity> findTbUserByUserNm(String userNm);

    Mono<UserEntity> findById(String userId);
}
