package com.spring.security.service.impl;

import com.spring.security.mapper.UserMapper;
import com.spring.security.model.dto.UserDto;
import com.spring.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final SqlSession sqlSession;

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
        return um.selectUserList(userDto);
    }
}
