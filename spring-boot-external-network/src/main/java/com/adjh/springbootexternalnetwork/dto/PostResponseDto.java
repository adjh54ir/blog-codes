package com.adjh.springbootexternalnetwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : PostResponseDto
 * @since : 11/24/24
 */
@Getter
@Setter
public class PostResponseDto {
    private Long userId;
    private Long id;
    private String title;
    private String body;

    @Builder
    public PostResponseDto(Long userId, Long id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}