package com.adjh.springbootasync.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : SchedulerConfig
 * @since : 8/3/24
 */

@Configuration
//@EnableScheduling      // 스케줄링 테스트를 위해서 해당 주석을 풀어줍니다.
public class SchedulerConfig {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Async("scheduledThreadPoolExecutor")
    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTask() {
        System.out.println("Thread Name :: " + Thread.currentThread().getName());

        System.out.println("5초마다 비동기로 실행이 됩니다 - " + LocalDateTime.now().format(formatter));
    }

    @Async("scheduledThreadPoolExecutor")
    @Scheduled(fixedDelay = 3000)
    public void scheduleFixedDelayTask() {
        System.out.println("Thread Name :: " + Thread.currentThread().getName());
        System.out.println("이전 작업이 완료된 후 3초 뒤에 실행됩니다. - " + LocalDateTime.now().format(formatter));
    }

    @Async("scheduledThreadPoolExecutor")
    @Scheduled(initialDelay = 1000, fixedRate = 2000)
    public void scheduleTaskWithInitialDelay() {
        System.out.println("Thread Name :: " + Thread.currentThread().getName());
        System.out.println("1초 지연 후 시작되고, 이후 2초마다 실행됩니다. - " + LocalDateTime.now().format(formatter));
    }
}
