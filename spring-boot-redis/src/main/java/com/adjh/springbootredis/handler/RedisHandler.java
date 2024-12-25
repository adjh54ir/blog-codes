package com.adjh.springbootredis.handler;

import com.adjh.springbootredis.config.RedisConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisHandler {

    private final RedisConfig redisConfig;

    /**
     * 리스트에 접근하여 다양한 연산을 수행합니다.
     *
     * @return ListOperations<String, Object>
     */
    public ListOperations<String, Object> getListOperations() {
        return redisConfig.redisTemplate().opsForList();
    }

    /**
     * 단일 데이터에 접근하여 다양한 연산을 수행합니다.
     *
     * @return ValueOperations<String, Object>
     */
    public ValueOperations<String, Object> getValueOperations() {
        return redisConfig.redisTemplate().opsForValue();
    }


    /**
     * Redis 작업중 등록, 수정, 삭제에 대해서 처리 및 예외처리를 수행합니다.
     *
     * @param operation
     * @return
     */
    public int executeOperation(Runnable operation) {
        try {
            operation.run();
            return 1;
        } catch (Exception e) {
            System.out.println("Redis 작업 오류 발생 :: " + e.getMessage());
            return 0;
        }
    }
}
