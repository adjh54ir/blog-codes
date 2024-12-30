package com.adjh.springbootswagger.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Swagger springdoc-ui 구성 파일
 */
@Configuration
public class OpenApiConfig {

    @Profile({"loc", "dev"})         // loc, dev 환경에서만 적용
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("데모 프로젝트 API Document")
                .version("v0.0.1")
                .description("데모 프로젝트의 API 명세서입니다.");
        return new OpenAPI()
                .components(new Components())
                .addSecurityItem(new SecurityRequirement().addList("Authentication"))
                .addServersItem(new Server().url("/")) // 상대 경로 추가 및 CORS 에러 방지
                .components(new Components()
                        .addSecuritySchemes("Authentication",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(info);

    }
}
