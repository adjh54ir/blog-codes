package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.dto.UserDto;
import com.blog.springbootwebflux.service.FluxService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : FluxServiceImpl.java
 * @since : 2024. 12. 4.
 */
@Service
public class FluxServiceImpl implements FluxService {
    @Override
    public Flux<UserDto> userList() {
        return null;
    }
}
