package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.model.dto.UserDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : FluxService.java
 * @since : 2024. 12. 4.
 */
@Service
public interface FluxService {

    Flux<UserEntity> findTbUserByUserNm(String userNm);
}
