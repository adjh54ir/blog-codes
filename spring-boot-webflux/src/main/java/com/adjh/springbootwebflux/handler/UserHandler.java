package com.adjh.springbootwebflux.handler;

import com.adjh.springbootwebflux.entity.UserEntity;
import com.adjh.springbootwebflux.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserHandler
 * @since : 6/8/24
 */
@Component
public class UserHandler {

    private static UserJpaRepository userJpaRepository;

    public UserHandler(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public Mono<ServerResponse> getUserInfoByUserId(ServerRequest request) {
        String userId = request.pathVariable("userId");
        UserEntity userEntity = userJpaRepository.findByUserId(userId);
        System.out.println("userEntity :: " + userEntity.toString());
        return userEntity != null ? ServerResponse.ok().bodyValue(userEntity) : ServerResponse.notFound().build();
    }

}
