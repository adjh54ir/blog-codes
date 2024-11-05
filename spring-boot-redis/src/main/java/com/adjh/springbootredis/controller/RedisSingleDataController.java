package com.adjh.springbootredis.controller;

import com.adjh.springbootredis.dto.RedisDto;
import com.adjh.springbootredis.service.RedisSingleDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Redis 단일 데이터를 조회, 등록, 삭제하는 로직입니다.
 *
 * @author : jonghoon
 * @fileName : RedisSingleDataController
 * @since : 11/5/24
 */
@RestController
@RequestMapping("/api/v1/redis/singleData")
public class RedisSingleDataController {

    private final RedisSingleDataService redisSingleDataService;

    public RedisSingleDataController(RedisSingleDataService redisSingleDataService) {
        this.redisSingleDataService = redisSingleDataService;
    }

    /**
     * Redis 키를 기반으로 단일 데이터의 값을 조회합니다.
     *
     * @param redisDto
     * @return
     */
    @PostMapping("/getValue")
    public ResponseEntity<Object> getValue(@RequestBody RedisDto redisDto) {
        String result = redisSingleDataService.getSingleData(redisDto.getKey());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 단일 데이터 값을 등록/수정합니다.(duration 값이 존재하면 메모리 상 유효시간을 지정합니다.)
     *
     * @param redisDto
     * @return
     */
    @PostMapping("/setValue")
    public ResponseEntity<Object> setValue(@RequestBody RedisDto redisDto) {
        int result = 0;
        if (redisDto.getDuration() == null) {
            result = redisSingleDataService.setSingleData(redisDto.getKey(), redisDto.getValue());
        } else {
            result = redisSingleDataService.setSingleData(redisDto.getKey(), redisDto.getValue(), redisDto.getDuration());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 키를 기반으로 단일 데이터의 값을 삭제합니다.
     *
     * @param redisDto
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteRow(@RequestBody RedisDto redisDto) {
        int result = redisSingleDataService.deleteSingleData(redisDto.getKey());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
