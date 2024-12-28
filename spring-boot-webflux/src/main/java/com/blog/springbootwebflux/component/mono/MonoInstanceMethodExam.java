package com.blog.springbootwebflux.component.mono;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Mono Instance 메서드 사용예시
 *
 * @author : jonghoon
 * @fileName : MonoInstanceMethod
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class MonoInstanceMethodExam {

    public void instanceMethod() {
        // 기본적인 Mono 생성
        Mono<String> mono = Mono.just("Hello");

        // 1. map() - 데이터 변환
        Mono<Integer> lengthMono = mono.map(str -> str.length());
        lengthMono.subscribe(value -> log.debug("map 값: {}", value));

        // 2. filter() - 조건에 맞는 데이터 필터링
        Mono<String> filteredMono = mono.filter(str -> str.length() > 3);
        filteredMono.subscribe(value -> log.debug("filter 값: {}", value));

        // 3. defaultIfEmpty() - 비어있는 경우 기본값 제공
        Mono<String> defaultMono = mono
                .filter(str -> str.length() > 10)
                .defaultIfEmpty("기본값");
        defaultMono.subscribe(value -> log.debug("defaultIfEmpty 값: {}", value));

        // 4. timeout() - 시간 제한 설정
        Mono<String> timeoutMono = mono.timeout(Duration.ofSeconds(5));
        timeoutMono.subscribe(value -> log.debug("timeout 값: {}", value));

        // 5. zipWith() - 두 Mono 결합
        Mono<String> mono1 = Mono.just("Hello");
        Mono<String> mono2 = Mono.just("World");
        Mono<String> zippedMono = mono1.zipWith(mono2)
                .map(tuple -> tuple.getT1() + " " + tuple.getT2());
        zippedMono.subscribe(value -> log.debug("zipWith 값: {}", value));


        // 6. flatMap() - 중첩된 Mono를 평탄화
        Mono<String> flatMappedMono = mono.flatMap(str ->
                Mono.just(str + " World")
                        .delayElement(Duration.ofSeconds(1))
        );
        flatMappedMono.subscribe(value -> log.debug("flatMap 값: {}", value));
    }
}
