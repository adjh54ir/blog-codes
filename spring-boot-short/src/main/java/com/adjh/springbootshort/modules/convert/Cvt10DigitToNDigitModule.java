package com.adjh.springbootshort.modules.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 10진법을 N진법으로 변환하는 함수
 *
 * @author : jonghoon
 * @fileName : Cvt10DigitToNDigitModule
 * @link : https://adjh54.tistory.com/118
 * @since : 6/6/24
 */
@Component
@RequiredArgsConstructor
public class Cvt10DigitToNDigitModule {

    /**
     * [Short] 10진법을 N 진법으로 변환하는 방법 변환하는 방법 -1
     * toString() 혹은 toBinaryString() 함수를 이용하는 방법
     *
     * @param num
     * @return
     */
    public static String cvt10DigitToNDigit1(int num) {

        String rstStr = "";
        /*
         * [CASE1] 10진법을 2진법으로 변환 방법 : Decimal notation to binary notation
         */
        String decimalToBinary1 = Integer.toString(num, 2);         // "1010"
        String decimalToBinary2 = Integer.toBinaryString(num);           // "1010"

        /*
         * [CASE2] 10진법을 8진법으로 변환 방법: Decimal notation to octal notation
         */
        String decimalToOctal1 = Integer.toString(num, 8);          // "12"
        String decimalToOctal2 = Integer.toOctalString(num);             // "12"

        /*
         * [CASE3] 10진법을 16진법으로 변환 방법: Decimal notation to hexadecimal notation
         */
        String decimalToHex1 = Integer.toString(num, 16);           // "a"
        String decimalToHex2 = Integer.toHexString(num);                 // "a"

        /*
         * [CASE3] 10진법을 n진법 변환 방법: Decimal notation to n notation
         */
        String decimalToThree = Integer.toString(num, 3);           // "101"
        String decimalToFive = Integer.toString(num, 5);            // "20"
        String decimalToSeven = Integer.toString(num, 7);           // "13"

        return rstStr;
    }

}
