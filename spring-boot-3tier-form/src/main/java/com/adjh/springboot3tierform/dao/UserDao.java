package com.adjh.springboot3tierform.dao;

import com.adjh.springboot3tierform.model.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserDao
 * @since : 5/15/24
 */
@Repository
public interface UserDao {

    List<UserDto> selectUserList(UserDto userDto);

    int insertUser(UserDto userDto);

    int updateUser(UserDto userDto);

    int deleteUser(UserDto userDto);

}
