package com.blog.springbootwebflux.handler;

import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.service.FluxService;
import com.blog.springbootwebflux.service.MonoService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserHandler
 * @since : 2024. 12. 4.
 */
@Component
public class UserHandler {
    private final MonoService monoService; // 서비스 계층 사용
    private final FluxService fluxService;

    public UserHandler(MonoService monoService, FluxService fluxService) {
        this.monoService = monoService;
        this.fluxService = fluxService;
    }

    /**
     * 사용자 리스트를 조회합니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findTbUserByUserId(ServerRequest request) {
        String userId = request.pathVariable("userId");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(monoService.findUserByUserId(userId), UserEntity.class);
    }

    /**
     * 사용자 아이디를 기반으로 사용자 정보를 조회합니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findTbUserByUserNm(ServerRequest request) {
        String userNm = request.queryParam("userNm")
                .orElseThrow(() -> new RuntimeException("userNm is required"));


        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxService.findTbUserByUserNm(userNm).collectList(), List.class);
    }
}
