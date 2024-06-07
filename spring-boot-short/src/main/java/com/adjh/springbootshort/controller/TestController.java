package com.adjh.springbootshort.controller;

import com.adjh.springbootshort.modules.calculate.CalcDigitModule;
import com.adjh.springbootshort.modules.casting.datatype.CvtArrToListModule;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : TestController
 * @since : 6/8/24
 */
@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    @GetMapping("/main")
    public ResponseEntity<Object> main() {
        String result = "";
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
