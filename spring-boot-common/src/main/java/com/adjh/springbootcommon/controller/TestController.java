package com.adjh.springbootcommon.controller;

import com.adjh.springbootcommon.commons.enums.DayEnum;
import com.adjh.springbootcommon.commons.enums.OperationEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : TestController
 * @since : 24. 5. 22.
 */
@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {

    @GetMapping("/user")
    public ResponseEntity<Object> testMethod() {
        int answer = 0;
        int funcEnum = OperationEnum.PLUS.apply(1, 2);
        String variableEnum = DayEnum.MONDAY.getKoreanTranslation();

        System.out.println("함수성 Enum 활용" + funcEnum);
        System.out.println("코드성 Enum 활용 :: " + variableEnum);

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
