package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserServiceTest
 * @since : 2024. 12. 20.
 */
@SpringBootTest
@AutoConfigureWebTestClient
public class UserServiceTest {

    @Test
    public void testParallelProcessing() {
        WebClient client = WebClient.create("http://localhost:8080");

        // 10명의 서로 다른 사용자 데이터 생성
        List<UserEntity> users = IntStream.range(0, 10)
                .mapToObj(i -> UserEntity.builder()
                        .userId("testIdd" + i)
                        .userEmail("adjh54@naver.com")
                        .build())
                .collect(Collectors.toList());

        // 각 사용자에 대해 테스트 실행
        StepVerifier.create(
                        Flux.fromIterable(users)
                                .flatMap(user ->
                                        client.get()
                                                .uri("/api/v1/user/user/{userId}", user.getUserId())
                                                .accept(MediaType.APPLICATION_JSON)
                                                .exchange()
                                                .thenReturn(Integer.class)
                                                .elapsed()
                                )
                )
                .expectNextCount(10)
                .verifyComplete();
    }


    @Test
    void testMultipleUserRegistrations() {
        WebClient client = WebClient.create("http://localhost:8080");

        // 10명의 서로 다른 사용자 데이터 생성
        List<UserEntity> users = IntStream.range(0, 10)
                .mapToObj(i -> UserEntity.builder()
                        .userId("testIdd" + i)
                        .userEmail("adjh54@naver.com")
                        .build())
                .collect(Collectors.toList());

        // 각 사용자에 대해 테스트 실행
        StepVerifier.create(
                        Flux.fromIterable(users)
                                .flatMap(user ->
                                        client.post()
                                                .uri("/api/v1/user/users")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(user)
                                                .exchange()
                                                .thenReturn(Integer.class)
                                                .elapsed()
                                )
                )
                .expectNextCount(10)
                .verifyComplete();
    }
}
