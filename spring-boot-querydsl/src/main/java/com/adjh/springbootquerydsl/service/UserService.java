package com.adjh.springbootquerydsl.service;

import com.adjh.springbootquerydsl.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 사용자 서비스 인터페이스입니다.
 *
 * @author : jonghoon
 * @fileName : UserService
 * @since : 2/8/24
 */
@Service
public interface UserService {

    List<UserEntity> userList();                                // 사용자 리스트 조회

    UserEntity saveUser(UserEntity userEntity);                 // 사용자 등록

    UserEntity updateUser(UserEntity userEntity);               // 사용자 수정

    List<UserEntity> selectUserList(UserEntity userEntity);           // 사용자 조회

    void deleteUserByUserSq(long userSq);                       // 사용자 삭제

}
