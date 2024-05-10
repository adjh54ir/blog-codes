package com.adjh.springbootjpa.service.impl;

import com.adjh.springbootjpa.entity.UserEntity;
import com.adjh.springbootjpa.repository.UserJpaRepository;
import com.adjh.springbootjpa.service.UserJpaRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * JpaRepository를 이용한 Service 구현체
 *
 * @author : lee
 * @fileName : UserJpaRepositoryServiceImpl
 * @since : 2024. 5. 10.
 */
@Service
public class UserJpaRepositoryServiceImpl implements UserJpaRepositoryService {
    private final UserJpaRepository userJpaRepository;

    public UserJpaRepositoryServiceImpl(UserJpaRepository userJpaRepository) {
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
        UserEntity user = userJpaRepository.findById(userEntity.getUserSq()).get();

        UserEntity resultEntity = new UserEntity();

        if (Objects.nonNull(user.getUserNm()) && !"".equalsIgnoreCase(user.getUserNm())) {
            resultEntity = userEntity.toBuilder().userNm(user.getUserNm()).build();
        }

        if (Objects.nonNull(user.getUserId()) && !"".equalsIgnoreCase(user.getUserId())) {
            resultEntity = userEntity.toBuilder().userId(user.getUserId()).build();
        }

        return userJpaRepository.save(resultEntity);
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
