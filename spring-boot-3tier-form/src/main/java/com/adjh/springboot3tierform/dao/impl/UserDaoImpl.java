package com.adjh.springboot3tierform.dao.impl;

import com.adjh.springboot3tierform.dao.UserDao;
import com.adjh.springboot3tierform.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserDaoImpl
 * @since : 5/15/24
 */
@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

    private final SqlSession sqlSession;

    public UserDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<UserDto> selectUserList(UserDto userDto) {
        UserDao ud = sqlSession.getMapper(UserDao.class);
        return ud.selectUserList(userDto);
    }

    @Override
    public int insertUser(UserDto userDto) {
        UserDao ud = sqlSession.getMapper(UserDao.class);
        return ud.insertUser(userDto);
    }

    @Override
    public int updateUser(UserDto userDto) {
        UserDao ud = sqlSession.getMapper(UserDao.class);
        return ud.updateUser(userDto);
    }

    @Override
    public int deleteUser(UserDto userDto) {
        UserDao ud = sqlSession.getMapper(UserDao.class);
        return ud.deleteUser(userDto);
    }
}
