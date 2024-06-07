package com.adjh.springbootshort.modules.casting.datatype;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 배열을 리스트로 변환하는 다양한 방법 (Array to List)
 *
 * @author : jonghoon
 * @fileName : CvtListToArrModule
 * @link : https://adjh54.tistory.com/116
 * @since : 6/6/24
 */
@Component
@RequiredArgsConstructor
public class CvtListToArrModule {

    // **********************************************************************************************
    // ************************ List<Object> to Object[] 변환 방법 *************************************
    // **********************************************************************************************

    /**
     * [Short] 리스트(ArrayList)를 배열(Array)로 변환하는 방법 -1
     * toArray() 함수를 이용한 변환 방법
     *
     * @param objArr
     * @return
     */
    public static Object[] cvtListToArr1(List<Object> objArr) {
        List<Object> objectList = Arrays.asList(objArr);
        // 1. [컬렉션 함수 -> 배열] 배열의 사이즈를 지정하고 값을 넣어줍니다.
        String[] rstArr = objectList.toArray(new String[objectList.size()]);
        return rstArr;
    }

    /**
     * [Short] 배열(Array)를 리스트(ArrayList)로 변환하는 방법 -2
     * for문을 순회하며 변환하는 방법
     *
     * @param objArr
     * @return
     */
    public static Object[] cvtListToArr2(List<Object> objArr) {

        List<Object> objectList = new ArrayList<>(objArr);
        Object[] rstArr = new String[objectList.size()];

        // 2. [컬렉션 함수 -> 배열] for문으로 배열의 값을 넣어줍니다.
        for (int j = 0; j < objectList.size(); j++) {
            rstArr[j] = objectList.get(j);
        }
        return rstArr;
    }


    // **********************************************************************************************
    // ************************ List<Integer> to int[] 변환 방법 **************************************
    // **********************************************************************************************


    /**
     * [Short] 배열(Array)를 리스트(ArrayList)로 변환하는 방법 -1
     * toArray() 함수를 이용한 변환 방법
     *
     * @param objArr
     * @return
     */
    public static int[] cvtListToArr3(List<Integer> objArr) {

        // [STEP1] 리스트 구성
        List<Integer> integerList = new ArrayList(objArr);

        // [STEP2] List<Integer> to Integer[]로 변환
        Integer[] integerArr = integerList.toArray(new Integer[integerList.size()]);

        // [STEP3] Integer[] to int[]로 변환: 언박싱
        int[] resultArr = Arrays.stream(integerArr).mapToInt(Integer::intValue).toArray();
        return resultArr;
    }
}
