package com.adjh.springbootdotenv.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [CASE1] DotEnv 자체만 가져오는 설정
 *
 * @author : leejonghoon
 * @fileName : DotEnvConfig
 * @since : 25. 12. 01.
 */
@Configuration
public class DotEnvConfig {

    private final Dotenv dotenv;

    /**
     * DotEnv 로드 및 데이터 조회
     */
    public DotEnvConfig() {
        this.dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }

    /**
     * Dot Env 빈 등록
     * @return
     */
    @Bean
    public Dotenv dotenv() {
        return this.dotenv;
    }
}



