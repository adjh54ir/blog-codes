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

    /**
     * Reactor Resource Control
     * - 글로벌 리소스 비활성화
     * - 이벤트 루프 스레드 설정
     *
     * @return ReactorResourceFactory
     */
    @Bean
    public ReactorResourceFactory reactorResourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false); // 글로벌 리소스 사용 비활성화
        factory.setLoopResources(LoopResources.create("event-loop-", 4, true));
        return factory;
    }

    /**
     * Single Router 구성 예시
     *
     * @param userHandler UserHandler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> singleRouterFunction(UserHandler userHandler) {
        return RouterFunctions
                .route()
                .path("/api/v1", builder -> builder
                        .path("/single", userBuilder -> userBuilder
                                .GET("/user/{userId}", userHandler::findTbUserByUserId)
                                .GET("/users", userHandler::findTbUserByUserNm)
                        )
                )
                .build();
    }


    /**
     * Nested Router 구성 예시
     *
     * @param userHandler UserHandler
     * @param codeHandler CodeHandler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> nestedRouterFunction(UserHandler userHandler, CodeHandler codeHandler) {
        return RouterFunctions
                .route()
                .path("/api/v1", builder -> builder
                        .path("/user", userBuilder -> userBuilder
                                .GET("/user/{userId}", userHandler::findTbUserByUserId)
                                .GET("/users/{userId}", userHandler::getUserById)
                                .POST("/users", userHandler::registerUser)
                        )
                        .path("/code", codeBuilder -> codeBuilder
                                .GET("/{codeId}", codeHandler::findAllByCd)
                        )
                )
                .build();
    }


}


