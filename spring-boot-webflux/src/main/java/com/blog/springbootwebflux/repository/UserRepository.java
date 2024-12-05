package com.blog.springbootwebflux.repository;

import com.blog.springbootwebflux.model.dto.UserDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserRepository
 * @since : 2024. 12. 5.
 */
public interface UserRepository extends ReactiveCrudRepository<UserDto, Long> {

    Flux<UserDto> findTbUserByUserId(String userId);

    Mono<UserDto> findUserDtoByUserNm(String userName);
}
