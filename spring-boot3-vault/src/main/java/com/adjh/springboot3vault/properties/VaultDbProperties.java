package com.adjh.springboot3vault.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Database Secret Engine 데이터 객체 매핑
 *
 * @author : leejonghoon
 * @fileName : VaultDbProperties
 * @since : 25. 12. 22.
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class VaultDbProperties {
    private String username;
    private String password;
}
