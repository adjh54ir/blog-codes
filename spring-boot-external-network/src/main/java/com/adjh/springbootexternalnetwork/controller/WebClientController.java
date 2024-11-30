package com.adjh.springbootexternalnetwork.controller;

import com.adjh.springbootexternalnetwork.dto.ApiRequestDto;
import com.adjh.springbootexternalnetwork.dto.ApiResponseDto;
import com.adjh.springbootexternalnetwork.dto.PostResponseDto;
import com.adjh.springbootexternalnetwork.service.WebClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : WebClientController
 * @since : 11/30/24
 */
@RestController
@RequestMapping("api/v1/webClient")
public class WebClientController {

    private final WebClientService webClientService;

    public WebClientController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    /**
     * 1. 파라미터가 존재하지 않는 데이터 통신
     *
     * @return
     */
    @GetMapping("/getPosts")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> resultObj = webClientService.getPosts();
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }

    /**
     * 2. 어노테이션 @PathVariable을 활용한 데이터 통신
     *
     * @return
     */
    @GetMapping("/getPostById/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable String id) {
        PostResponseDto resultObj = webClientService.getPostById(id);
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }

    /**
     * 3. 어노테이션 @RequestParam을 활용한 데이터 통신
     *
     * @return
     */
    @GetMapping("/getPostByUserId")
    public ResponseEntity<List<PostResponseDto>> getPost2(@RequestParam String userId) {
        List<PostResponseDto> resultObj = webClientService.getPostsByUserId(userId);
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }

    /**
     * 4. 어노테이션 @RequestBody을 활용한 데이터 통신
     *
     * @return
     */
    @PostMapping("/getPostList")
    public ResponseEntity<List<PostResponseDto>> getPostList(@RequestBody ApiRequestDto requestDto) {
        List<PostResponseDto> resultObj = webClientService.getPostsList(requestDto);
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }


}
