package com.adjh.springboot4init.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 환경을 설정합니다.
 *
 * @author : leejonghoon
 * @fileName : WebConfiguration
 * @since : 26. 1. 20.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//        configurer.useRequestHeader("API-Version");
    }
}
