package com.adjh.springbootredis.dto;

import lombok.*;

import java.time.Duration;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RedisDto {
    private String key;
    private String value;
    private Duration duration;


    @Builder
    public RedisDto(String key, String value, Duration duration) {
        this.key = key;
        this.value = value;
        this.duration = duration;
    }
}
