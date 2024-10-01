package com.adjh.springboot3security.service;


import com.adjh.springboot3security.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> login(UserDto userVo);

    List<UserDto> selectUserList(UserDto userDto);
}
