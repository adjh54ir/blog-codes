package com.blog.springbootwebflux.modules;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : InstanceGenerateExam
 * @since : 2024. 12. 10.
 */
public class InstanceGenerateExam {


    /**
     * Mono Instance 생성
     */
    private void monoInstanceGenerate() {
        // 빈 Mono 생성
        Mono<String> emptyMono = Mono.empty();

        // 값으로 Mono 생성
        Mono<String> mono = Mono.just("Hello");

        // 에러로 Mono 생성
        Mono<String> errorMono = Mono.error(new RuntimeException("Error"));

        // defer를 사용한 지연 생성
        Mono<String> deferredMono = Mono.defer(() -> Mono.just("Deferred Value"));

        // Callable을 이용한 Mono 생성
        Mono<String> callableMono = Mono.fromCallable(() -> {
            // 시간이 걸리는 작업 수행
            Thread.sleep(1000);
            return "Callable 결과";
        });

        // Future를 이용한 Mono 생성
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Future 결과");
        Mono<String> futureMono = Mono.fromFuture(future);

        // Runnable을 이용한 Mono 생성
        Mono<Void> runnableMono = Mono.fromRunnable(() -> {
            // 결과값 없이 실행만 하는 작업
            System.out.println("작업 실행");
        });
    }

    /**
     * Flux 인스턴스 생성
     */
    private void fluxInstanceGenerate() {
        // 1. just() - 하나 이상의 항목으로 Flux 생성
        Flux<String> justFlux = Flux.just("A", "B", "C");

        // 2. fromArray() - 배열로부터 Flux 생성
        String[] array = {"A", "B"};
        Flux<String> arrayFlux = Flux.fromArray(array);

        // 3. fromIterable() - Iterable로부터 Flux 생성
        List<String> list = Arrays.asList("A", "B");
        Flux<String> iterableFlux = Flux.fromIterable(list);

        // 4. range() - 연속된 정수 시퀀스 생성
        Flux<Integer> rangeFlux = Flux.range(1, 5); // 1부터 5까지

        // 5. interval() - 주기적으로 증가하는 Long 값 생성
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));

        // 6. empty() - 비어있는 Flux 생성
        Flux<String> emptyFlux = Flux.empty();

        // 7. error() - 에러를 발생시키는 Flux 생성
        Flux<String> errorFlux = Flux.error(new RuntimeException("에러 발생"));

        // 8. fromStream() - Stream으로부터 Flux 생성
        Stream<String> stream = Stream.of("A", "B");
        Flux<String> streamFlux = Flux.fromStream(stream);

        // 9. generate() - 프로그래밍 방식으로 Flux 생성
        Flux<String> generateFlux = Flux.generate(sink -> {
            sink.next("데이터");
            sink.complete();
        });

        // 10. create() - 프로그래밍 방식으로 복잡한 Flux 생성
        Flux<String> createFlux = Flux.create(sink -> {
            sink.next("데이터1");
            sink.next("데이터2");
            sink.complete();
        });
    }
}
