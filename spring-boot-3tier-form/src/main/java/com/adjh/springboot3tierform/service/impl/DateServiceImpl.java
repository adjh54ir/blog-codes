package com.adjh.springboot3tierform.service.impl;

import com.adjh.springboot3tierform.dao.DateDao;
import com.adjh.springboot3tierform.model.dto.DateDto;
import com.adjh.springboot3tierform.model.dto.DateInfoDto;
import com.adjh.springboot3tierform.service.DateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : DateServiceImpl
 * @since : 24. 5. 31.
 */
@Service
public class DateServiceImpl implements DateService {

    private final DateDao dateDao;

    public DateServiceImpl(DateDao dateDao) {
        this.dateDao = dateDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DateInfoDto> selectDateList() {
        List<DateInfoDto> dateList = dateDao.selectDateList();
        return dateList;
    }

    @Override
    @Transactional
    public int insertDate(DateDto dateDto) {
        int result = dateDao.insertDate(dateDto);
        return result;
    }
}
