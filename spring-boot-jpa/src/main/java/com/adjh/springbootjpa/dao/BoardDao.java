package com.adjh.springbootjpa.dao;

import com.adjh.springbootjpa.dto.BoardDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 게시판 DAO 인터페이스
 *
 * @author : lee
 * @fileName : BoardDao
 * @since : 5/2/24
 */
@Repository
public interface BoardDao {

    // 게시판 리스트를 조회합니다.
    List<BoardDto> selectBoardList(BoardDto boardDto);

    // 게시판 글을 등록합니다.
    long insertBoard(BoardDto boardDto);

    // 게시판 글을 수정합니다.
    long updateBoard(BoardDto boardDto);

    // 게시판 글을 삭제합니다.
    long deleteBoard(BoardDto boardDto);
}
