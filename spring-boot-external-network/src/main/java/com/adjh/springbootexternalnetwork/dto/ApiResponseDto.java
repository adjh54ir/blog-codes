package com.adjh.springbootexternalnetwork.dto;

import lombok.*;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ApiResponseDto
 * @since : 11/23/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponseDto {
    private HeaderDto headers;
    private List<PostResponseDto> body;

    @Builder
    public ApiResponseDto(HeaderDto headers, List<PostResponseDto> body) {
        this.headers = headers;
        this.body = body;
    }
}
