package com.adjh.springbootshort.modules.casting.datatype;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 배열을 리스트로 변환하는 다양한 방법 (Array to List)
 *
 * @author : jonghoon
 * @fileName : CvtArrToListModule
 * @link : https://adjh54.tistory.com/116
 * @since : 6/6/24
 */
@Component
@RequiredArgsConstructor
public class CvtArrToListModule {


    // **********************************************************************************************
    // ************************ Object[] to List<Object> 변환 방법 *************************************
    // **********************************************************************************************

    /**
     * [Short] 배열(Array)를 리스트(ArrayList)로 변환하는 방법 -1
     * asList()를 이용한 방법
     *
     * @param objArr
     * @return
     */
    public static List<?> cvtArrayToList1(Object[] objArr) {

        // [배열 -> 컬렉션 함수] 배열 리스트(ArrayList) 선언 및 초기화합니다.
        List<?> strArrList = Arrays.asList(objArr);

        return strArrList;
    }

    /**
     * [Short] 배열(Array)를 리스트(ArrayList)로 변환하는 방법 -2
     * asList()를 이용한 방법 : 초기화를 수행
     *
     * @param objArr
     * @return
     */
    public static List<?> cvtArrayToList2(Object[] objArr) {
        // [배열 -> 컬렉션 함수] 배열 리스트(ArrayList) 선언 및 초기화합니다.
        List<?> objList = new ArrayList<>(Arrays.asList(objArr));
        return objList;
    }

    /**
     * [Short] 배열(Array)를 리스트(ArrayList)로 변환하는 방법 -3
     * for문을 이용하여 순회하며 리스트를 구성하는 방법
     *
     * @param objArr
     * @return
     */
    public static List<?> cvtArrayToList3(Object[] objArr) {
        List<Object> objList = new ArrayList<>();

        for (Object s : objArr) {
            objList.add(s);
        }
        return objList;
    }

    /**
     * [Short] 배열(Array)를 리스트(ArrayList)로 변환하는 방법 -4
     * Stream을 이용한 방법
     *
     * @param objArr
     * @return
     */
    public static List<?> cvtArrayToList4(Object[] objArr) {
        List<?> objList = Arrays.stream(objArr).collect(Collectors.toList());
        return objList;
    }


    // **********************************************************************************************
    // ************************ int[] to List<Integer> 변환 방법 **************************************
    // **********************************************************************************************


    /**
     * [Short] 배열(Array)를 리스트(ArrayList)로 변환하는 방법(int[] to List<Integer>) -1
     * for문을 이용하는 방법
     *
     * @param intArr
     * @return
     */
    public static List<Integer> CvtIntArrToIntegerList1(int[] intArr) {

        List<Integer> integerList = new ArrayList<>();

        for (int intItem : intArr) {
            integerList.add(intItem);
        }
        return integerList;
    }

    /**
     * [Short] 배열(Array)를 리스트(ArrayList)로 변환하는 방법(int[] to List<Integer>) -2
     * stream().boxed()를 이용한 방법
     *
     * @param intArr
     * @return
     */
    public static List<Integer> cvtIntArrToIntegerList2(int[] intArr) {
        List<Integer> integerList = Arrays.stream(intArr).boxed().collect(Collectors.toList());
        return integerList;
    }


}
