package com.adjh.springboot3tierform.dao;

import com.adjh.springboot3tierform.model.dto.DateDto;
import com.adjh.springboot3tierform.model.dto.DateInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : DateDao
 * @since : 24. 5. 31.
 */
@Repository
public interface DateDao {
    List<DateInfoDto> selectDateList();

    int insertDate(DateDto dateDto);
}
