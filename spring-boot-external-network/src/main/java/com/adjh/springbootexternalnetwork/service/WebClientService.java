package com.adjh.springbootexternalnetwork.service;

import com.adjh.springbootexternalnetwork.dto.ApiRequestDto;
import com.adjh.springbootexternalnetwork.dto.PostResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * WebClient를 활용한 외부 통신을 위한 서비스 구성
 *
 * @author : jonghoon
 * @fileName : WebClientService
 * @since : 11/30/24
 */
@Service
public interface WebClientService {
    // 1. 파라미터가 존재하지 않는 데이터 통신
    List<PostResponseDto> getPosts();

    // 2. 어노테이션 @PathVariable을 활용한 데이터 통신
    PostResponseDto getPostById(String id);

    // 3. 어노테이션 @RequestParam을 활용한 데이터 통신
    List<PostResponseDto> getPostsByUserId(String userId);

    // 4. 어노테이션 @RequestBody을 활용한 데이터 통신
    List<PostResponseDto> getPostsList(ApiRequestDto apiRequestDto);
}
