package com.adjh.springbootchatgpt.dto;

import lombok.*;

/**
 * 프롬프트 요청 DTO : gpt-3.5-turbo-instruct, babbage-002, davinci-002
 *
 * @author : lee
 * @fileName : CompletionDto
 * @since : 12/29/23
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompletionDto {

    // 사용할 모델
    private String model;

    // 사용할 프롬프트 명령어
    private String prompt;

    // 프롬프트의 다양성을 조절할 명령어(default : 1)
    private float temperature = 1;

    // 최대 사용할 토큰(default : 16)
    private int max_tokens = 16;

    @Builder
    public CompletionDto(String model, String prompt, float temperature, int max_tokens) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }
}
