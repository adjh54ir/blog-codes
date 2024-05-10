package com.adjh.springbootjpa.service.impl;

import com.adjh.springbootjpa.dao.BoardDao;
import com.adjh.springbootjpa.dto.BoardDto;
import com.adjh.springbootjpa.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 게시판과 관련된 비즈니스 로직을 구성합니다.
 *
 * @author : jonghoon
 * @fileName : BoardServiceImpl
 * @since : 5/4/24
 */
@Service
public class BoardServiceImpl implements BoardService {

    private BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public List<BoardDto> selectBoardList(BoardDto boardDto) {
        return boardDao.selectBoardList(boardDto);
    }

    @Override
    public long insertBoard(BoardDto boardDto) {
        return boardDao.insertBoard(boardDto);
    }

    @Override
    public long updateBoard(BoardDto boardDto) {
        return boardDao.updateBoard(boardDto);
    }

    @Override
    public long deleteBoard(BoardDto boardDto) {
        return boardDao.deleteBoard(boardDto);
    }
}
