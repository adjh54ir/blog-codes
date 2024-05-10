package com.adjh.springbootjpa.dao;

import com.adjh.springbootjpa.dto.UserClubDto;
import com.adjh.springbootjpa.dto.UserDto;
import com.adjh.springbootjpa.dto.UserOrderDto;
import com.adjh.springbootjpa.dto.UserPassportDto;
import com.adjh.springbootjpa.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserDao
 * @since : 2/9/24
 */
@Repository
public interface UserDao {

    List<UserEntity> selectUserList(UserEntity userEntity);

    UserEntity selectUserBySq(UserEntity userEntity);

    int insertUser(UserEntity userEntity);

    int updateUser(UserEntity userEntity);

    int deleteUser(UserEntity userEntity);

    UserPassportDto selectUserPassport(UserDto userDto);

    List<UserOrderDto> selectUserOrderList(UserDto userDto);

    List<UserClubDto> selectUserClubList(UserDto userDto);

    List<UserClubDto> selectUserClubListRight(UserDto userDto);

    List<UserEntity> selectUserInfo(UserDto userDto);

}
