package com.adjh.springbootshort.modules.calculate;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 랜덤 숫자 사용 및 활용 방법
 *
 * @author : jonghoon
 * @fileName : CalcRandomModule
 * @link https://adjh54.tistory.com/130
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class CalcRandomModule {


    /**
     * 랜덤 숫자 반환하기 -1: 최대값 범위만 지정
     *
     * @param max
     * @return
     */
    public static int calcMaxRandom(int max) {
        int rst = 0;
        /*
         * 0 이상 101 미만의 랜덤한 난수를 반환합니다.
         * 범위 : 0 ~ 100 랜덤 숫자
         */
        int random0to100 = (int) (Math.random() * max + 1);

        /*
         * 0 이상 1001 미만의 랜덤한 난수를 반환합니다.
         * 범위 : 0 ~ 1000 랜덤 숫자
         */
        int random0to1000 = (int) (Math.random() * max + 1);

        return rst;
    }

    /**
     * 랜덤 숫자 반환하기 -2: 최소, 최대값 지정
     *
     * @param max
     * @return
     */
    public static int calcMaxRandom2(int min, int max) {
        int rst = 0;
        /*
         * 50 이상 201 미만의 랜덤한 난수를 반환합니다.
         * 범위 : 50 ~ 200 랜덤 숫자
         */

        int random50to200 = (int) (Math.random() * max + 1) + min;

        return rst;
    }

    /**
     * 랜덤 숫자 반환하기 -3: 최소값, 최대값 지정 (음수 포함)
     *
     * @param max
     * @return
     */
    public static int calcMaxRandom3(int min, int max) {
        int rst = 0;

        /*
         * 0 이상 -100 미만의 랜덤한 난수를 반환합니다.
         * 범위 : 0 ~ -100 랜덤 숫자
         */
        int random0toM100 = (int) (Math.random() * -100);

        /*
         * -50 이상 -201 미만의 랜덤한 난수를 반환합니다.
         * 범위 : -50 ~ -200 랜덤 숫자
         */
        int randomM50toM200 = (int) (Math.random() * -201) - 50;
        return rst;
    }

    /**
     * 랜덤 문자 반환하기 -4: 문자열 배열 내의 랜덤 값 반환
     *
     * @param strArr
     * @return
     */
    public static String calcMaxStrRandom(String[] strArr) {

        String randomFruit = strArr[(int) (Math.random() * strArr.length)];

        return randomFruit;
    }


}
