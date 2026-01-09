package com.adjh.springboot3vault.mapper;

import com.adjh.springboot3vault.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserMapper
 * @since : 26. 1. 8.
 */
@Repository
public interface UserMapper {

    UserDto findByUserSq(@Param("userSq") Long userSq);

    UserDto findByUserId(@Param("userId") String userId);

    List<UserDto> selectUser();

}
