package com.adjh.springboottest.service.impl;

import com.adjh.springboottest.dao.UserDao;
import com.adjh.springboottest.dto.UserDto;
import com.adjh.springboottest.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserServiceImpl
 * @since : 5/31/24
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> selectUserList(UserDto userDto) {
        List<UserDto> userList = userDao.selectUserList(userDto);
        return userList;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto selectUserByUserSq(UserDto userDto) {
        System.out.println("[+] selectUserItemByUserSq 서비스 수행");
        UserDto userItemByUserSq = userDao.selectUserByUserSq(userDto);
        return userItemByUserSq;
    }
}
