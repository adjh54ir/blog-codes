package com.adjh.springbootshort.modules.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * N진법을 10진법으로 변환하는 함수
 *
 * @author : jonghoon
 * @fileName : CvtNDigitTo10DigitModule
 * @link : https://adjh54.tistory.com/118
 * @since : 6/6/24
 */
@Component
@RequiredArgsConstructor
public class CvtNDigitTo10DigitModule {

    /**
     * [Short] N 진법을 10진법으로 변환하는 방법 변환하는 방법 -1
     * toString() 혹은 toBinaryString() 함수를 이용하는 방법
     *
     * @param num
     * @return
     */
    public static String cvtNDigitTo10Digit(int num) {

        String rstStr = "";
        String bin = "1010";    // 2진법
        String three = "101";   // 3진법
        String five = "20";     // 5진법
        String seven = "13";    // 7진법
        String oct = "12";      // 8진법
        String hex = "a";       // 16진법

        int binarayToDecimal = Integer.parseInt(bin, 2);        // 10
        int threeToDecimal = Integer.parseInt(three, 3);        // 10
        int fiveToDecimal = Integer.parseInt(five, 5);          // 10
        int sevenToDecimal = Integer.parseInt(seven, 7);        // 10
        int octalToDecimal = Integer.parseInt(oct, 8);          // 10
        int hexToDecimal = Integer.parseInt(hex, 16);           // 10

        return rstStr;
    }


}
