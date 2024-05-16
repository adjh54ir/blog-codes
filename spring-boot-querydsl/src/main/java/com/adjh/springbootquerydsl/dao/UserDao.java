package com.adjh.springbootquerydsl.dao;

import com.adjh.springbootquerydsl.dto.UserClubDto;
import com.adjh.springbootquerydsl.dto.UserDto;
import com.adjh.springbootquerydsl.dto.UserOrderDto;
import com.adjh.springbootquerydsl.dto.UserPassportDto;
import com.adjh.springbootquerydsl.entity.UserEntity;
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

    List<UserEntity> selectUserClubAllList(UserDto userDto);

    List<UserDto> selectExistOrderUser(UserDto userDto);

}
