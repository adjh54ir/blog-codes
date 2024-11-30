package com.adjh.springbootexternalnetwork.service.impl;

import com.adjh.springbootexternalnetwork.dto.PostResponseDto;
import com.adjh.springbootexternalnetwork.service.OpenFeignService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OpenFeign 통신을 통해 가져온 응답 값을 통해 비즈니스 로직을 처리합니다.
 */
@Service
public class OpenFeignBusinessService {
    private final OpenFeignService openFeignService;

    public OpenFeignBusinessService(OpenFeignService openFeignService) {
        this.openFeignService = openFeignService;
    }

    // 특정 조건으로 게시물 필터링
    public List<PostResponseDto> getFilteredPosts() {
        List<PostResponseDto> posts = openFeignService.getPosts();
        return posts.stream()
                .filter(post -> post.getId() > 30)
                .collect(Collectors.toList());
    }

    // 게시물 데이터 가공
    public PostResponseDto getFilterBodySummary(String id) {
        PostResponseDto post = openFeignService.getPostById(id);
        // 컨텐츠 요약 추가
        if (post.getBody().length() > 100) {
            post.setBody(post.getBody().substring(0, 100) + "...");
        } else {
            post.setBody(post.getBody());
        }
        // 추가 데이터 처리 로직
        return post;
    }
}
