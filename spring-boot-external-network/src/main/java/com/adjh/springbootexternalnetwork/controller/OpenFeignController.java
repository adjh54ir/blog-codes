package com.adjh.springbootexternalnetwork.controller;

import com.adjh.springbootexternalnetwork.service.impl.OpenFeignBusinessService;
import com.adjh.springbootexternalnetwork.service.OpenFeignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * OpenFeign 테스트 Controller
 *
 * @author : jonghoon
 * @fileName : OpenFeignController
 * @since : 11/24/24
 */
@RestController
@RequestMapping("api/v1/openFeign")
public class OpenFeignController {

    private final OpenFeignService openFeignService;
    private final OpenFeignBusinessService openFeignBusinessService;

    public OpenFeignController(OpenFeignService openFeignService, OpenFeignBusinessService openFeignBusinessService) {
        this.openFeignService = openFeignService;
        this.openFeignBusinessService = openFeignBusinessService;
    }

    /**
     * 게시물을 전체를 조회합니다.
     *
     * @return
     */
    @PostMapping("/getPosts")
    public ResponseEntity<Object> getPosts() {
        Object resultObj = openFeignService.getPosts();
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }

    /**
     * 특정 게시물을 조회합니다.
     *
     * @param postId
     * @return
     */
    @PostMapping("/getPost/{postId}")
    public ResponseEntity<Object> getPostById(@PathVariable String postId) {
        Object resultObj = openFeignService.getPostById(postId);
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }


    /**
     * 게시물을 전체를 조회합니다.
     *
     * @return
     */
    @PostMapping("/getPostsFilter")
    public ResponseEntity<Object> getPostsFilter() {
        Object resultObj = openFeignBusinessService.getFilteredPosts();
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }

    /**
     * 특정 게시물을 조회합니다.
     *
     * @param postId
     * @return
     */
    @PostMapping("/getPostFilter/{postId}")
    public ResponseEntity<Object> getPostFilterById(@PathVariable String postId) {
        Object resultObj = openFeignBusinessService.getFilterBodySummary(postId);
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }


    /**
     * 게시물을 전체를 조회합니다.
     *
     * @return
     */
    @PostMapping("/getTodos")
    public ResponseEntity<Object> getPostsAddHeader(@RequestHeader("Authorization") String token) {
        Object resultObj = openFeignService.getTodosAddHeader(token);
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }

    /**
     * 특정 게시물을 조회합니다.
     *
     * @param postId
     * @return
     */
    @PostMapping("/getTodo/{postId}")
    public ResponseEntity<Object> getPostByIdAddHeader(
            @RequestHeader("Authorization") String token,
            @RequestHeader("x-refresh-token") String refreshToken,
            @PathVariable String postId) {
        Object resultObj = openFeignService.getTodoAddHeader(token, refreshToken, postId);
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }

}
