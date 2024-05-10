package com.adjh.springbootjpa.service.impl;

import com.adjh.springbootjpa.entity.UserEntity;
import com.adjh.springbootjpa.repository.UserJpaRepository;
import com.adjh.springbootjpa.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 사용자 인터페이스의 구현체입니다.
 *
 * @author : jonghoon
 * @fileName : UserServiceImpl
 * @since : 2/8/24
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * 사용자 리스트 조회
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> userList() {
        return (List<UserEntity>) userJpaRepository.findAll();
    }

    /**
     * 사용자 등록
     *
     * @param userEntity
     * @return
     */
    @Override
    @Transactional
    public UserEntity saveUser(UserEntity userEntity) {
        return userJpaRepository.save(userEntity);
    }

    /**
     * 사용자 수정
     *
     * @param userEntity
     * @return
     */
    @Override
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        return null;
    }

    /**
     * 사용자 삭제
     *
     * @param userSq
     */
    @Override
    @Transactional
    public void deleteUserByUserSq(long userSq) {
        userJpaRepository.deleteById(userSq);
    }

}
