package com.adjh.springbootasync.service;

import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : AsyncService
 * @since : 7/13/24
 */
@Service
public interface AsyncService {

    void asyncVoidType();                                   // 리턴 값이 존재하지 않는 비동기 서비스

    Future<String> asyncFutureType();                       // 리턴 값이 존재하는 비동기 서비스 : Future

    ListenableFuture<String> asyncListenableFuture();       // 리턴 값이 존재하는 비동기 서비스 : ListenableFuture<String>

    CompletableFuture<String> asyncCompletableFuture();     // 리턴 값이 존재하는 비동기 서비스 : CompletableFuture<String>
}
