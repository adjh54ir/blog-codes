package com.adjh.springbootasync.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
class SyncAsyncDiffServiceTest {
    @Autowired
    private SyncAsyncDiffServiceImpl syncAsyncDiffServiceImpl;

    @Test
    void synchronousMethod() {
        // 동기 메서드 호출
        String result = syncAsyncDiffServiceImpl.synchronousMethod();
        System.out.println("Result: " + result);
    }

    @Test
    void asyncMethod() {
        // 비동기 메서드 호출
        CompletableFuture<String> future = syncAsyncDiffServiceImpl.asyncMethod();

        // 다른 작업을 수행
        System.out.println("Doing other tasks...");

        // 비동기 작업의 결과를 기다림
        future.thenAccept(result -> System.out.println("Result: " + result));
    }
}