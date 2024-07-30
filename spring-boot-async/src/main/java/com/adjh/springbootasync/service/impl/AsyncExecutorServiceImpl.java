package com.adjh.springbootasync.service.impl;

import com.adjh.springbootasync.service.AsyncExecutorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Spring Boot Async Executor 별 사용 예시 : 구현체
 */
@Service
public class AsyncExecutorServiceImpl implements AsyncExecutorService {

    /**
     * Config 내에서 지정한 simpleAsyncTaskExecutor를 이용한 비동기 통신
     */
    @Async("simpleAsyncTaskExecutor")
    @Override
    public void simpleAsyncTaskExecutor() {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
    }
}
