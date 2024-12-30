package com.blog.springbootwebflux.component.flux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Flux Instance 메서드 사용예시
 *
 * @author : jonghoon
 * @fileName : FluxInstanceMethodExam
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class FluxInstanceMethodExam {

    public void instanceMethod() {
        Flux<Integer> numbers = Flux.just(1, 2, 2, 3, 4, 5, 5);

        numbers
                // map(): 각 요소를 2배로 변환
                .map(n -> n * 2)

                // filter(): 짝수만 필터링
                .filter(n -> n % 2 == 0)

                // distinct(): 중복 제거
                .distinct()

                // take(): 처음 3개 요소만 선택
                .take(3)

                // flatMap(): 각 숫자를 문자열로 변환하고 분리
                .flatMap(n -> Flux.fromArray(String.valueOf(n).split("")))

                // collectList(): List로 변환
                .collectList()

                // subscribe(): 결과 구독 및 처리
                .subscribe(
                        result -> System.out.println("결과: " + result),
                        error -> System.out.println("에러 발생: " + error),
                        () -> System.out.println("처리 완료")
                );

        numbers.subscribe(value -> log.debug("다양한 처리 결과값: {}", value));

        // 여러 Flux 결합 예시
        Flux<String> flux1 = Flux.just("A", "B");
        Flux<String> flux2 = Flux.just("C", "D");

        // merge(): 여러 Flux를 하나로 병합
        Flux<String> fluxMerge = Flux.merge(flux1, flux2);
        fluxMerge.subscribe(value -> log.debug("merge 값: {}", value));

        // zip(): 여러 Flux 요소들을 조합
        Flux<String> fluxZip = Flux.zip(flux1, flux2, (f1, f2) -> f1 + f2);
        fluxZip.subscribe(value -> log.debug("zip 값: {}", value));
    }
}
