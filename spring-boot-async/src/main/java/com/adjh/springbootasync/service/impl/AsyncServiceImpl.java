package com.adjh.springbootasync.service.impl;

import com.adjh.springbootasync.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.*;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : AsyncServiceImpl
 * @since : 7/13/24
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    /**
     * [Async] 반환 유형이 존재하지 않는 경우 : void
     */
    @Async
    @Override
    public void asyncVoidType() {
        System.out.println("Execute method asynchronously. :: " + Thread.currentThread().getName());
    }

    /**
     * [Async] 반환 유형이 존재하는 경우 : Future
     *
     * @return
     */
    @Async
    @Override
    public Future<String> asyncFutureType() {
        // 1. 현재 스레드의 이름을 출력합니다.
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            // 2. 스레드를 5초간 일시 중지 시킵니다.
            Thread.sleep(5000);

            // 3. 비동기 작업의 결과를 반환합니다.
            return new AsyncResult<String>("hello world !!!!");
        } catch (InterruptedException e) {
            //
        }
        return null;
    }

    /**
     * [Async] 반환 유형이 존재하는 경우 : ListenableFuture
     *
     * @return ListenableFuture<String>
     */
    @Async
    @Override
    public ListenableFuture<String> asyncListenableFuture() {
        // 1. 현재 스레드의 이름을 출력합니다.
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());

        try {
            // 2. 스레드를 5초간 일시 중지 시킵니다.
            Thread.sleep(5000);

            // 3. 비동기 작업의 결과를 반환합니다.
            ListenableFuture<String> future = new AsyncResult<>("hello world !!!!");

            // 4. 콜백을 등록합니다.
            future.addCallback(new ListenableFutureCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    // 비동기 작업이 성공적으로 완료되었을 때 실행되는 콜백
                    System.out.println("Success with result: " + result);
                }

                @Override
                public void onFailure(Throwable t) {
                    // 비동기 작업이 실패했을 때 실행되는 콜백
                    System.out.println("Failure: " + t.getMessage());
                }
            });
            return future;
        } catch (InterruptedException e) {
            System.out.println("error :: " + e.getMessage());
        }

        return null;
    }

    /**
     * [Async] 반환 유형이 존재하는 경우 : CompletableFuture
     *
     * @return CompletableFuture<String>
     */
    @Async
    @Override
    public CompletableFuture<String> asyncCompletableFuture() {
        // 1. 현재 스레드의 이름을 출력합니다.
        System.out.println("Execute method asynchronously " + Thread.currentThread().getName());


        // 2. 비동기 작업을 수행할 CompletableFuture 객체를 생성합니다.
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 3.  스레드를 5초간 일시 중지 시킵니다.
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Error: " + e.getMessage();
            }
            // 4. 비동기 결과가 성공하였을때 반환되는 값입니다.
            return "hello world !!!!";
        });
    }
}
