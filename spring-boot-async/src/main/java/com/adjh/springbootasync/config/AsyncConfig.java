package com.adjh.springbootasync.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : AsyncConfig
 * @since : 24. 7. 17.
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     * Executor를 SimpleAsyncTaskExecutor로 지정합니다.
     *
     * @return Executor
     */
    @Bean(name = "simpleAsyncTaskExecutor")
    public Executor simpleAsyncTaskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
