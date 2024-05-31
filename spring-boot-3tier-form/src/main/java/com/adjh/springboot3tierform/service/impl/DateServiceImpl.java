package com.adjh.springboot3tierform.service.impl;

import com.adjh.springboot3tierform.dao.DateDao;
import com.adjh.springboot3tierform.model.dto.DateDto;
import com.adjh.springboot3tierform.service.DateService;
import org.springframework.stereotype.Service;

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
    public List<DateDto> selectDateList(DateDto dateDto) {
        List<DateDto> dateList = dateDao.selectDateList(dateDto);
        return dateList;
    }
}
