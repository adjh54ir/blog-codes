package com.adjh.springboot3security.service.impl;


import com.adjh.springboot3security.model.dto.UserDetailsDto;
import com.adjh.springboot3security.model.dto.UserDto;
import com.adjh.springboot3security.service.UserService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService us) {
        this.userService = us;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) {
        UserDto userDto = UserDto
                .builder()
                .userId(userId)
                .build();

        // 사용자 정보가 존재하지 않는 경우
        if (userId == null || userId.isEmpty()) {
            return userService.login(userDto)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
                    .orElseThrow(() -> new AuthenticationServiceException(userId));
        }
        // 비밀번호가 맞지 않는 경우
        else {
            return userService.login(userDto)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
                    .orElseThrow(() -> new BadCredentialsException(userId));
        }
    }

}
