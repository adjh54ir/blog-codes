package com.adjh.springbootshort.modules.casting.datatype;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 문자열을 정수로 변환하는 방법
 *
 * @author : jonghoon
 * @fileName : CvtStrToIntModule
 * @since : 6/7/24
 */
@Component
@RequiredArgsConstructor
public class CvtStrToIntModule {

    /**
     * [Short] 문자열을 정수로 변환 방법-1 : 부호 포함.
     * Integer.parseInt() 함수 이용
     *
     * @param str
     * @return
     */
    public static int cvtStrToInt(String str) {
        int rst = 0;
        String s1 = "-1234";
        String s2 = "+1234";
        int minusInt = Integer.parseInt(s1);    // -1234
        int plusInt = Integer.parseInt(s2);     // +1234
        return rst;
    }

    /**
     * [Short] 문자열을 정수로 변환 방법-2 : 부호를 제외하는 방법
     * Math.abs() 함수 + Integer.parseInt()를 이용하는 방법
     *
     * @param str
     * @return
     */
    public int cvtStrToInt2(String str) {
        int rst = 0;
        /*
         * int to int : 부호가 존재하는 int 타입을 Math.abs() 함수를 사용하면 "부호"는 유지되지 않습니다.
         */
        int i1 = +12000;
        int i2 = -12000;
        int absPlusInt = Math.abs(i1);      // 12000
        int absMinusInt = Math.abs(i2);     // 12000

        /*
         * String to Integer : 오토 언박싱으로 즉시 int 타입으로 반환 받습니다.
         * 부호가 존재하는 문자열을 Math.abs()로 변환하였을 시 "부호"는 유지되지 않습니다.
         */
        String s3 = "+12000";
        String s4 = "-12000";
        int absPlusInt2 = Math.abs(Integer.parseInt(s3));   // 12000
        int absMinusInt2 = Math.abs(Integer.parseInt(s4));  // 12000

        return rst;
    }

}
