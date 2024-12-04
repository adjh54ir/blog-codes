package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.dto.UserDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : FluxService.java
 * @since : 2024. 12. 4.
 */
@Service
public interface FluxService {

    Flux<UserDto> userList();
}
