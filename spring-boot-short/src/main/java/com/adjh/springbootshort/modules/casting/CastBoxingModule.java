package com.adjh.springbootshort.modules.casting;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * [캐스팅] Boxing을 수행합니다(기본 자료형 -> 래퍼 클래스)
 *
 * @author : jonghoon
 * @fileName : CastBoxingModule
 * @link : https://adjh54.tistory.com/120
 * @since : 6/6/24
 */
@Component
public class CastBoxingModule {


    /**
     * Boxing 사용예제 -1
     * Integer(), Integer.valueOf()를 통해 Boxing을 수행합니다.
     *
     * @param num
     * @return
     */
    public Integer CastBoxing(int num) {
        Integer rstInteger = 0;

        /*
         * Java 1.5 이상 버전의 경우 - 오토박싱 (Auto Boxing)으로 별도의 수동적 처리 없이 Boxing 된다.
         * 기본 자료형 -> 래퍼 클래스 변환
         */

        // [Java v1.5이상] 박싱 (Boxing) - 기본 자료형 -> 래퍼 클래스
        int aInt = 3;
        int bInt = 4;
        Integer aInteger = aInt;
        Integer bInteger = bInt;
        System.out.println("aInteger :: " + aInteger + "  bInteger ::" + bInteger);

        /*
         * [CASE1] 박싱(Boxing) 방법
         * Java 1.5 미만 버전의 경우 - 박싱(Boxing)으로 수동적인 처리로 Boxing 해야했다.
         * 기본 자료형 -> 래퍼 클래스 변환
         */
//        int aInt2 = 3;
//        int bInt2 = 4;
//        Integer aInteger2 = new Integer(aInt2);
//        Integer bInteger2 = new Integer(bInt2)
//        System.out.println("aInteger2 :: " + aInteger2 + "  bInteger2 ::" + bInteger2);

        /*
         * [CASE2] 박싱(Boxing) 방법
         * Java 1.5 미만 버전의 경우 - 박싱(Boxing)으로 수동적인 처리로 Boxing 해야했다.
         * 기본 자료형 -> 래퍼 클래스 변환
         */
        int num2 = 10;
        Integer boxedNum = Integer.valueOf(num2);

        double num3 = 3.14;
        Double boxedNum2 = Double.valueOf(num3);

        char c = 'a';
        Character boxedChar = Character.valueOf(c);

        boolean b = true;
        Boolean boxedBool = Boolean.valueOf(b);

        return rstInteger;
    }

    /**
     * Boxing 사용예제 -2
     * stream().boxed() 함수를 이용합니다.
     *
     * @param numArr
     * @return
     */
    public Integer[] CastBoxing2(int[] numArr) {
        Integer[] resultIntegerArr = Arrays.stream(numArr).boxed().sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        return resultIntegerArr;
    }

}
