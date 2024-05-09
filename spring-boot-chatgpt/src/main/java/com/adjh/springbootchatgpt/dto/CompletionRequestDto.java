package com.adjh.springbootchatgpt.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 프롬프트 요청 DTO
 *
 * @author : lee
 * @fileName : CompletionRequestDto
 * @since : 12/29/23
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompletionRequestDto {

    private String model;

    private String prompt;

    private float temperature;


    @Builder
    CompletionRequestDto(String model, String prompt, float temperature) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = temperature;
    }

}
