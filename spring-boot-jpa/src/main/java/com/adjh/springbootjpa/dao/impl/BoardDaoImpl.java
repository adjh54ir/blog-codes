package com.adjh.springbootjpa.dao.impl;

import com.adjh.springbootjpa.dao.BoardDao;
import com.adjh.springbootjpa.dto.BoardDto;
import com.adjh.springbootjpa.entity.QBoardEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * QueryDSL의 데이터 처리를 위한 Dao의 구현체
 *
 * @author : lee
 * @fileName : BoardDaoImpl
 * @since : 5/2/24
 */
@Repository
public class BoardDaoImpl implements BoardDao {

    // 1. 구성한 JPAQueryFactory를 로드 합니다.
    private final JPAQueryFactory queryFactory;

    // 데이터베이스와 매핑된 boardEntity 클래스를 Q-Class
    private final QBoardEntity qBoard = QBoardEntity.boardEntity;

    @PersistenceContext
    private EntityManager em;

    public BoardDaoImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    /**
     * 게시판 리스트를 전체 조회합니다.
     *
     * @param boardDto
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<BoardDto> selectBoardList(BoardDto boardDto) {

        return queryFactory
                .select(Projections.fields(BoardDto.class, qBoard.boardSq, qBoard.boardTitle, qBoard.boardContent, qBoard.boardType))
                .from(qBoard)
                .where(
                        eqTitle(boardDto.getTitle()),
                        eqContent(boardDto.getContent())
                )
                .orderBy(qBoard.boardSq.desc())
                .fetch();
    }

    /**
     * 게시판 글을 등록합니다.
     *
     * @param boardDto
     * @return
     */
    @Override
    @Transactional
    public long insertBoard(BoardDto boardDto) {
        return queryFactory
                .insert(qBoard)
                .columns(qBoard.boardTitle, qBoard.boardContent)
                .values(boardDto.getTitle(), boardDto.getContent())
                .execute();
    }

    /**
     * 게시판 글을 수정합니다.
     *
     * @param boardDto
     * @return
     */
    @Override
    @Transactional
    public long updateBoard(BoardDto boardDto) {
        JPAUpdateClause updateClause = new JPAUpdateClause(em, qBoard);

        // title 값이 존재하는 경우 수행
        if (boardDto.getTitle() != null && !boardDto.getTitle().equals("")) {
            updateClause.set(qBoard.boardTitle, boardDto.getTitle());
        }
        // content 값이 존재하는 경우 수행
        if (boardDto.getContent() != null && !boardDto.getContent().equals("")) {
            updateClause.set(qBoard.boardContent, boardDto.getContent());
        }
        return updateClause
                .where(qBoard.boardSq.eq(boardDto.getBoardSq()))
                .execute();
    }

    /**
     * 게시판 글을 삭제합니다.
     *
     * @param boardDto
     * @return
     */
    @Override
    @Transactional
    public long deleteBoard(BoardDto boardDto) {
        return queryFactory
                .delete(qBoard)
                .where(qBoard.boardSq.eq(boardDto.getBoardSq()))
                .execute();
    }


    /**
     * title 값이 존재하는 경우 조건절로 추가 됩니다.
     *
     * @param title {String}
     * @return BooleanExpression
     */
    private BooleanExpression eqTitle(String title) {
        if (StringUtils.isNullOrEmpty(title)) return null;
        return qBoard.boardTitle.eq(title);
    }

    /**
     * content 값이 존재하는 경우 조건절로 추가됩니다.
     *
     * @param content {String}
     * @return BooleanExpression
     */
    private BooleanExpression eqContent(String content) {
        if (StringUtils.isNullOrEmpty(content)) return null;
        return qBoard.boardContent.containsIgnoreCase(content);
    }
}
