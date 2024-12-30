package com.blog.springbootwebflux.component.flux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Flux 타입의 Static Method 사용 예시
 *
 * @author : jonghoon
 * @fileName : FluxStaticMethodExam
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class FluxStaticMethodExam {

    public void staticMethod() {
        // concat() 예시 - 순차적 연결
        Flux<String> flux1 = Flux.just("A", "B");
        Flux<String> flux2 = Flux.just("C", "D");


        Flux<String> concatenated = Flux.concat(flux1, flux2);  // 결과: A, B, C, D
        concatenated.subscribe(value -> log.debug("concat 값: {}", value));

        // merge() 예시 - 병렬 병합
        Flux<String> merged = Flux.merge(flux1, flux2);  // 순서가 보장되지 않음
        merged.subscribe(value -> log.debug("merge 값: {}", value));

        // zip() 예시 - 요소 조합
        Flux<Integer> numbers = Flux.just(1, 2, 3);
        Flux<String> letters = Flux.just("A", "B", "C");
        Flux<String> zipped = Flux.zip(numbers, letters,
                (n, l) -> n + l);  // 결과: "1A", "2B", "3C"

        zipped.subscribe(value -> log.debug("zip 값: {}", value));
    }
}
