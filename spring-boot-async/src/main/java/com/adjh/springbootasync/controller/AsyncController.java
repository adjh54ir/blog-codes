package com.adjh.springbootasync.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : AsyncController
 * @since : 7/13/24
 */
@RestController
@RequestMapping("api/v1/async")
public class AsyncController {

    @PostMapping("/chkThread")
    public void checkThread() {
        System.out.println("Thread Name :: " + Thread.currentThread().getName());
    }
}
