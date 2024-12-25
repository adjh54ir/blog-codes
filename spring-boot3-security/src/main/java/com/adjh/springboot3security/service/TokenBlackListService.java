package com.adjh.springboot3security.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Redis 내에 Token BlackList를 관리하는 서비스입니다.
 *
 * @author : jonghoon
 * @fileName : RedisTokenService
 * @since : 11/2/24
 */
@Service
public interface TokenBlackListService {

    void addTokenToList(String value);              // Redis key-value 형태로 리스트 추가

    boolean isContainToken(String value);           // Redis key 기반으로 리스트 조회

    List<Object> getTokenBlackList();               // Redis Key 기반으로 BlackList를 조회합니다.

    void removeToken(String value);                 // Redis Key 기반으로 리스트 내 요소 제거
}
