package com.adjh.springboot3vault.controller;

import com.adjh.springboot3vault.properties.VaultKVProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bean을 호출하여서 Vault 값을 테스트하기 위한 Controller
 *
 * @author : leejonghoon
 * @fileName : VaultController
 * @since : 25. 11. 25.
 */
@RestController
@RequestMapping("/api/v1/vault")
public class VaultController {

    private final VaultKVProperties vaultKVProperties;

    public VaultController(VaultKVProperties vaultKVProperties) {
        this.vaultKVProperties = vaultKVProperties;
    }

    @GetMapping("/kv")
    public String main() {
        System.out.println("Getter appEnv : " + vaultKVProperties.getAppEnv());
        System.out.println("Getter dbHost : " + vaultKVProperties.getDbHost());
        System.out.println("Getter dbPassword : " + vaultKVProperties.getDbPassword());
        System.out.println("Getter dbUser : " + vaultKVProperties.getDbUser());
        return "임시 테스트 ";
    }
}
