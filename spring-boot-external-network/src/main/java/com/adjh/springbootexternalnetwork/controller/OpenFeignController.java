package com.adjh.springbootexternalnetwork.controller;

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

    public OpenFeignController(OpenFeignService openFeignService) {
        this.openFeignService = openFeignService;
    }

    /**
     * 게시물을 전체를 조회합니다.
     *
     * @param paramObj
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
}
