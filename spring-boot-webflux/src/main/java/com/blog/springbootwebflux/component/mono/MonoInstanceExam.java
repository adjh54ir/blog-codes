package com.blog.springbootwebflux.component.mono;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Mono 인스턴스를 생성하는 다양한 예시
 *
 * @author : jonghoon
 * @fileName : MonoInstanceExam
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class MonoInstanceExam {
    /**
     * Mono Instance 생성
     */
    public void generateInstance() {
        // 빈 Mono 생성
        Mono<String> emptyMono = Mono.empty();
        emptyMono.subscribe(value -> log.debug("empty 값: {}", value));

        // 값으로 Mono 생성
        Mono<String> mono = Mono.just("Hello");
        mono.subscribe(value -> log.debug("just 값: {}", value));

        // Optional 값으로 Mono 생성
        Optional<String> optionalValue = Optional.of("Optional Value");
        Mono<String> optionalMono = Mono.justOrEmpty(optionalValue);
        optionalMono.subscribe(value -> log.debug("justOrEmpty 값: {}", value));

        // 신호를 방출하지 않는 Mono 생성
        Mono<String> neverMono = Mono.never();
        neverMono.subscribe(value -> log.debug("never 값: {}", value));

        // 에러로 Mono 생성
//        Mono<String> errorMono = Mono.error(new RuntimeException("Error"));
//        errorMono.subscribe(value -> log.debug("error 값: {}", value));

        // defer를 사용한 지연 생성
        Mono<String> deferredMono = Mono.defer(() -> Mono.just("Deferred Value"));
        deferredMono.subscribe(value -> log.debug("defer 값: {}", value));

        // Callable을 이용한 Mono 생성
        Mono<String> callableMono = Mono.fromCallable(() -> {
            // 시간이 걸리는 작업 수행
            Thread.sleep(1000);
            return "Callable 결과";
        });
        callableMono.subscribe(value -> log.debug("fromCallable 값: {}", value));

        // Future를 이용한 Mono 생성
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Future 결과");
        Mono<String> futureMono = Mono.fromFuture(future);
        futureMono.subscribe(value -> log.debug("futureMono 값: {}", value));

        // Runnable을 이용한 Mono 생성
        Mono<Void> runnableMono = Mono.fromRunnable(() -> {
            // 결과값 없이 실행만 하는 작업
            System.out.println("작업 실행");
        });
        runnableMono.subscribe(value -> log.debug("fromRunnable 값: {}", value));
    }

}
