package com.adjh.springboottest.dao;

import com.adjh.springboottest.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserDao
 * @since : 5/31/24
 */
@Repository
public interface UserDao {
    List<UserDto> selectUserList(UserDto userDto);
}
