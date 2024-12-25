package com.spring.security.mapper;


import com.spring.security.model.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMapper {

    Optional<UserDto> login(UserDto userDto);

    List<UserDto> selectUserList(UserDto userDto);

}
