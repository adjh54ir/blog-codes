package com.adjh.springbootquerydsl.service;

import com.adjh.springbootquerydsl.entity.UserEntity;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void saveUser() {
        UserEntity userEntity = UserEntity.builder()
                .userId("adjh54")
                .userNm("lee")
                .userSt("A")
                .userPw("1234")
                .userEmail("adjh54@naver.com")
                .build();
        UserEntity result = userService.saveUser(userEntity);
        System.out.println("result :: " + result);
    }

    @Test
    void selectUserList() {
        UserEntity userEntity = new UserEntity();
        List<UserEntity> selectUserList = userService.selectUserList(userEntity);
        System.out.println("userList :: " + selectUserList);
    }

    @Test
    void userList() {
    }

    @Test
    void testSaveUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserByUserSq() {
    }
}