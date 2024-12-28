package com.blog.springbootwebflux.component.mono;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

/**
 * Mono 타입의 Static Method 사용 예시
 *
 * @author : jonghoon
 * @fileName : MonoStaticMethodExam
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class MonoStaticMethodExam {


    public void staticMethodExam() {

        // 1. delay() - 지정된 시간만큼 실행을 지연
        Mono<Long> delayedMono = Mono.delay(Duration.ofSeconds(2));
        delayedMono.subscribe(value -> log.debug("delay 값: {}", value));


        // 2. firstWithValue() - 여러 Mono 중 첫 번째 값을 선택
        Mono<String> mono1 = Mono.just("First");
        Mono<String> mono2 = Mono.just("Second");
        Mono<String> firstMono = Mono.firstWithValue(Arrays.asList(mono1, mono2));
        firstMono.subscribe(value -> log.debug("firstWithValue 값: {}", value));

        // 3. fromCallable() - Callable을 Mono로 변환
        Mono<String> callableMono = Mono.fromCallable(() -> {
            // 시간이 걸리는 작업 수행
            Thread.sleep(1000);
            return "작업 완료";
        });
        callableMono.subscribe(value -> log.debug("fromCallable 값: {}", value));

        // 4. zip() - 여러 Mono의 결과를 결합
        Mono<String> monoA = Mono.just("Hello");
        Mono<String> monoB = Mono.just("World");
        Mono<String> zipped = Mono.zip(monoA, monoB,
                (a, b) -> a + " " + b);
        zipped.subscribe(value -> log.debug("zip 값: {}", value));

    }
}
