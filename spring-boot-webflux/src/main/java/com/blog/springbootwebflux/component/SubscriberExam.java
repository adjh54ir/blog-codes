package com.blog.springbootwebflux.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : SubscriberExam
 * @since : 24. 12. 28.
 */
@Slf4j
@Component
public class SubscriberExam {

    public void subsciberExam() {

        // Publisher Flux 기반 데이터 생성
        Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5);

        // Subscriber 데이터 구독
        numbers.subscribe(
                // onNext - 각 데이터 처리
                data -> log.debug("Received: " + data),

                // onError - 에러 처리
                error -> log.debug("Error occurred: " + error),

                // onComplete - 완료 처리
                () -> log.info("Completed!"),

                // onSubscribe - 구독 시작 시 처리
                subscription -> {
                    log.debug("Subscribed!");
                    subscription.request(Long.MAX_VALUE);
                }
        );
    }
}
