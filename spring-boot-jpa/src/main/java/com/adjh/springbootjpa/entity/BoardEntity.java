package com.adjh.springbootjpa.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : BoardEntity
 * @since : 4/8/24
 */
@Entity
@Getter
@Table(name = "tb_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_sq")
    private long boardSq;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String boardType;

    @Builder
    public BoardEntity(long boardSq, String title, String content, String boardType) {
        this.boardSq = boardSq;
        this.title = title;
        this.content = content;
        this.boardType = boardType;
    }
}
