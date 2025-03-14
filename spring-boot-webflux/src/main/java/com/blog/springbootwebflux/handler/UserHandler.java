package com.blog.springbootwebflux.handler;

import com.blog.springbootwebflux.component.BackpressureExam;
import com.blog.springbootwebflux.component.ErrorHandlerExam;
import com.blog.springbootwebflux.component.SubscriberExam;
import com.blog.springbootwebflux.component.flux.FluxInstanceExam;
import com.blog.springbootwebflux.component.flux.FluxInstanceMethodExam;
import com.blog.springbootwebflux.component.flux.FluxStaticMethodExam;
import com.blog.springbootwebflux.component.mono.MonoInstanceExam;
import com.blog.springbootwebflux.component.mono.MonoInstanceMethodExam;
import com.blog.springbootwebflux.component.mono.MonoStaticMethodExam;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * 사용자 Handler Function
 *
 * @author : leejonghoon
 * @fileName : UserHandler
 * @since : 2024. 12. 4.
 */
@Slf4j
@Component
public class UserHandler {
    private final UserService userService;
    private final TransactionalOperator transactionalOperator;
    private final MonoInstanceExam monoInstanceExam;
    private final MonoStaticMethodExam monoStaticMethodExam;
    private final MonoInstanceMethodExam monoInstanceMethodExam;
    private final FluxInstanceExam fluxInstanceExam;
    private final FluxInstanceMethodExam fluxInstanceMethodExam;
    private final FluxStaticMethodExam fluxStaticMethodExam;
    private final SubscriberExam subscriberExam;
    private final ErrorHandlerExam errorHandlerExam;
    private final BackpressureExam backpressureExam;


    public UserHandler(UserService userService, TransactionalOperator transactionalOperator, MonoInstanceExam monoInstanceExam, MonoStaticMethodExam monoStaticMethodExam, MonoInstanceMethodExam monoInstanceMethodExam, FluxInstanceExam fluxInstanceExam, FluxInstanceMethodExam fluxInstanceMethodExam, FluxStaticMethodExam fluxStaticMethodExam, SubscriberExam subscriberExam, ErrorHandlerExam errorHandlerExam, BackpressureExam backpressureExam) {
        this.userService = userService;
        this.transactionalOperator = transactionalOperator;
        this.monoInstanceExam = monoInstanceExam;
        this.monoStaticMethodExam = monoStaticMethodExam;
        this.monoInstanceMethodExam = monoInstanceMethodExam;
        this.fluxInstanceExam = fluxInstanceExam;
        this.fluxInstanceMethodExam = fluxInstanceMethodExam;
        this.fluxStaticMethodExam = fluxStaticMethodExam;
        this.subscriberExam = subscriberExam;
        this.errorHandlerExam = errorHandlerExam;
        this.backpressureExam = backpressureExam;
    }


    // 사용자 조회 핸들러

    /**
     * Handler 처리 과정 예시
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        // 1. 요청 처리 - ServerRequest를 통해 경로 변수 추출
        String userId = request.pathVariable("userId");

        // 2. 비즈니스 로직 연계 - Service 계층 호출
        return userService.findById(userId)
                // 3. 응답 생성 - 성공 시 200 OK와 함께 사용자 정보 반환
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user))
                // 4. 에러 처리 - 사용자가 없을 경우 404 Not Found 반환
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 트랜잭션을 관리합니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> executeInTransaction(ServerRequest request) {
        // 1. 요청 처리 - ServerRequest를 통해 경로 변수 추출
        String userId = request.pathVariable("id");

        // 2. 비즈니스 로직 연계 - Service 계층 호출
        return userService.findUserByUserId(userId)
                // 3. 응답 생성 - 성공 시 200 OK와 함께 사용자 정보 반환
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user))
                .as(transactionalOperator::transactional)
                .doOnError(e -> log.error("Transaction failed", e))
                .onErrorResume(e -> Mono.error(new RuntimeException("Transaction failed", e)));
    }


    /**
     * 사용자를 등록하고 성공 메일을 보냅니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> registerUser(ServerRequest request) {
        return request.bodyToMono(UserEntity.class)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "요청 본문이 비어있습니다")))
                .flatMap(userService::userRegister)
                .flatMap(result -> ServerResponse.ok().bodyValue(result));
    }

    /**
     * 사용자 아이디를 기반으로 단건 사용자를 조회합니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findTbUserByUserId(ServerRequest request) {
        String userId = request.pathVariable("userId");
        return userService.findUserByUserId(userId)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user))
                .doOnNext(response -> log.info("Found user for userId: {}", userId))
                .doOnError(error -> log.error("Error finding user: {}", error.getMessage()))
                .switchIfEmpty(Mono.defer(() -> {
                    log.info("No user found with ID: {}", userId);
                    return ServerResponse.notFound().build();
                }));
    }

    /**
     * 사용자 이름을 기반으로 단건 사용자를 조회합니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findTbUserByUserNm(ServerRequest request) {
        return request.queryParam("userNm")
                .map(userNm -> userService.findTbUserByUserNm(userNm)
                        .collectList()
                        .flatMap(users -> {
                            if (users.isEmpty()) {
                                return ServerResponse.notFound().build();
                            }
                            return ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(users);
                        })
                        .doOnNext(response -> log.info("Found users for userNm: {}", userNm))
                        .doOnError(error -> log.error("Error finding users: {}", error.getMessage())))
                .orElse(ServerResponse.badRequest()
                        .bodyValue("userNm parameter is required"));
    }
}
