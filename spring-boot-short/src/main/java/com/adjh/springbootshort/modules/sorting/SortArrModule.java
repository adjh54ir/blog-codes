package com.adjh.springbootshort.modules.sorting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 배열을 정렬하는 모듈
 *
 * @author : jonghoon
 * @fileName : SortArrModule
 * @link : https://adjh54.tistory.com/121
 * @since : 6/6/24
 */
@Component
@RequiredArgsConstructor
public class SortArrModule {

    /**
     * 숫자 배열을 정렬하는 방법 -1
     * Arrays.sort() 함수를 이용한 정렬 방법
     *
     * @param objArr
     * @return
     */
    public static Object[] sortingIntArr(Object[] objArr) {

        Integer[] sortNumArr1 = {0, 1, 2, 3, 4};
        Integer[] sortNumArr2 = {10, 11, 1, 2, 4};

        // [CASE1] 숫자 오름차순 정렬 -1 : 오름차순으로 정렬이 됩니다.
        Arrays.sort(sortNumArr1);   // [0, 1, 2, 3, 4]
        Arrays.sort(sortNumArr2);   // [1, 2, 4, 10, 11]

        // [CASE2] 숫자 오름차순 정렬 -2 : 오름차순으로 정렬이 됩니다.
        Arrays.sort(sortNumArr1, Comparator.naturalOrder()); // [0, 1, 2, 3, 4]
        Arrays.sort(sortNumArr2, Comparator.naturalOrder()); // [1, 2, 4, 10, 11]

        // [CASE3] 숫자 내림차순 정렬 -1 : 내림차순으로 정렬이 됩니다.(* 해당 주의 사항은 Wrapper Class 를 이용하여야 합니다.)
        Arrays.sort(sortNumArr1, Collections.reverseOrder());   // [4, 3, 2, 1, 0]
        Arrays.sort(sortNumArr2, Collections.reverseOrder());   // [11, 10, 4, 2, 1]

        // [CASE4] 숫자 내림차순 정렬 -2 : 내림차순으로 정렬이 됩니다.(* 해당 주의 사항은 Wrapper Class 를 이용하여야 합니다.)
        Arrays.sort(sortNumArr1, Comparator.reverseOrder());   // [4, 3, 2, 1, 0]
        Arrays.sort(sortNumArr2, Comparator.reverseOrder());   // [11, 10, 4, 2, 1]

        // [CASE5] 숫자 인덱스 범위 정렬 : 시작 인덱스와 종료 인덱스를 선택하여서 정렬이 됩니다.
        Arrays.sort(sortNumArr1, 0, 3);         // [1, 10, 11, 2, 4]

        Integer[] sortNumArr3 = {10, 11, 1, 2, 4};

        // [CASE6] 숫자 오름차순 정렬 : Lambda를 이용한 오름 차순으로 정렬
        Arrays.sort(sortNumArr3, (s1, s2) -> s1 - s2);          // [1, 2, 4, 10, 11]

        // [CASE7] 숫자 내림차순 정렬 : Lambda를 이용한 내림 차순으로 정렬
        Arrays.sort(sortNumArr3, (s1, s2) -> s2 - s1);          // [11, 10, 4, 2, 1]

        return objArr;
    }

    /**
     * 숫자 배열을 정렬하는 방법 -2 : 역순 정렬 방법
     * for문을 이용한 역순 정렬 수행
     *
     * @param objArr
     * @return
     */
    public static Object[] sortingIntArr2(Object[] objArr) {
        int[] sortNumArr4 = {0, 1, 2, 3, 4};
        int[] remakeNumArr = new int[sortNumArr4.length];

        Arrays.sort(sortNumArr4);
        for (int i = 0; i < sortNumArr4.length; i++) {
            remakeNumArr[i] = sortNumArr4[sortNumArr4.length - 1 - i];
        }
        System.out.println("result :: " + Arrays.toString(remakeNumArr));   // [4, 3, 2, 1, 0]
        return objArr;
    }


    /**
     * 숫자 배열을 정렬하는 방법 -3 : Boxing 이후 정렬
     *
     * @param objArr
     * @return
     */
    public static Integer[] sortingIntArr3(int[] objArr) {
        // 1. int[] -> Integer[] Boxing
        Integer[] integerArr = Arrays.stream(objArr).boxed().toArray(Integer[]::new);

        // 2. Integer[] reverse Order
        Arrays.sort(integerArr, Comparator.reverseOrder()); // [4, 2, 1]

        return integerArr;
    }

