package com.adjh.springboot3security.service.impl;

import com.adjh.springboot3security.service.TokenBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Redis 내에 Token BlackList를 관리하는 서비스 구현체입니다.
 *
 * @author : jonghoon
 * @fileName : RedisTokenService
 * @since : 11/2/24
 */
@Service
@RequiredArgsConstructor
public class TokenBlackListServiceImpl implements TokenBlackListService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final String REDIS_BLACK_LIST_KEY = "tokenBlackList";

    /**
     * BlackList 내에 토큰을 추가합니다.
     *
     * @param value
     */
    @Override
    public void addTokenToList(String value) {
        redisTemplate.opsForList().rightPush(REDIS_BLACK_LIST_KEY, value);
    }

    /**
     * BlackList 내에 토큰이 존재하는지 여부를 확인합니다.
     *
     * @param value
     * @return
     */
    @Override
    public boolean isContainToken(String value) {
        List<Object> allItems = redisTemplate.opsForList().range(REDIS_BLACK_LIST_KEY, 0, -1);
        return allItems.stream()
                .anyMatch(item -> item.equals(value));
    }


    /**
     * BlackList 항목을 모두 조회합니다.
     *
     * @return
     */
    public List<Object> getTokenBlackList() {
        return redisTemplate.opsForList().range(REDIS_BLACK_LIST_KEY, 0, -1);
    }


    /**
     * BlackList 내에서 항목을 제거합니다.
     *
     * @param value
     */
    @Override
    public void removeToken(String value) {
        redisTemplate.opsForList().remove(REDIS_BLACK_LIST_KEY, 0, value);
    }
}
