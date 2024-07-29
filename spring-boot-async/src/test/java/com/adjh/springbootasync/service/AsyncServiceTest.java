package com.adjh.springbootasync.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AsyncServiceTest {

    @Autowired
    private AsyncService asyncService;

    @Test
    void asyncVoidType() {
        asyncService.asyncVoidType();
    }

    @RepeatedTest(5)
    void asyncFutureType() throws ExecutionException, InterruptedException {
        Future<String> result = asyncService.asyncFutureType();
        System.out.println("result :: " + result.get());
    }

    @Test
    void asyncListenableFuture() {
        asyncService.asyncListenableFuture();
    }

    @Test
    void asyncCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = asyncService.asyncCompletableFuture();
        System.out.println("future :: " + future.get());
    }
}