package com.adjh.springbootshort.modules.etc.vacate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 배열 내의 특정 요소를 비우는데 사용하는 모듈
 *
 * @author : jonghoon
 * @fileName : VacateArrElementModule
 * @link : https://adjh54.tistory.com/137
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class VacateArrElementModule {

    /**
     * 배열의 특정 요소를 비웁니다.-1 : Stream API를 이용한 특정 값 비우기
     *
     * @return
     */
    public static Object[] vacateArrElement1() {
        Object[] objArr = new Object[0];
        // Stream을 이용한 특정 값 제거 방법
        String[] fruitArr1 = {"apple", "banana", "cherry", "berry"};
        fruitArr1 = Arrays.stream(fruitArr1)
                .filter(item -> !item.equals("apple"))
                .toArray(String[]::new);        // ["banana", "cherry", "berry"]
        return objArr;
    }

    /**
     * 배열의 특정 요소를 비웁니다.-2: List의 remove() 메서드로 특정 값 비우기
     *
     * @return
     */
    public static Object[] vacateArrElement2() {
        Object[] objArr = new Object[0];
        // 리스트로 변환하여 제거 방법: 해당 값은 순차적으로 먼저 발견된 값만 제거합니다.
        String[] fruitArr2 = {"apple", "banana", "cherry", "berry", "apple"};
        List<String> fruitArrList = new ArrayList<>(Arrays.asList(fruitArr2));
        fruitArrList.remove("apple");
        fruitArr2 = fruitArrList.toArray(String[]::new);    // ["banana", "cherry", "berry", "apple"]
        return objArr;
    }

    /**
     * 배열의 특정 요소를 비웁니다.-3 : List의 removeAll() 메서드로 특정 값 비우기
     *
     * @return
     */
    public static Object[] vacateArrElement3() {
        Object[] objArr = new Object[0];
        // 리스트로 변환하여 제거 방법: 해당 값은 배열 내에 동일하게 존재하는 모든 값을 제거합니다.
        String[] fruitArr3 = {"apple", "banana", "cherry", "berry", "apple"};
        List<String> fruitArrList3 = Arrays.asList(fruitArr3);
        fruitArrList3.removeAll(Arrays.asList("apple"));
        fruitArr3 = fruitArrList3.toArray(String[]::new);
        return objArr;
    }
}
