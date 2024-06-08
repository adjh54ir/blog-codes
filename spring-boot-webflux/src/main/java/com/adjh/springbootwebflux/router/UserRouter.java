package com.adjh.springbootwebflux.router;

import com.adjh.springbootwebflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserRouter
 * @since : 6/8/24
 */
@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler handler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/api/v1/user/{userId}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getUserInfoByUserId);
    }
}
