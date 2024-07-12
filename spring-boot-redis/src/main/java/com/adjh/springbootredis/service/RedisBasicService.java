package com.adjh.springbootredis.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
/**
 * 기본적인 Redis 통신 서비스 인터페이스
 *
 * @author : lee
 * @fileName : RedisBasicService
 * @since : 24. 7. 2.
 */
@Service
public interface RedisBasicService {

    int setValues(String key, String value);                       // 값 등록 / 수정

    int setValues(String key, String value, Duration duration);    // 값 등록 / 수정

    String getValue(String key);                                    // 값 조회

    int deleteValue(String key);                                   // 값 삭제
}
