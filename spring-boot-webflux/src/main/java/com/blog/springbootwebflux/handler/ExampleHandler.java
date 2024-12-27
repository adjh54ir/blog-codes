package com.blog.springbootwebflux.handler;

import com.blog.springbootwebflux.model.dto.UserDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : ExampleHandler
 * @since : 2024. 12. 27.
 */
@Component
public class ExampleHandler {

    private final TransactionalOperator transactionalOperator;

    public ExampleHandler(TransactionalOperator transactionalOperator) {
        this.transactionalOperator = transactionalOperator;
    }


    public Mono<ServerResponse> handleGetRequest(ServerRequest request) {
        // 1. 경로 변수 추출
        String id = request.pathVariable("id");

        // 2. 쿼리 파라미터 추출
        String name = request.queryParam("name").orElse("default");

        // 3. 헤더 값 추출
        String authHeader = request.headers().firstHeader("Authorization");

        // 4. 요청 본문 추출 (JSON을 User 객체로 변환)
        Mono<UserDto> userMono = request.bodyToMono(UserDto.class);

        // 5. 쿠키 추출
        String sessionId = request.cookies().getFirst("sessionId")
                .getValue();

        // 6. 요청 메소드 확인
        String method = request.methodName();

        // 7. 요청 URI 확인
        URI uri = request.uri();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("Request processed successfully");
    }


}
