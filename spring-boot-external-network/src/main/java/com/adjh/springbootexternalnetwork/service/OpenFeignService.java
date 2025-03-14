package com.adjh.springbootexternalnetwork.service;

import com.adjh.springbootexternalnetwork.dto.PostResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * json placeholder 외부 통신을 하는 예시
 *
 * @author : jonghoon
 * @fileName : OpenFeignService
 * @since : 11/23/24
 */
@FeignClient(name = "json-placeholder", url = "https://jsonplaceholder.typicode.com")
public interface OpenFeignService {

    @GetMapping("/posts")
    List<PostResponseDto> getPosts();

    @GetMapping("/posts/{id}")
    PostResponseDto getPostById(@PathVariable("id") String id);

    @GetMapping("/todos")
    List<PostResponseDto> getTodosAddHeader(@RequestHeader("Authorization") String token);

    @GetMapping("/todos/{id}")
    PostResponseDto getTodoAddHeader(
            @RequestHeader("Authorization") String authToken,
            @RequestHeader("x-refresh-token") String refreshToken,
            @PathVariable("id") String id);
}
