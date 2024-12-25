package com.blog.springbootwebflux.config;

import com.blog.springbootwebflux.handler.CodeHandler;
import com.blog.springbootwebflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ReactorResourceFactory;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.resources.LoopResources;

/**
 * API Endpoint 관리하는 Router Function입니다.
 *
 * @author : leejonghoon
 * @fileName : RouterConfig
 * @since : 2024. 12. 4.
 */
@Configuration
public class RouterConfig {

    @Bean
    public ReactorResourceFactory reactorResourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false); // 글로벌 리소스 사용 비활성화
        factory.setLoopResources(LoopResources.create("event-loop-", 4, true));
        return factory;
    }

    /**
     * 사용자 라우터를 구성합니다.
     *
     * @param userHandler UserHandler
     * @param codeHandler CodeHandler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler, CodeHandler codeHandler) {
        return RouterFunctions
                .route()
                .path("/api/v1", builder -> builder
                        .path("/user", userBuilder -> userBuilder
                                .GET("/user/{userId}", userHandler::findTbUserByUserId)
                                .GET("/users", userHandler::findTbUserByUserNm)
                        )
                        .path("/code", codeBuilder -> codeBuilder
                                .GET("/{codeId}", codeHandler::findAllByCd)
                        )
                )
                .build();
    }


}


