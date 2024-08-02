package com.adjh.springbootasync.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 동기처리방식과 비동기 처리방식을 비교하는 서비스
 */
@Service
public interface SyncAsyncDiffService {

    String synchronousMethod();           // 동기 처리 방식

    CompletableFuture<String> asyncMethod();                 // 비동기 처리 방식

}
