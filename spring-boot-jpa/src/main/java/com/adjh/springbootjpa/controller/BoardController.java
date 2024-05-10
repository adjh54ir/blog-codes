package com.adjh.springbootjpa.controller;

import com.adjh.springbootjpa.dto.BoardDto;
import com.adjh.springbootjpa.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시판 관리 Controller
 *
 * @author : jonghoon
 * @fileName : BoardController
 * @since : 5/4/24
 */
@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;


    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    /**
     * 게시판 리스트 조회
     *
     * @param boardDto
     * @return
     */
    @PostMapping("/boards")
    public ResponseEntity<List<BoardDto>> selectUserList(@RequestBody BoardDto boardDto) {
        List<BoardDto> boardDtoList = boardService.selectBoardList(boardDto);
        return new ResponseEntity<>(boardDtoList, HttpStatus.OK);
    }

    /**
     * 게시판 글 등록
     *
     * @param boardDto
     * @return
     */
    @PostMapping("/board")
    public ResponseEntity<Long> insertBoard(@RequestBody BoardDto boardDto) {

        long result = boardService.insertBoard(boardDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 게시판 글 수정
     *
     * @param boardDto
     * @return
     */
    @PutMapping("/board")
    public ResponseEntity<Long> updateBoard(@RequestBody BoardDto boardDto) {
        long result = boardService.updateBoard(boardDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 게시판 글 삭제
     *
     * @param boardDto
     * @return
     */
    @DeleteMapping("/board")
    public ResponseEntity<Long> deleteBoard(@RequestBody BoardDto boardDto) {
        long result = boardService.deleteBoard(boardDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
