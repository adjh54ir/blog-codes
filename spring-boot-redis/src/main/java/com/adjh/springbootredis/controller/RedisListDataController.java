package com.adjh.springbootredis.controller;

import com.adjh.springbootredis.dto.RedisDto;
import com.adjh.springbootredis.service.RedisListDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Redis의 리스트 값을 조회, 등록, 삭제하는 로직입니다.
 */
@RestController
@RequestMapping("/api/v1/redis/listData")
public class RedisListDataController {
    private final RedisListDataService redisListDataService;

    public RedisListDataController(RedisListDataService redisListDataService) {
        this.redisListDataService = redisListDataService;
    }

    /**
     * Redis 키를 기반으로 리스트 데이터의 값을 조회합니다.
     *
     * @param key
     * @return
     */
    @GetMapping("/getList")
    public ResponseEntity<Object> getListData(@RequestBody RedisDto redisDto) {
        List<Object> result = redisListDataService.getListData(redisDto.getKey());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 리스트 데이터 값을 등록/수정합니다.(duration 값이 존재하면 메모리 상 유효시간을 지정합니다.)
     *
     * @param redisDto
     * @return
     */
    @PostMapping("/setList")
    public ResponseEntity<Object> setListData(@RequestBody RedisDto redisDto) {
        int result;
        if (redisDto.getDuration() == null) {
            result = redisListDataService.setListData(redisDto.getKey(), redisDto.getValue());
        } else {
            result = redisListDataService.setListData(redisDto.getKey(), redisDto.getValue(), redisDto.getDuration());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 리스트 데이터 내에 값이 존재하는지 여부를 확인합니다.
     *
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/contains")
    public ResponseEntity<Object> isContainsValue(@RequestParam String key, @RequestParam Object value) {
        boolean result = redisListDataService.isContainsValue(key, value);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 키를 기반으로 리스트 내의 특정 데이터를 삭제합니다.
     *
     * @param key
     * @param value
     * @return
     */
    @DeleteMapping("/deleteItem")
    public ResponseEntity<Object> deleteListItem(@RequestParam String key, @RequestParam Object value) {
        int result = redisListDataService.deleteListItem(key, value);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Redis 키를 기반으로 리스트 데이터를 삭제합니다.
     *
     * @param key
     * @return
     */
    @DeleteMapping("/deleteList")
    public ResponseEntity<Object> deleteList(@RequestParam String key) {
        int result = redisListDataService.deleteList(key);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
