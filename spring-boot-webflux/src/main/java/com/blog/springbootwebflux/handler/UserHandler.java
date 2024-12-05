package com.blog.springbootwebflux.handler;

import com.blog.springbootwebflux.model.dto.UserDto;
import com.blog.springbootwebflux.service.FluxService;
import com.blog.springbootwebflux.service.MonoService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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
    public Mono<ServerResponse> getUserList(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxService.userList(), UserDto.class); // 서비스 메서드 호출
    }

    /**
     * 사용자 아이디를 기반으로 사용자 정보를 조회합니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getUserByUserId(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxService.userList(), UserDto.class); // 서비스 메서드 호출
    }
}
