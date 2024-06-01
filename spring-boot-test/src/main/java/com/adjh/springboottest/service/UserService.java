package com.adjh.springboottest.service;

import com.adjh.springboottest.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserService
 * @since : 5/31/24
 */
@Service
public interface UserService {
    List<UserDto> selectUserList(UserDto userDto);
}
