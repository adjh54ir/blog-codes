package com.adjh.springbootshort.modules.etc.remove;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 마지막 요소를 제거하는데 사용이 됩니다.
 *
 * @author : jonghoon
 * @fileName : RemoveArrLastModule
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class RemoveArrLastModule {

    /**
     * 배열의 마지막 요소를 제거합니다-1 : 마지막 인덱스의 값으로 변경 : 배열 사이즈를 유지하면서 마지막 값 비우기
     * - 첫번째 값을 비우기 위해서 첫 번째 인덱스 값을 가지고 값을 null로 변경하여 사이즈를 유지하면서 첫 번째 값을 비웁니다.
     *
     * @return
     */
    public static Object[] arrLastElement1() {
        Object[] objArr = new Object[0];
        String[] strArr4 = {"apple", "banana", "cherry", "berry"};
        strArr4[strArr4.length] = null; // ["apple", "banana", "cherry", null]
        return objArr;
    }

    /**
     * 배열의 마지막 요소를 제거합니다-2: Arrays.copyOfRange() : 배열 재구성
     * - Arrays.copyOfRange()를 통해서 새로운 배열을 만들어서 마지막 값을 비웁니다. 해당 메서드는 복사할 원본 배열과, 복사를 시작할 인덱스와 끝 인덱스를 지정하여, 새로운 배열을 만들어주는 메서드입니다.
     *
     * @return
     */
    public static Object[] arrLastElement2() {
        Object[] objArr = new Object[0];
        // 마지막 값 제거
        String[] strArr3 = {"apple", "banana", "cherry", "berry"};
        String[] copyOfRangeStrArr3 = Arrays.copyOfRange(strArr3, 0, strArr3.length - 1); // ["apple", "banana", "cherry"]
        return objArr;
    }

    /**
     * 배열의 마지막 요소를 제거합니다-3: System.arraycopy() : 배열 재구성
     * - System.arraycopy()를 통해서 새로운 배열을 만들어서 마지막 값을 비웁니다. array 배열에서 인덱스 0부터 시작하여 newArray.length-1 만큼 newArray 배열에 복사합니다.
     *
     * @return
     */
    public static Object[] arrLastElement3() {
        Object[] objArr = new Object[0];
        String[] strArr5 = {"apple", "banana", "cherry", "berry"};
        String[] newArray = new String[strArr5.length - 1];
        System.arraycopy(strArr5, 0, newArray, 0, newArray.length - 1); // ["apple", "banana", "cherry"]
        return objArr;

    }
}
