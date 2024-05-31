package com.adjh.springboot3tierform.service;

import com.adjh.springboot3tierform.model.dto.DateDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : DateService
 * @since : 24. 5. 31.
 */
@Service
public interface DateService {
    List<DateDto> selectDateList(DateDto dateDto);
}
