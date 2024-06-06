package com.adjh.springbootshort.modules.convert;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ASCII 코드를 문자열로 변환하는 모듈
 *
 * @author : jonghoon
 * @fileName : CvtAsciiCodeToStrMoudule
 * @link https://adjh54.tistory.com/117
 * @since : 6/6/24
 */
@Component
public class CvtAsciiCodeToStrModule {


    /**
     * [Short] ASCII Code를 문자열(String)로 변환 방법 -1
     * - charAt() 함수를 이용하여 변환하는 방법
     *
     * @param objArr
     * @return
     */
    public String cvtStrToAsciiCode1(int[] asciiNumArr) {
        String rstStr = "";
        char[] charArr = new char[asciiNumArr.length];

        // int to String
        for (int i = 0; i < asciiNumArr.length; i++) {
            rstStr += (char) asciiNumArr[i];
        }

        return rstStr;
    }
}
