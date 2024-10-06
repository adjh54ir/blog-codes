package com.adjh.springboot3security.mapper;


import com.adjh.springboot3security.model.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 관련 SQL Mapper 인터페이스 입니다.
 *
 * @author : jonghoon
 * @fileName : WebConfig
 * @since : 10/1/24
 */
@Repository
public interface UserMapper {

    Optional<UserDto> login(UserDto userDto);

    List<UserDto> selectUserList(UserDto userDto);

}
