package com.blog.springbootkeycloak.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ClientCredentialService
 * @since : 25. 1. 30.
 */
@Service
@FeignClient(name = "keycloak-sub-call", url = "http://localhost:8081")
public interface ClientCredentialService {

    @GetMapping
    String getProtectedResource(@RequestHeader("Authorization") String bearerToken);
}
