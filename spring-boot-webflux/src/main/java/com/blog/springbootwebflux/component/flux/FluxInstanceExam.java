package com.blog.springbootwebflux.component.flux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Flux 인스턴스를 생성하는 다양한 예시
 *
 * @author : jonghoon
 * @fileName : FluxInstanceExam
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class FluxInstanceExam {
    /**
     * Flux 인스턴스 생성
     */
    public void generateInstance() {
        // 1. just() - 하나 이상의 항목으로 Flux 생성
        Flux<String> justFlux = Flux.just("A", "B", "C");
        justFlux.subscribe(value -> log.debug("just() 값: {}", value));

        // 2. fromArray() - 배열로부터 Flux 생성
        String[] array = {"A", "B"};
        Flux<String> arrayFlux = Flux.fromArray(array);
        arrayFlux.subscribe(value -> log.debug("fromArray 값: {}", value));

        // 3. fromIterable() - Iterable로부터 Flux 생성
        List<String> list = Arrays.asList("A", "B");
        Flux<String> iterableFlux = Flux.fromIterable(list);
        iterableFlux.subscribe(value -> log.debug("iterableFlux 값: {}", value));

        // 4. range() - 연속된 정수 시퀀스 생성
        Flux<Integer> rangeFlux = Flux.range(1, 5); // 1부터 5까지
        rangeFlux.subscribe(value -> log.debug("rangeFlux 값: {}", value));

        // 5. interval() - 주기적으로 증가하는 Long 값 생성
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
        intervalFlux.take(5).subscribe(value -> log.debug("intervalFlux 값: {}", value));

        // 6. empty() - 비어있는 Flux 생성
        Flux<String> emptyFlux = Flux.empty();
        emptyFlux.subscribe(value -> log.debug("emptyFlux 값: {}", value));

        // 7. error() - 에러를 발생시키는 Flux 생성
//        Flux<String> errorFlux = Flux.error(new RuntimeException("에러 발생"));
//        errorFlux.subscribe(value -> log.debug("errorFlux 값: {}", value));

        // 8. fromStream() - Stream으로부터 Flux 생성
        Stream<String> stream = Stream.of("A", "B");
        Flux<String> streamFlux = Flux.fromStream(stream);
        streamFlux.subscribe(value -> log.debug("streamFlux 값: {}", value));

        // 9. generate() - 프로그래밍 방식으로 Flux 생성
        Flux<String> generateFlux = Flux.generate(sink -> {
            sink.next("데이터");
            sink.complete();
        });
        generateFlux.subscribe(value -> log.debug("generateFlux 값: {}", value));

        // 10. create() - 프로그래밍 방식으로 복잡한 Flux 생성
        Flux<String> createFlux = Flux.create(sink -> {
            sink.next("데이터1");
            sink.next("데이터2");
            sink.complete();
        });
        createFlux.subscribe(value -> log.debug("createFlux 값: {}", value));
    }
}
