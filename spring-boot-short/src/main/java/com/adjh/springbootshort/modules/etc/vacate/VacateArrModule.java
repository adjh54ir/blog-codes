package com.adjh.springbootshort.modules.etc.vacate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 배열을 비우는데 사용됩니다.
 *
 * @author : jonghoon
 * @fileName : VacateArrModule
 * @link : https://adjh54.tistory.com/136
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class VacateArrModule {

    /**
     * 문자열 배열 혹은 정수 배열을 비웁니다. : 빈 배열 객체로 재 생성하는 방법: new String[], new Int[]
     *
     * @return
     */
    public static Object[] vacateArr1() {

        Object[] objArr = new Object[0];
        // [문자열] 빈 배열 객체로 재생성하는 방법
        String[] strArr = {"apple", "banana", "cherry", "berry"};
        strArr = new String[strArr.length];     // [null, null, null, null]

        // [정수] 빈 배열 객체로 재생성하는 방법
        int[] intArr = {1, 2, 3, 4};
        intArr = new int[intArr.length];        // 0, 0, 0, 0
        return objArr;
    }

    /**
     * 문자열 배열 혹은 정수 배열을 비웁니다. : null 값을 반복하며 채워 가며 비우는 방법: Arrays.fill()
     *
     * @return
     */
    public static Object[] vacateArr2() {
        Object[] objArr = new Object[0];
        // [문자열] null 값을 반복하며 채워 가며 비우는 방법
        String[] strArr2 = {"apple", "banana", "cherry", "berry"};
        Arrays.fill(strArr2, null);         // [null, null, null, null]

        // [정수] null 값을 반복하며 채워 가며 비우는 방법
        int[] intArr2 = {1, 2, 3, 4};
        Arrays.fill(intArr2, 0);            // [0, 0, 0, 0]

        return objArr;
    }

    /**
     * 문자열 배열 혹은 정수 배열을 비웁니다. : for문을 순회하면서 값을 비우는 방법: for 문
     *
     * @return
     */
    public static Object[] vacateArr3() {
        Object[] objArr = new Object[0];

        // [문자열] Stream으로 순회하면서 값을 비우는 방법
        String[] strArr3 = {"apple", "banana", "cherry", "berry"};
        for (int i = 0; i < strArr3.length; i++) {
            strArr3[i] = new String();
        }                                       // ["", "", "", ""]

        // [정수] Stream으로 순회하면서 값을 비우는 방법
        int[] intArr3 = {1, 2, 3, 4};
        for (int i = 0; i < intArr3.length; i++) {
            intArr3[i] = 0;
        }                                       // [0, 0, 0, 0]
        return objArr;
    }
}
