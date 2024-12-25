package com.adjh.springbootshort.modules.casting.datatype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 문자열 배열과 정수 배열을 변환 하는 모듈
 *
 * @author : jonghoon
 * @fileName : CvtStrArrToIntArrModule
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class CvtStrArrToIntArrModule {

    /**
     * 문자열 배열을 정수 배열로 변환-1 : for문을 이용한 캐스팅 : Integer.parseInt()
     * - 배열을 순회하면서 Integer.parseInt()로 캐스팅을 하여서 문자열을 정수형으로 변환하고 새로운 정수형 배열을 구성합니다.
     *
     * @param strArr
     * @return
     */
    public static int[] cvtStrArrToIntArr1(String[] strArr) {
        String[] stringArray = {"1", "2", "3", "4", "5"};
        int[] intArray = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        // print the new integer array
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + " ");
        }
        return intArray;
    }

    /**
     * 문자열 배열을 정수 배열로 변환-2: Stream을 이용한 캐스팅 : stream().mapToInt()
     * - Stream API를 이용하여서 변경하는 방법입니다. Arrays.stream() 메서드를 사용하여 문자열 배열을 스트림으로 변환하고 mapToInt() 메서드를 사용하여 각 문자열 요소를 정수형으로 변환합니다.
     * - 이를 통해 toArray() 메서드를 사용하여 새로운 정수형 배열을 생성합니다.
     *
     * @param strArr
     * @return
     */
    public static int[] cvtStrArrToIntArr2(String[] strArr) {

        String[] stringArray = {"1", "2", "3", "4", "5"};

        int[] intArray = Arrays.stream(stringArray)
                .mapToInt(Integer::parseInt)
                .toArray();

        // print the new integer array
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + " ");
        }
        return intArray;
    }


}
