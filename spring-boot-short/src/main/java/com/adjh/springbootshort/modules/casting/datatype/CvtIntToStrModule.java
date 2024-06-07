package com.adjh.springbootshort.modules.casting.datatype;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 정수를 문자열로 변환하는 방법
 *
 * @author : jonghoon
 * @fileName : CvtIntToStrModule
 * @link https://adjh54.tistory.com/128
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class CvtIntToStrModule {
    /**
     * [Short] 정수를 문자열로 변환 방법-1 : 부호를 포함한 방법
     * "+", "-" + String.valueOf() 함수를 이용하는 방법
     *
     * @param num
     * @return
     */
    public static String cvtIntToStr1(int num) {
        String rst = "";
        int i1 = 12000;
        String s1 = "+" + String.valueOf(i1);
        String s2 = "-" + String.valueOf(i1);
        return rst;
    }

    /**
     * [Short] 정수를 문자열로 변환 방법-2 : 부호를 포함한 방법
     * "+", "-" + String.valueOf() 함수를 이용하는 방법
     *
     * @param num
     * @return
     */
    public static String cvtIntToStr2(int num) {
        String rst = "";
        int plusNum = 4;
        int minusNum = Integer.parseInt(String.valueOf("-" + plusNum));     // -4
        return rst;
    }


}