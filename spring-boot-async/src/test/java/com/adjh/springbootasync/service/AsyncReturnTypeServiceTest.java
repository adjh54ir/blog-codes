package com.adjh.springbootasync.service;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
class AsyncReturnTypeServiceTest {

    @Autowired
    private AsyncReturnTypeService asyncReturnTypeService;


    @Test
    void multiThread() {
        asyncReturnTypeService.multiThread();
    }


    @Test
    void asyncVoidType() {
        asyncReturnTypeService.asyncVoidType();
    }

    @RepeatedTest(5)
    void asyncFutureType() throws ExecutionException, InterruptedException {
        Future<String> result = asyncReturnTypeService.asyncFutureType();
        System.out.println("result :: " + result.get());
    }


    @Test
    void asyncListenableFuture() {
        asyncReturnTypeService.asyncListenableFuture();
    }

    @Test
    void asyncCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = asyncReturnTypeService.asyncCompletableFuture();
        System.out.println("future :: " + completableFuture.get());
    }

    @Test
    void asyncCompletableFuture2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = asyncReturnTypeService.asyncCompletableFuture2();
        System.out.println("future :: " + completableFuture.get());
    }


}