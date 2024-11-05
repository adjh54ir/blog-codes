package com.adjh.springbootredis.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * Redis 리스트 데이터를 처리하는 비즈니스 로직 인터페이스입니다.
 */
@Service
public interface RedisListDataService {

    public int setListData(String key, Object value);                       // Redis 리스트 데이터 값을 등록/수정합니다.

    public int setListData(String key, Object value, Duration duration);    // Redis 리스트 데이터 값을 등록/수정합니다.(duration 값이 존재하면 메모리 상 유효시간을 지정합니다.)

    public boolean isContainsValue(String key, Object value);               // Redis 리스트 데이터 내에 값이 존재하는지 여부를 확인합니다.

    public List<Object> getListData(String key);                            // Redis 키를 기반으로 리스트 데이터의 값을 조회합니다.

    public int deleteListItem(String key, Object value);                    // Redis 키를 기반으로 리스트 내의 특정 데이터를 삭제합니다.

    public int deleteList(String key);                                      // Redis 키를 기반으로 리스트 데이터를 삭제합니다.

}
