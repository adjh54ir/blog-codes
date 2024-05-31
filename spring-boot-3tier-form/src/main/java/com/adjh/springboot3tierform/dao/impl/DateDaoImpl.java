package com.adjh.springboot3tierform.dao.impl;

import com.adjh.springboot3tierform.dao.DateDao;
import com.adjh.springboot3tierform.model.dto.DateDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : DateDaoImpl
 * @since : 24. 5. 31.
 */
@Repository
public class DateDaoImpl implements DateDao {

    private final SqlSession sqlSession;

    public DateDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<DateDto> selectDateList(DateDto dateDto) {
        DateDao dd = sqlSession.getMapper(DateDao.class);
        return dd.selectDateList(dateDto);
    }
}
