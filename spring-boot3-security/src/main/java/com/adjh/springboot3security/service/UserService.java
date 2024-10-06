package com.adjh.springboot3security.service;


import com.adjh.springboot3security.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 정보를 조회해오기 위한 인터페이스입니다.
 */
public interface UserService {
    Optional<UserDto> login(UserDto userVo);

    List<UserDto> selectUserList(UserDto userDto);
}
