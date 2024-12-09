package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.model.dto.UserDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : MonoService.java
 * @since : 2024. 12. 4.
 */
@Service
public interface MonoService {

    Mono<UserEntity> findUserByUserId(String userId);
}
