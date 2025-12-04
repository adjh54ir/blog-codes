package com.adjh.springbootdotenv.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

/**
 * [CASE2] Dotenv와 yml 파일 매핑 설정
 *
 * @author : leejonghoon
 * @fileName : DotEnvConfig2
 * @since : 25. 12. 2.
 */
@Configuration
public class DotEnvConfig2 {

    static {
        /*
         * DotEnv 로드 및 데이터 조회
         */
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        /*
         * DotEnv 로드된 값을 기반으로 순회하여 properties 값 세팅
         */
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}
