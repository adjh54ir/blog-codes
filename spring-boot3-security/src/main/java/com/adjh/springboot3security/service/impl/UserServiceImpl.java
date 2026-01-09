package com.adjh.springboot3security.service.impl;

import com.adjh.springboot3security.mapper.UserMapper;
import com.adjh.springboot3security.model.dto.UserDto;
import com.adjh.springboot3security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * 사용자 서비스의 구현체 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : UserDto
 * @since : 10/1/24
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final SqlSession sqlSession;

    private final String USER_USE_KEY = "fgjhgj1948581sfg";

    public UserServiceImpl(SqlSession ss) {
        this.sqlSession = ss;
    }

    /**
     * 로그인 구현체
     *
     * @param userDto UserDto
     * @return Optional<UserDto>
     */
    public Optional<UserDto> login(UserDto userDto) {
        UserMapper um = sqlSession.getMapper(UserMapper.class);
        return um.login(userDto);
    }

    @Override
    public List<UserDto> selectUserList(UserDto userDto) {
        UserMapper um = sqlSession.getMapper(UserMapper.class);
        System.out.println("USER_USE_KEY: " + USER_USE_KEY);
        return um.selectUserList(userDto);
    }
}
