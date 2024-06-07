package com.adjh.springbootshort.modules.calculate;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * [계산] 진법 간의 사칙 연산을 수행합니다.
 *
 * @author : jonghoon
 * @fileName : CalcDigitModule
 * @link :https://adjh54.tistory.com/118
 * @since : 6/6/24
 */
@Component
@RequiredArgsConstructor
public class CalcDigitModule {
    /**
     * [Short] 10진법을 N 진법으로 변환하는 방법 변환하는 방법 -1
     * toString() 혹은 toBinaryString() 함수를 이용하는 방법
     *
     * @param num
     * @return
     */
    public static String CalcDigit1(int num) {
        String str = "";
        int num1 = 10;
        int num2 = 20;
        String decimalToBinary1 = Integer.toBinaryString(num1);                     // "1010"
        String decimalToBinary2 = Integer.toBinaryString(num2);                     // "10100"

        // 2진법간의 합
        String decimalToBinarySum = Integer.toBinaryString(num1 | num2);         // "11110"
        String decimalToOctal1 = Integer.toOctalString(num1);                       // "12"
        String decimalToOctal2 = Integer.toOctalString(num2);                       // "24"


        // 8진법간의 합
        String decimalToOctalSum = Integer.toOctalString(num1 | num2);           // "36"
        String decimalToHex1 = Integer.toHexString(num1);                           // "a"
        String decimalToHex2 = Integer.toHexString(num2);                           // "14"

        // 16진법간의 합
        String decimalToHexSum = Integer.toHexString(num1 | num2);               // "1e"

        return str;
    }
}
