package com.blog.springbootwebflux.handler;

import com.blog.springbootwebflux.model.entity.CodeEntity;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : CodeHandler
 * @since : 2024. 12. 5.
 */
@Component
public class CodeHandler {

    private final CodeService codeService;

    public CodeHandler(CodeService codeService) {
        this.codeService = codeService;
    }

    /**
     * 코드 값을 기반으로 코드 정보를 조회합니다.
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> findAllByCd(ServerRequest request) {
        String cd = request.queryParam("cd")
                .orElseThrow(() -> new RuntimeException("cd is required"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(codeService.findAllByCd(cd), CodeEntity.class);
    }
}
