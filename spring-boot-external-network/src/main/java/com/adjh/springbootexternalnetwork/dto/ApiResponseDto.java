package com.adjh.springbootexternalnetwork.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ApiResponseDto
 * @since : 11/23/24
 */
@Getter
@Setter
@NoArgsConstructor
public class ApiResponseDto {
    private HeaderDto headers;
    private List<PostResponseDto> body;
}
