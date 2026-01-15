package com.adjh.springboot3vault.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class VaultDebugRunner {

    @Bean
    public ApplicationRunner vaultDbUserRunner(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("REAL USER = " + conn.getMetaData().getUserName());
                System.out.println("REAL URL = " + conn.getMetaData().getURL());
            }
        };
    }
}
