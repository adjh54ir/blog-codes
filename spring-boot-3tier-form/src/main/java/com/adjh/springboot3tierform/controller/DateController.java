package com.adjh.springboot3tierform.controller;

import com.adjh.springboot3tierform.model.dto.DateDto;
import com.adjh.springboot3tierform.model.dto.DateInfoDto;
import com.adjh.springboot3tierform.service.DateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @PostMapping("/dates")
    public ResponseEntity<Object> selectDateList() {
        List<DateInfoDto> resultList = new ArrayList<>();
        try {
            resultList = dateService.selectDateList();
//            for (DateDto dtoItem : resultList) {
//                System.out.println("dtoItem :: " + dtoItem.toString());
//            }
//            System.out.println("결과 값 :: " + resultList.toString());

        } catch (Exception e) {
            System.out.println("e :: " + e.getMessage());
        }
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PostMapping("/date")
    public ResponseEntity<Integer> insertDate(@RequestBody DateDto dateDto) {
        int result = dateService.insertDate(dateDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
