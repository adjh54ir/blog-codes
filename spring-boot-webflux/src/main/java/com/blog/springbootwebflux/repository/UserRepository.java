package com.blog.springbootwebflux.repository;

import com.blog.springbootwebflux.model.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

    Mono<UserEntity> findTbUserByUserId(String userId);

    Flux<UserEntity> findTbUserByUserNm(String userName);
}
