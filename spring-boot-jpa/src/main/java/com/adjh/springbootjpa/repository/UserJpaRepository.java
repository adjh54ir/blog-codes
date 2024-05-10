package com.adjh.springbootjpa.repository;

import com.adjh.springbootjpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

}