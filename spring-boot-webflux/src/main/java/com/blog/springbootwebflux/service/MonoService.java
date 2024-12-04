package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.dto.UserDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

    Mono<UserDto> getUserByUserId(String userId);
}
