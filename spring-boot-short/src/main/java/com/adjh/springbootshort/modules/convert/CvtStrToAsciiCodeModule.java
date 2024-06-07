package com.adjh.springbootshort.modules.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 문자열 ASCII 코드로 변환하는 모듈
 *
 * @author : jonghoon
 * @fileName : CvtStrToAsciiCodeModule
 * @link https://adjh54.tistory.com/117
 * @since : 6/6/24
 */
@Component
@RequiredArgsConstructor
public class CvtStrToAsciiCodeModule {

    /**
     * [Short] 문자열(String)을 ASCII Code로 변환 방법 -1
     * - charAt() 함수를 이용하여 변환하는 방법
     *
     * @param str
     * @return
     */
    public static int[] cvtStrToAsciiCode1(String str) {

        String[] helloStrArr = str.split("");
        int[] resultIntArr = new int[helloStrArr.length];

        for (int i = 0; i < helloStrArr.length; i++) {
            int helloItemNum = helloStrArr[i].charAt(0);
            resultIntArr[i] = helloItemNum;
        }

        System.out.println("resultArr :: " + Arrays.toString(resultIntArr));

        return resultIntArr;
    }

    /**
     * [Short] 문자열(String)을 ASCII Code로 변환 방법 -2
     * - str.getBytes(StandardCharsets.US_ASCII)를 이용하는 방법
     *
     * @param str
     * @return
     */
    public static int[] cvtStrToAsciiCode2(String str) {

        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        int[] resultIntArr = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            resultIntArr[i] = Byte.toUnsignedInt(bytes[i]);
        }
        System.out.println("resultArr :: " + Arrays.toString(resultIntArr));
        return resultIntArr;
    }

}
