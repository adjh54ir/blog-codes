package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.model.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserService
 * @since : 2024. 12. 19.
 */
@Service
public interface UserService {

    Mono<Integer> userRegister(UserEntity userEntity);

    Mono<UserEntity> findUserByUserId(String userId);
}
