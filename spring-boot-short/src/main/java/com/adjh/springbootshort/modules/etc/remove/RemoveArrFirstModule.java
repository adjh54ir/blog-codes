package com.adjh.springbootshort.modules.etc.remove;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 배열의 첫 번째 요소를 제거합니다.
 *
 * @author : jonghoon
 * @fileName : RemoveArrFirstModule
 * @link : https://adjh54.tistory.com/137
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class RemoveArrFirstModule {


    /**
     * 배열의 첫 번째 요소를 제거합니다-1 : 첫 번째 인덱스 0의 값으로 변경 : 배열 사이즈를 유지하면서 첫 번째 값 비우기
     *
     * @return
     */
    public static Object[] arrFirstElement1() {
        Object[] rstArr = new Object[0];
        String[] strArr4 = {"apple", "banana", "cherry", "berry"};
        strArr4[0] = null; // [null, "banana", "cherry", "berry"]
        return rstArr;
    }

    /**
     * 배열의 첫 번째 요소를 제거합니다-2 : Arrays.copy() : 배열 재구성
     *
     * @return
     */
    public static Object[] arrFirstElement2() {
        Object[] rstArr = new Object[0];
        String[] strArr2 = {"apple", "banana", "cherry", "berry"};
        String[] copyOfStr = Arrays.copyOf(strArr2, strArr2.length - 1); // ["banana", "cherry", "berry"]
        return rstArr;
    }

    /**
     * 배열의 첫 번째 요소를 제거합니다-3 : Arrays.copyOfRange() : 배열 재구성
     *
     * @return
     */
    public static Object[] arrFirstElement3() {
        Object[] rstArr = new Object[0];
        String[] strArr1 = {"apple", "banana", "cherry", "berry"};
        String[] copyStrArr1 = Arrays.copyOfRange(strArr1, 1, strArr1.length); // ["banana", "cherry", "berry"]
        return rstArr;
    }

    /**
     * 배열의 첫 번째 요소를 제거합니다-4 : System.arraycopy() : 배열 재구성
     *
     * @return
     */
    public static Object[] arrFirstElement4() {
        Object[] rstArr = new Object[0];
        String[] strArr5 = {"apple", "banana", "cherry", "berry"};
        String[] newArray = new String[strArr5.length - 1];
        System.arraycopy(strArr5, 1, newArray, 0, newArray.length); // [banana, cherry, berry]
        return rstArr;
    }

    /**
     * 배열의 첫 번째 요소를 제거합니다-5 : for() : 배열 재구성
     *
     * @return
     */
    public static Object[] arrFirstElement5() {
        Object[] rstArr = new Object[0];
        String[] oldArray2 = {"first", "second", "third"};
        String[] newArray2 = new String[oldArray2.length - 1];
        for (int i = 1; i < oldArray2.length; i++) {
            newArray2[i - 1] = oldArray2[i];
        }
        oldArray2 = newArray2; // ["banana", "cherry", "berry"]
        return rstArr;
    }


}
