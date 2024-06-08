package com.adjh.springboottest.dao.impl;

import com.adjh.springboottest.dao.UserDao;
import com.adjh.springboottest.dto.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserDaoImpl
 * @since : 5/31/24
 */
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
    public UserDto selectUserByUserSq(UserDto userDto) {
        System.out.println("[+] selectUserItemByUserSq DAO 수행 ");
        UserDao ud = sqlSession.getMapper(UserDao.class);
        return ud.selectUserByUserSq(userDto);
    }
}
