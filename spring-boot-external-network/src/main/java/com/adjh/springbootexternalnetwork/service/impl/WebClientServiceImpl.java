package com.adjh.springbootexternalnetwork.service.impl;

import com.adjh.springbootexternalnetwork.dto.ApiRequestDto;
import com.adjh.springbootexternalnetwork.dto.PostResponseDto;
import com.adjh.springbootexternalnetwork.service.WebClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : WebClientServiceImpl
 * @since : 11/30/24
 */
@Service
public class WebClientServiceImpl implements WebClientService {

    /**
     * 공용으로 사용하는 WebClient
     *
     * @return Webclient Instance
     */
    @PostConstruct
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")                            // 외부 통신 URL
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)  // 외부 통신 URL Header 구성
                .build();
    }

    /**
     * 1. 파라미터가 존재하지 않는 데이터 통신
     *
     * @return
     */
    @Override
    public List<PostResponseDto> getPosts() {
        return webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/posts")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PostResponseDto>>() {
                })
                .block();
    }

    /**
     * 2. 어노테이션 @PathVariable을 활용한 데이터 통신
     *
     * @param id
     * @return
     */
    @Override
    public PostResponseDto getPostById(String id) {
        return webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/posts/" + id)
                        .build())
                .retrieve()
                .bodyToMono(PostResponseDto.class)
                .block();
    }

    /**
     * 3. 어노테이션 @RequestParam을 활용한 데이터 통신
     *
     * @param id
     * @return
     */
    @Override
    public List<PostResponseDto> getPostsByUserId(String userId) {
        return webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/posts")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PostResponseDto>>() {
                })
                .block();
    }

    /**
     * 4. 어노테이션 @RequestBody을 활용한 데이터 통신
     *
     * @return
     */
    @Override
    public List<PostResponseDto> getPostsList(ApiRequestDto apiRequestDto) {
        return webClient().post()
                .uri("/posts")
                .body(BodyInserters.fromValue(apiRequestDto))
                .retrieve()
                .bodyToFlux(PostResponseDto.class)  // bodyToMono 대신 bodyToFlux 사용
                .collectList()
                .block();
    }
}
