package com.adjh.springbootshort.modules.etc.add;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 정수 배열 내에 요소를 추가합니다.
 *
 * @author : jonghoon
 * @fileName : AddIntArrElementModule
 * @link : https://adjh54.tistory.com/172
 * @since : 6/9/24
 */
@Component
@RequiredArgsConstructor
public class AddIntArrElementModule {


    /**
     * 정수 배열 내에 순서대로 값을 추가합니다-1 : for() 함수를 이용한 방법
     * - for문을 순회하면서 배열의 요소 값을 순차적(1 ~ 10)으로 채웁니다.
     *
     * @return
     */
    public static int[] addIntArrElement1() {
        int[] arr1 = new int[10];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr1)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        return arr1;
    }

    /**
     * 정수 배열 내에 순서대로 값을 추가합니다-2 : Arrays.setAll() 예시
     * -  for문을 순회하면서 배열의 요소 값을 순차적(1 ~ 10)으로 채웁니다.
     *
     * @return
     */
    public static int[] addIntArrElement2() {
        int n = 10;
        int[] arr = new int[n];
        Arrays.setAll(arr, i -> i + 1);
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        return arr;
    }

    /**
     * 정수 배열 내에 순서대로 값을 추가합니다-3 : IntStream.rangeClose() 예시
     * - for문을 순회하면서 배열의 요소 값을 순차적(1 ~ 10)으로 채웁니다.
     *
     * @return
     */
    public static int[] addIntArrElement3() {
        int[] arr = IntStream.rangeClosed(1, 10).toArray(); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        return arr;
    }

    /**
     * 정수 배열 내에 순서대로 값을 추가합니다-4: IntStream.range() 예시
     * - 메서드는 주어진 값의 범위에서 순차적이고 순서대로 요소 Stream을 생성하는 데 사용됩니다.
     *
     * @return
     */
    public static int[] addIntArrElement4() {
        int[] arr = IntStream.range(1, 11).toArray();
        return arr;
    }

}
