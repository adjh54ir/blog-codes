package com.adjh.springbootdotenv.controller;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Env 불러오는 Controller
 *
 * @author : leejonghoon
 * @fileName : DotEnvController
 * @since : 25. 12. 2.
 */
@RestController
@RequestMapping("/api/v1/env")
public class DotEnvController {

//    @Autowired
//    private Dotenv dotenv;

    // or

    private final Dotenv dotenv;

    public DotEnvController(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    @GetMapping("/env")
    public String main() {
        String appEnv = dotenv.get("APP_ENV");
        String dbHost = dotenv.get("DB_HOST");
        String dbUser = dotenv.get("DB_USER");
        String testKey = dotenv.get("TEST_KEY");

        System.out.println("App env: " + appEnv);
        System.out.println("DB host: " + dbHost);
        System.out.println("DB user: " + dbUser);
        System.out.println("Test key: " + testKey);
        return "임시 테스트 ";
    }
}
