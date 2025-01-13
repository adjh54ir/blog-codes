package com.blog.springbootwebfluxkafka.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : MessageDto
 * @since : 2025. 1. 13.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageDto {
    private String content;

    @Builder(toBuilder = true)
    public MessageDto(String content) {
        this.content = content;
    }
}
