package com.adjh.springboot3tierform.service.impl;

import com.adjh.springboot3tierform.dao.UserDao;
import com.adjh.springboot3tierform.model.dto.UserDto;
import com.adjh.springboot3tierform.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserServiceImpl
 * @since : 5/15/24
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 사용자 리스트 조회
     *
     * @param userDto
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> selectUserList(UserDto userDto) {
        List<UserDto> userList = userDao.selectUserList(userDto);
        return userList;
    }

    /**
     * 사용자 등록
     *
     * @param userDto
     * @return
     */
    @Override
    @Transactional
    public int insertUser(UserDto userDto) {
        int result = userDao.insertUser(userDto);
        return result;
    }

    /**
     * 사용자 정보 수정
     *
     * @param userDto
     * @return
     */
    @Override
    @Transactional
    public int updateUser(UserDto userDto) {
        int result = userDao.updateUser(userDto);
        return result;
    }

    /**
     * 사용자 삭제
     *
     * @param userDto
     * @return
     */
    @Override
    @Transactional
    public int deleteUser(UserDto userDto) {
        int result = userDao.deleteUser(userDto);
        return result;
    }
}
