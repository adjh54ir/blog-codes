package com.blog.springbootwebflux.config;

import com.blog.springbootwebflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ReactorResourceFactory;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.resources.LoopResources;

/**
 * Please explain the class!!
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
     * @param userHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
        return RouterFunctions
                .route()
                .path("/api/v1", builder -> {
                    builder
                            .GET("/mono/user/{userId}", userHandler::findTbUserByUserId)
                            .GET("/flux/user", userHandler::findTbUserByUserNm)
                            .POST("/user/user", userHandler::registerUser);

                })
                .build();
    }
}


