package com.adjh.springboot3vault.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Vault Key-Value Properties Mapping value
 *
 * @author : leejonghoon
 * @fileName : VaultKVProperties
 * @since : 25. 11. 25.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "vault")
public class VaultKVProperties {
    private String appEnv;
    private String dbHost;
    private String dbPassword;
    private String dbUser;
}
