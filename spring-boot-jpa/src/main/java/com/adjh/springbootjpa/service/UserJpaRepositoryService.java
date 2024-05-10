package com.adjh.springbootjpa.service;

import com.adjh.springbootjpa.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JpaRepository 를 활용한 서비스 인터페이스
 *
 * @author : lee
 * @fileName : UserJpaRepositoryService
 * @since : 2024. 5. 10.
 */
@Service
public interface UserJpaRepositoryService {
    List<UserEntity> userList();                                // 사용자 리스트 조회

    UserEntity saveUser(UserEntity userEntity);                 // 사용자 등록

    UserEntity updateUser(UserEntity userEntity);               // 사용자 수정

    void deleteUserByUserSq(long userSq);                       // 사용자 삭제
}
