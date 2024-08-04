package com.adjh.springbootquerydsl.repository;

import com.adjh.springbootquerydsl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {


}