package com.blog.springbootwebflux.handler;

import com.blog.springbootwebflux.model.dto.MailTxtSendDto;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.repository.UserRepository;
import com.blog.springbootwebflux.service.EmailService;
import com.blog.springbootwebflux.service.FluxService;
import com.blog.springbootwebflux.service.MonoService;
import com.blog.springbootwebflux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;
    private final EmailService emailService;

    public UserHandler(MonoService monoService, FluxService fluxService, UserService userService, EmailService emailService) {
        this.monoService = monoService;
        this.fluxService = fluxService;
        this.userService = userService;
        this.emailService = emailService;
    }


    /**
     * 사용자를 등록하고 성공 메일을 보냅니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> registerUser(ServerRequest request) {
        return request.bodyToMono(UserEntity.class)
                .flatMap(userService::userRegister)
                .flatMap(result -> ServerResponse.ok().bodyValue(result))
                .onErrorResume(e -> ServerResponse.ok().bodyValue(0));
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
