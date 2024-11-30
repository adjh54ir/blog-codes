package com.adjh.springbootswagger.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger springdoc-ui 구성 파일
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("데모 프로젝트 API Document")
                .version("v0.0.1")
                .description("데모 프로젝트의 API 명세서입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
