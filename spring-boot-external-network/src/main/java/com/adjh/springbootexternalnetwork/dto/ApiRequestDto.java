package com.adjh.springbootexternalnetwork.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ApiRequestDto
 * @since : 11/30/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiRequestDto {

    private String title;
    private String body;
    private String userId;

    @Builder
    public ApiRequestDto(String title, String body, String userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
