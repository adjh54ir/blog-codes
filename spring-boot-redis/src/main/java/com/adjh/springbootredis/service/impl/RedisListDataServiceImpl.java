package com.adjh.springbootredis.service.impl;

import com.adjh.springbootredis.config.RedisConfig;
import com.adjh.springbootredis.handler.RedisHandler;
import com.adjh.springbootredis.service.RedisListDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * Redis 리스트 데이터를 처리하는 비즈니스 로직 구현체입니다.
 */
@Service
@RequiredArgsConstructor
public class RedisListDataServiceImpl implements RedisListDataService {

    private final RedisHandler redisHandler;
    private final RedisConfig redisConfig;

    /**
     * Redis 리스트 데이터 값을 등록/수정합니다.
     *
     * @param key   : redis key
     * @param value : redis value
     * @return {int} 성공(1), 실패(0)
     */
    @Override
    public int setListData(String key, Object value) {
        return redisHandler.executeOperation(() -> redisHandler.getListOperations().rightPush(key, value));
    }

    /**
     * Redis 리스트 데이터 값을 등록/수정합니다.(duration 값이 존재하면 메모리 상 유효시간을 지정합니다.)
     *
     * @param key      : redis key
     * @param value    : redis value
     * @param duration : redis 값 메모리 상의 유효시간.
     * @return {int} 성공(1), 실패(0)
     */
    @Override
    public int setListData(String key, Object value, Duration duration) {
        try {
            redisHandler.getListOperations().rightPush(key, value);
            redisConfig.redisTemplate().expire(key, duration);          // key에 대한 duration 통해 데이터 유효시간을 지정합니다.
            return 1;
        } catch (Exception e) {
            System.out.println("Redis 작업 오류 발생 :: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Redis 리스트 데이터 내에 값이 존재하는지 여부를 확인합니다.
     *
     * @param key   : redis key
     * @param value : redis value
     * @return {boolean} 존재(true), 미 존재(false)
     */
    @Override
    public boolean isContainsValue(String key, Object value) {
        List<Object> allItems = redisHandler.getListOperations().range(key, 0, -1);
        return Objects.requireNonNull(allItems).stream()
                .anyMatch(item -> item.equals(value));
    }

    /**
     * Redis 키를 기반으로 리스트 데이터의 값을 조회합니다.
     *
     * @param key : redis key
     * @return {List<Object>}
     */
    @Override
    public List<Object> getListData(String key) {
        return redisHandler.getListOperations().range(key, 0, -1);
    }

    /**
     * Redis 키를 기반으로 리스트 데이터의 값을 삭제합니다.
     *
     * @param key : redis key
     * @return {int} 성공(1), 실패(0)
     */
    @Override
    public int deleteListItem(String key, Object value) {
        return redisHandler.executeOperation(() -> redisHandler.getListOperations().remove(key, 0, value));
    }

    /**
     * Redis 키를 기반으로 리스트 데이터를 삭제합니다.
     *
     * @param key : redis key
     * @return {int} 성공(1), 실패(0)
     */
    @Override
    public int deleteList(String key) {
        return redisHandler.executeOperation(() -> redisConfig.redisTemplate().delete(key));
    }


}
