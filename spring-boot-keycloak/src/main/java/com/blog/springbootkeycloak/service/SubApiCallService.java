package com.blog.springbootkeycloak.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : SubApiCallService
 * @since : 2025. 1. 31.
 */
@FeignClient(
        name = "sub-api-call",
        url = "http://localhost:8081/api/v1"
)
public interface SubApiCallService {

    /**
     * @param accessToken
     * @return
     */
    @PostMapping(
            value = "/receive/accessToken"
    )
    String sendAccessToken(@RequestHeader("Authorization") String accessToken);

}
