package com.adjh.springbootasync.service.impl;

import com.adjh.springbootasync.service.AsyncExecutorService;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Spring Boot Async Executor 별 사용 예시 : 구현체
 */
@Service
public class AsyncExecutorServiceImpl implements AsyncExecutorService {

    /**
     * Config 내에서 지정한 simpleAsyncTaskExecutor를 이용한 비동기 통신
     */
    @Async("simpleAsyncTaskExecutor")
    @Override
    public void simpleAsyncTaskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                System.out.println("Hello from thread: " + Thread.currentThread().getName());
            });
        }
    }

    /**
     * Config 내에서 지정한 threadPoolTaskExecutor를 이용한 비동기 통신
     *
     * @param index
     */
    @Async("threadPoolTaskExecutor")
    @Override
    public void threadPoolTaskExecutor(int index) {
        System.out.println("Executing task " + index + " - " + Thread.currentThread().getName());

        try {
            Thread.sleep(2000); // 2초 동안 작업을 시뮬레이션
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
