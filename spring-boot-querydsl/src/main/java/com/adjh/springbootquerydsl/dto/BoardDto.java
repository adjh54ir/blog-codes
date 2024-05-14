package com.adjh.springbootquerydsl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Board 데이터 관리
 *
 * @author : lee
 * @fileName : BoardDto
 * @since : 5/2/24
 */
@Getter
@ToString
@NoArgsConstructor
public class BoardDto {
    private long boardSq;

    private String title;

    private String content;

    private String boardType;

    @Builder
    public BoardDto(long boardSq, String title, String content, String boardType) {
        this.boardSq = boardSq;
        this.title = title;
        this.content = content;
        this.boardType = boardType;
    }
}
