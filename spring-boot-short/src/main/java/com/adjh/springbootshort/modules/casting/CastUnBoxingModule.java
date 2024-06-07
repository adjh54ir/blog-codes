package com.adjh.springbootshort.modules.casting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [캐스팅] Unboxing 수행합니다(래퍼 클래스 -> 기본 자료형)
 *
 * @author : jonghoon
 * @fileName : CastUnBoxingModule
 * @link : https://adjh54.tistory.com/120
 * @since : 6/6/24
 */
@Component
@RequiredArgsConstructor
public class CastUnBoxingModule {

    /**
     * Unboxing 사용예제 -1
     * int 대입, Integer.intValue() 사용
     *
     * @param integer
     * @return
     */
    public static int CastUnBoxing1(Integer integer) {
        int rstInt = 0;
        /*
         * Java 1.5 이상 버전의 경우 - 오토 언박싱 (Auto UnBoxing)으로 별도의 수동적 처리 없이 UnBoxing 된다.
         * 래퍼 클래스 -> 기본 자료형
         */
        Integer cInteger = 3;
        Integer dInteger = 4;
        int cInt = cInteger;
        int dInt = dInteger;

        System.out.println("cInt :: " + cInt + "  dInt :: " + dInt);

        /*
         * Java 1.5 미만 버전의 경우 - 박싱(UnBoxing)으로 수동적인 처리로 UnBoxing 해야했다.
         * 래퍼 클래스 -> 기본 자료형
         */
        Integer cInteger2 = new Integer(3);
        Integer dInteger2 = new Integer(4);
        int cInt2 = cInteger2.intValue();
        int dInt2 = dInteger2.intValue();
        System.out.println("cInt2 :: " + cInt2 + "  dInt2 :: " + dInt2);

        return rstInt;
    }


    /**
     * Unboxing 사용예제 -2
     * stream().mapToInt() 사용
     *
     * @param integerArr
     * @return
     */
    public static int[] CastUnBoxing2(Integer[] integerArr) {
        int[] numArr = Arrays.stream(integerArr).mapToInt(Integer::intValue).toArray();
        return numArr;
    }
}
