package com.blog.springbootslack.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessagesDto {
    private String message;


    @Builder
    public MessagesDto(String message) {
        this.message = message;
    }
}
