package com.adjh.springboot3vault.service;

import com.adjh.springboot3vault.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserService
 * @since : 26. 1. 8.
 */
@Service
public interface UserService {
    UserDto findByUserSq(@Param("userSq") Long userSq);

    UserDto findByUserId(@Param("userId") String userId);

    List<UserDto> selectUser();
}
