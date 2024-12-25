package com.adjh.springbootcommon.commons.utils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 숫자를 이용하는 공통유틸
 *
 * @author : lee
 * @fileName : NumberUtils
 * @Method @since : 2/16/24
 * - convertNumToDigit()    : 10진법의 값을 변환할 진수와 전달하여서 각각의 진법에 맞게 변환해주는 함수
 * - convertDigitToNum()    : 변환하려는 값과 해당 진법을 전달하면 10진법으로 변환해준다.
 * - convertStrToASCIIArr() : Convert String to ASCII Code Array
 * - convertASCIIToString() : ASCII Code Array to String
 */
@RequiredArgsConstructor
public class NumberUtils {

    /**
     * 10진법의 값을 변환할 진수와 전달하여서 각각의 진법에 맞게 변환해주는 함수
     *
     * @param num   숫자
     * @param digit 진법
     * @return 10진법 문자열
     */
    public static String convertNumToDigit(int num, int digit) {
        String resultVal = "";
        switch (digit) {

            case 2:
                resultVal = Integer.toBinaryString(num);
                break;
            case 8:
                resultVal = Integer.toHexString(num);
                break;
            case 16:
                resultVal = Integer.toOctalString(num);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
                resultVal = Integer.toString(num, digit);
                break;
            default:
                break;
        }
        return resultVal;
    }

    /**
     * 변환하려는 값과 해당 진법을 전달하면 10진법으로 변환해준다.
     *
     * @param value 변환하려는 값
     * @param digit 진법
     * @return 10진법
     */
    public static int convertDigitToNum(String value, int digit) {
        return Integer.parseInt(value, digit);
    }


    /**
     * Convert String to ASCII Code Array
     *
     * @param str {String}  : 변환하려는 문자열
     * @return {int[]}      : ASCII Code 배열
     */
    public static int[] convertStrToASCIIArr(String str) {

        String[] strArr = str.split("");
        int[] result = new int[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            result[i] = str.charAt(i);
        }
        return result;
    }

    /**
     * ASCII Code Array to String
     *
     * @param intArray {int[]}  :  ASCII Code 배열
     * @return {String}      : 변환하려는 문자열
     */
    public static String convertASCIIToString(int[] intArray) {

        char[] charArr = new char[intArray.length];

        for (int i = 0; i < intArray.length; i++) {
            charArr[i] = (char) intArray[i];
        }

        return Arrays.toString(charArr);
    }
}
