package com.adjh.springbootasync.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Async Executor 테스트 환경
 */
@SpringBootTest
class AsyncExecutorServiceTest {

    @Autowired
    private AsyncExecutorService asyncExecutorService;


    @Test
    void simpleAsyncTaskExecutor() {
        asyncExecutorService.simpleAsyncTaskExecutor();
    }
}