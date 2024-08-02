package com.adjh.springbootasync.service.impl;

import com.adjh.springbootasync.service.SyncAsyncDiffService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 동기, 비동기 차이 비교 서비스
 */
@Service
public class SyncAsyncDiffServiceImpl implements SyncAsyncDiffService {
    @Override
    public String synchronousMethod() {
        // 1. 현재 스레드의 이름을 출력합니다.
        System.out.println("Execute method synchronously " + Thread.currentThread().getName());

        try {
            // 2. 스레드를 5초간 일시 중지 시킵니다.
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error: " + e.getMessage();
        }

        // 3. 작업이 완료된 후 결과를 반환합니다.
        return "hello world !!!!";
    }

    @Override
    @Async
    public CompletableFuture<String> asyncMethod() {
        // 1. 현재 스레드의 이름을 출력합니다.
        System.out.println("Execute method asynchronously " + Thread.currentThread().getName());

        return CompletableFuture.supplyAsync(() -> {
            try {
                // 2. 스레드를 5초간 일시 중지 시킵니다.
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Error: " + e.getMessage();
            }

            // 3. 비동기 결과를 반환합니다.
            return "hello world !!!!";
        });
    }
}
