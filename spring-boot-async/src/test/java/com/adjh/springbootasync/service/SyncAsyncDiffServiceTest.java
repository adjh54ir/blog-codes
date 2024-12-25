package com.adjh.springbootasync.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SyncAsyncDiffServiceTest {

    @Autowired
    private SyncAsyncDiffService syncAsyncDiffService;

    /**
     * 동기 호출 수행 테스트
     */
    @Test
    void synchronousMethod() {
        // 동기 메서드 호출
        String result = syncAsyncDiffService.synchronousMethod();
        System.out.println("Result: " + result);
    }

    /**
     * 비동기 호출 수행 테스트
     */
    @Test
    void asyncMethod() {

        // 비동기 메서드 호출
        CompletableFuture<String> future = syncAsyncDiffService.asyncMethod();

        // 다른 작업을 수행
        System.out.println("Doing other tasks...");

        try {
            // 비동기 작업의 결과를 기다림
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}