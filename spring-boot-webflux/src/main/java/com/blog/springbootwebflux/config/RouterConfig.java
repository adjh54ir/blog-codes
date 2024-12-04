package com.blog.springbootwebflux.config;

import com.blog.springbootwebflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

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
                .path("/api/v1/mono", builder -> {
                    builder
                            .POST("/user", userHandler::getUserList)
                            .GET("/user/{userId}", userHandler::getUserByUserId);
                })
                .build();
    }
}
