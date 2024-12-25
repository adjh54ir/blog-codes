package com.adjh.springbootexternalnetwork.controller;

import com.adjh.springbootexternalnetwork.service.RestTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : RestTemplateController
 * @since : 11/23/24
 */
@RestController
@RequestMapping("api/v1/restTemplate")
public class RestTemplateController {

    private final RestTemplateService restTemplateService;


    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @PostMapping("/getPost")
    public ResponseEntity<Object> restTemplateCall(@RequestBody Object paramObj) {
        Object resultObj = restTemplateService.externalCmnc(paramObj);
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }


}
