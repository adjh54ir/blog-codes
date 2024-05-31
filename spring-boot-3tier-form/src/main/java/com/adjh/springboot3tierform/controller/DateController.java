package com.adjh.springboot3tierform.controller;

import com.adjh.springboot3tierform.model.dto.DateDto;
import com.adjh.springboot3tierform.service.DateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : DateController
 * @since : 24. 5. 31.
 */
@RestController
@RequestMapping("/api/v1/date")
public class DateController {

    private DateService dateService;

    public DateController(DateService dateService) {
        this.dateService = dateService;
    }

    @PostMapping("/date")
    public ResponseEntity<List<DateDto>> selectDateList(@RequestBody DateDto dateDto) {
        List<DateDto> result = dateService.selectDateList(dateDto);

        System.out.println("결과 값 :: " + result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
