package com.blog.springbootwebflux.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * 전역 에러 속성을 커스터마이징한 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : GlobalErrorAttributes
 * @since : 24. 12. 28.
 */
@Slf4j
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = getError(request);
        Map<String, Object> map = new HashMap<>();
        if (error instanceof NoResourceFoundException) {
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("errorCode", 404);
            map.put("message", "요청한 리소스를 찾을 수 없습니다.");
        } else if (error instanceof ResponseStatusException) {
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("errorCode", 400);
            map.put("message", "잘못된 요청입니다.");
        } else {
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("errorCode", 500);
            map.put("message", "서버 내부 오류가 발생했습니다.");
        }
        return map;
    }

}