package com.adjh.springbootshort.modules.etc.vacate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 리스트를 비우는데 사용합니다.
 *
 * @author : jonghoon
 * @fileName : VacateListModules
 * @link : https://adjh54.tistory.com/136
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class VacateListModules {

    /**
     * 리스트를 배우는데 사용합니다.: 빈 리스트 객체로 재생성하는 방법 : new ArrayList <>()
     *
     * @return
     */
    public static Object[] vacateList1() {
        Object[] objArr = new Object[0];
        // [문자열] 빈 리스트 객체로 재생성하는 방법
        List<String> list1 = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "berry"));
        list1 = new ArrayList<>();              // []
        return objArr;
    }

    /**
     * 리스트를 배우는데 사용합니다. : 리스트의 값들을 비우는 방법 : clear()
     *
     * @return
     */
    public static Object[] vacateList2() {
        Object[] objArr = new Object[0];
        // [정수] 빈 리스트 객체로 재생성하는 방법
        List<String> list2 = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "berry"));
        list2.clear();                          // []
        return objArr;
    }
}
