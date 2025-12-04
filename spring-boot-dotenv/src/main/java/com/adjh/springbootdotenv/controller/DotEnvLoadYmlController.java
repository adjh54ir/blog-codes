package com.adjh.springbootdotenv.controller;

import com.adjh.springbootdotenv.config.properties.CustomProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dotenv에서 가져온 값을 yml 파일로 매핑되어서 가져온 값
 *
 * @author : leejonghoon
 * @fileName : DotEnvLoadYmlController
 * @since : 25. 12. 2.
 */
@RestController
@RequestMapping("/api/v1/env")
public class DotEnvLoadYmlController {

    @Value("${custom.appEnv}")
    private String appEnv;

    @Value("${custom.dbHost}")
    private String dbHost;

    @Value("${custom.dbUser}")
    private String dbUser;

    @Value("${custom.testKey}")
    private String testkey;

    @Autowired
    private CustomProperties customProperties;


    @GetMapping("/yml")
    public String main() {

        System.out.println("Getter appEnv :" + customProperties.getAppEnv());
        System.out.println("Getter dbHost :" + customProperties.getDbHost());
        System.out.println("Getter dbUser :" + customProperties.getDbUser());
        System.out.println("Getter testKey :" + customProperties.getTestKey());


        System.out.println("Getter appEnv : " + appEnv);
        System.out.println("Getter dbHost : " + dbHost);
        System.out.println("Getter dbUser : " + dbUser);
        System.out.println("Getter testKey : " + testkey);
        return "임시 테스트 ";
    }

}
