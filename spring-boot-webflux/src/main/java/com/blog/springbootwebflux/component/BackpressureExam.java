package com.blog.springbootwebflux.component;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * 백프레셔를 관리하는 메서드를 사용한 예시
 *
 * @author : jonghoon
 * @fileName : BackpressureExam
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class BackpressureExam {

    public void backpressureMethod() {

        // 백프레셔 제어 함수 예시 코드

        // 1. request(n) 예시 - 10개의 데이터만 요청
        Flux.range(1, 100)
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(10);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        log.debug("Received: " + value);
                    }
                });

        // 2. onBackpressureBuffer() 예시 - 최대 5개까지 버퍼링
        Flux.interval(Duration.ofMillis(1))
                .onBackpressureBuffer(5)
                .subscribe(value -> {
                    log.debug("Buffered value: " + value);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        // 3. onBackpressureDrop() 예시 - 처리할 수 없는 데이터는 드랍
        Flux.interval(Duration.ofMillis(1))
                .onBackpressureDrop(dropped ->
                        log.debug("Dropped value: " + dropped))
                .subscribe(value -> {
                    log.debug("Processing: " + value);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        // 4. onBackpressureLatest() 예시 - 최신 데이터만 유지
        Flux.interval(Duration.ofMillis(1))
                .onBackpressureLatest()
                .subscribe(value -> {
                    log.debug("Latest value: " + value);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        // 5. limitRate(n) 예시 - 한 번에 5개씩만 처리
        Flux.range(1, 20)
                .limitRate(5)
                .subscribe(value ->
                        log.debug("Limited rate value: " + value));

        // 6. sample() 예시 - 1초 간격으로 샘플링
        Flux.interval(Duration.ofMillis(100))
                .sample(Duration.ofSeconds(1))
                .subscribe(value ->
                        log.debug("Sampled value: " + value));

        // 7. sample() 예시 - 2초 동안 첫 번째 데이터만 처리
        Flux.interval(Duration.ofMillis(500))
                .sample(Duration.ofSeconds(2))  // throttleFirst와 비슷한 효과를 내기 위해 sample 사용
                .subscribe(value ->
                        System.out.println("First value in 2 seconds: " + value));

        // 8. sample() 예시 - 2초 동안 마지막 데이터만 처리
        Flux.interval(Duration.ofMillis(500))
                .sample(Duration.ofSeconds(2))  // throttleLast와 동일한 효과
                .subscribe(value ->
                        System.out.println("Last value in 2 seconds: " + value));
    }
}
