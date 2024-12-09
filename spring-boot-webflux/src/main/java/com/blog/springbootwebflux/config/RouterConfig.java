package com.blog.springbootwebflux.config;

import com.blog.springbootwebflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : RouterConfig
 * @since : 2024. 12. 4.
 */
@Configuration
public class RouterConfig {

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
                            .GET("/flux/user", userHandler::findTbUserByUserNm);
                })
                .build();
    }
}


