package com.adjh.springbootshort.modules.casting.datatype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 정수 배열을 문자열 배열로 변환하는 함수입니다.
 *
 * @author : jonghoon
 * @fileName : CvtIntArrToStrArrModule
 * @link : https://adjh54.tistory.com/153
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class CvtIntArrToStrArrModule {

    /**
     * 정수 배열을 문자열 배열로 변환합니다 -1 : for문을 이용한 캐스팅 : Integer.toString()
     * - 배열을 순회하면서 Integer.toString()로 캐스팅을 하여서 정수형을 문자열로 변환하고 새로운 문자열 배열을 구성합니다.
     *
     * @param strArr
     * @return
     */
    public static String[] cvtIntArrToStrArr1(int[] strArr) {

        int[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = new String[intArray.length];

        for (int i = 0; i < intArray.length; i++) {
            stringArray[i] = Integer.toString(intArray[i]);
        }

        // print the new string array
        for (int i = 0; i < stringArray.length; i++) {
            System.out.print(stringArray[i] + " ");
        }
        return stringArray;
    }

    /**
     * 정수 배열을 문자열 배열로 변환합니다 -2: Stream을 이용한 캐스팅 : stream().mapToObj()
     *
     * @param strArr
     * @return
     */
    public static int[] cvtIntArrToStrArr2(String[] strArr) {
        int[] intArray = {1, 2, 3, 4, 5};

        String[] stringArray = Arrays.stream(intArray)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        // print the new string array
        for (int i = 0; i < stringArray.length; i++) {
            System.out.print(stringArray[i] + " ");
        }

        return intArray;
    }
}
