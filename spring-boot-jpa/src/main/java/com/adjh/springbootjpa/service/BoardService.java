package com.adjh.springbootjpa.service;

import com.adjh.springbootjpa.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 게시판 서비스 인터페이스
 *
 * @author : jonghoon
 * @fileName : BoardService
 * @since : 5/4/24
 */
@Service
public interface BoardService {

    // 게시판 리스트를 조회합니다.
    List<BoardDto> selectBoardList(BoardDto boardDto);

    // 게시판 글을 등록합니다.
    long insertBoard(BoardDto boardDto);

    // 게시판 글을 업데이트 합니다.
    long updateBoard(BoardDto boardDto);

    // 게시판 글을 삭제합니다.
    long deleteBoard(BoardDto boardDto);
}
