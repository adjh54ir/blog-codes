package com.blog.springbootkeycloak.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ClientCredentialService
 * @since : 25. 1. 30.
 */
@FeignClient(name = "protected-api", url = "http://localhost:8081")
interface ClientCredentialService {

    @GetMapping
    ResponseEntity<String> getProtectedResource(@RequestHeader("Authorization") String bearerToken);
}
