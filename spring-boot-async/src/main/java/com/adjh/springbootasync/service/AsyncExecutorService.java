package com.adjh.springbootasync.service;

import org.springframework.stereotype.Service;

/**
 * Spring Boot Async Executor 별 사용 예시 : 인터페이스
 */
@Service
public interface AsyncExecutorService {

    void simpleAsyncTaskExecutor();             // SimpleAsyncTaskExecutor 이용한 서비스
}
