package com.blog.springbootwebflux.component;

import com.blog.springbootwebflux.model.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ErrorHandlerExam
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class ErrorHandlerExam {

    public void errorHandler() {
        // 1. onErrorReturn 예시
        Mono<String> mono1 = Mono.just("data")
                .map(str -> {
                    if (str.isEmpty()) {
                        throw new IllegalArgumentException("Empty string");
                    }
                    return str.toUpperCase();
                })
                .onErrorReturn("기본값");

        mono1.subscribe(value -> log.debug("onErrorReturn 사용예시: {}", value));

        // 2. onErrorReturn with Predicate 예시
        Mono<String> mono2 = Mono.just("")
                .map(str -> {
                    if (str.isEmpty()) {
                        throw new IllegalArgumentException("Empty string");
                    }
                    return str.toUpperCase();
                })
                .onErrorReturn(
                        e -> e instanceof IllegalArgumentException,  // 조건 체크
                        "빈 문자열 에러 발생"  // IllegalArgumentException 발생시에만 이 값 반환
                );
        mono2.subscribe(value -> log.debug("onErrorReturn 사용예시: {}", value));

        // 3. onErrorMap 예시
        Mono<Object> mono3 = Mono.just("data")
                .map(str -> {
                    throw new RuntimeException("원본 에러");
                })
                .onErrorMap(e -> new CustomException("변환된 에러", e))
                .onErrorReturn("CustomException 발생시 기본값"); // 에러 처리 추가
        mono3.subscribe(value -> log.debug("onErrorReturn 사용예시: {}", value));


        // 4. onErrorResume 예시
        Mono<Object> mono4 = Mono.just("data")
                .map(str -> {
                    throw new RuntimeException("에러 발생");
                })
                .onErrorResume(e -> {
                    // 에러 발생시 대체 Publisher 반환
                    return Mono.just("대체 데이터");
                });
        mono4.subscribe(value -> log.debug("onErrorResume 사용예시: {}", value));

        // 5. doOnError 예시
        Mono<Object> mono5 = Mono.just("data")
                .map(str -> {
                    throw new RuntimeException("에러 발생");
                })
                .doOnError(e -> {
                    System.err.println("에러 발생: " + e.getMessage());
                    // 로깅이나 기타 부가 작업 수행
                })
                .onErrorReturn("기본값");

        mono5.subscribe(value -> log.debug("doOnError 사용예시: {}", value));
    }

    /**
     * retry(), timeout()을 통해 고급 에러 처리 방법
     */
    public void advancedErrorHandler() {

        // retry() 사용예시
        Flux<Integer> fluxRetry = Flux.just(1, 2, 3)
                .map(i -> {
                    if (i == 2) throw new RuntimeException("Error");
                    return i;
                })
                .retry(3)  // 최대 3번 재시도
                .onErrorReturn(
                        e -> e instanceof RuntimeException,  // 조건 체크
                        0
                );
        fluxRetry.subscribe(value -> log.debug("처리 결과 :: {}", value));


        // timeout() 사용예시
        Mono<String> monoStr = Mono.just("data")
                .delayElement(Duration.ofSeconds(2))
                .timeout(Duration.ofSeconds(1))
                .onErrorReturn("Timeout occurred");
        monoStr.subscribe(value -> log.debug("처리 결과 :: {}", value));
    }

}
