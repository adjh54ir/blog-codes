package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.model.dto.UserDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.repository.UserRepository;
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

    private final UserRepository userRepository;

    public FluxServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<UserEntity> findTbUserByUserNm(String userNm) {
        System.out.println("[+] findTbUserByUserNm 실행 ....");
        Flux<UserEntity> userInfo = userRepository.findTbUserByUserNm(userNm);

        userInfo.subscribe(
                data -> System.out.println("User data: " + data),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        System.out.println("findTbUserByUserNm :: " + userInfo.toString());

        return userInfo;
    }
}
