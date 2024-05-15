package com.adjh.springboot3tierform.service;

import com.adjh.springboot3tierform.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserService
 * @since : 5/15/24
 */
@Service
public interface UserService {

    List<UserDto> selectUserList(UserDto userDto);

    int insertUser(UserDto userDto);

    int updateUser(UserDto userDto);

    int deleteUser(UserDto userDto);
}