    /**
     * 문자 배열을 정렬하는 방법 -4 : Stream을 이용한 정렬 방법
     * Arrays.stream,sorted()를 이용한 정렬 방법
     *
     * @param objArr
     * @return
     */
    public static Object[] sortingIntArr4(Object[] objArr) {
        // 1. Array Initialize
        int[] sortNumArr1 = {10, 11, 1, 2, 4};
        int[] sortNumArr2 = {0, 1, 2, 3, 4};

        // 2.1. Array to IntStream : 배열을 오름차순으로 정렬합니다.
        IntStream sortAscIntStreamType1 = Arrays.stream(sortNumArr1).sorted();

        // 2.2. Array to Stream<Integer> : 배열을 내림차순으로 정렬합니다.
        Stream<Integer> sortDescIntStreamType1 = Arrays.stream(sortNumArr1).boxed().sorted(Comparator.reverseOrder());
        Stream<Integer> sortDescIntStreamType2 = Arrays.stream(sortNumArr2).boxed().sorted((a, b) -> b - a);

        // 3.1. IntStream to int[]
        int[] resultSortedNumArr1 = sortAscIntStreamType1.toArray();    // 결과값 반환(오름차순) : [1, 2, 4, 10, 11]

        // 3.2. Stream<Integer> to int[]
        int[] resultSortedNumArr2 = sortDescIntStreamType1.mapToInt(i -> i).toArray();      // 결과값 반환(내림차순) : [11, 10, 4, 2, 1]
        int[] resultSortedNumArr3 = sortDescIntStreamType2.mapToInt(i -> i).toArray();      // 결과값 반환(내림차순) : [4, 3, 2, 1, 0]

        return objArr;
    }


    /**
     * 문자 배열을 정렬하는 방법 -1
     * sort 함수를 이용한 정렬 수행
     *
     * @param objArr
     * @return
     */
    public static Object[] sortingStrArr1(Object[] objArr) {
        /*
         * 문자열의 정렬-1 : 대소문자를 구분하여 정렬하는 방식
         */
        String[] sortStrArr1 = {"strawberry", "Strawberry", "mango", "Mango", "cherry", "Cherry", "banana", "Banana", "apple", "Apple"};

        // [CASE1] 문자 정렬 : 오름차순으로 정렬합니다.
        Arrays.sort(sortStrArr1);                                           // [Apple, Banana, Cherry, Mango, Strawberry, apple, banana, cherry, mango, strawberry]

        // [CASE2] 문자 정렬 : 내림차순으로 정렬이 됩니다. (* 해당 주의 사항은 Wrapper Class 를 이용하여야 합니다.)
        Arrays.sort(sortStrArr1, Collections.reverseOrder());               // [strawberry, mango, cherry, banana, apple, Strawberry, Mango, Cherry, Banana, Apple]

        return objArr;
    }

    /**
     * 문자 배열을 정렬하는 방법 -2 : 숫자형태이지만 문자열인 경우-> 파싱을 수행하여 진행
     * Integer.parseInt() 함수를 이용하여 처리
     *
     * @param objArr
     * @return
     */
    public static Object[] sortingStrArr2(Object[] objArr) {
        String[] strArr = {"5", "2", "10", "8"};
        int[] intArr = new int[strArr.length];

        // 문자열을 배열로 파싱하여 int[] 배열로 재구성합니다.
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }

        return objArr;
    }


    /**
     * 문자 배열을 정렬하는 방법 -2 : 알파벳 정렬 (대소문자 구분X)
     * String.CASE_INSENSITIVE_ORDER 상수를 적용하여 수행
     *
     * @param objArr
     * @return
     */
    public static Object[] sortingStrArr3(Object[] objArr) {
        /*
         * 문자열의 정렬-2 : 대소문자 구분없이 정렬하는 방식
         */
        String[] sortStrArr2 = {"strawberry", "Strawberry", "mango", "Mango", "cherry", "Cherry", "banana", "Banana", "apple", "Apple"};

        // [CASE1] 문자 정렬 : 오름차순으로 정렬합니다. : 대소문자 구분 O
        Arrays.sort(sortStrArr2, String.CASE_INSENSITIVE_ORDER);                                // [apple, Apple, banana, Banana, cherry, Cherry, mango, Mango, strawberry, Strawberry]

        // [CASE2] 문자 정렬 : 내림차순으로 정렬합니다. : 대소문자 구분 O
        Arrays.sort(sortStrArr2, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        return objArr;
    }


}
