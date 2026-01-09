package com.adjh.springboot3vault.service.impl;

import com.adjh.springboot3vault.dto.UserDto;
import com.adjh.springboot3vault.mapper.UserMapper;
import com.adjh.springboot3vault.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserService 구현체
 * <p>
 * - 비즈니스 로직 계층
 * - Mapper 호출 및 Optional 반환
 *
 * @author leejonghoon
 * @since 2026.01.08
 */
@Service
public class UserServiceImpl implements UserService {

    private final SqlSession sqlSession;

    public UserServiceImpl(SqlSession ss) {
        this.sqlSession = ss;
    }


    @Override
    public UserDto findByUserSq(Long userSq) {
        UserMapper um = sqlSession.getMapper(UserMapper.class);
        return um.findByUserSq(userSq);
    }

    @Override
    public UserDto findByUserId(String userId) {
        UserMapper um = sqlSession.getMapper(UserMapper.class);
        return um.findByUserId(userId);
    }

    @Override
    public List<UserDto> selectUser() {
        UserMapper um = sqlSession.getMapper(UserMapper.class);
        return um.selectUser();
    }
}
