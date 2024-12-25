package com.adjh.springbootshort.modules.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 문자열의 왼쪽에 값을 채우거나 혹은 오른쪽에 값을 채우는 모듈
 *
 * @author : jonghoon
 * @fileName : CvtLRPadModule
 * @link : https://adjh54.tistory.com/134
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class CvtLRPadModule {

    /**
     * 왼쪽, 오른쪽의 문자열을 채워 넣습니다.
     *
     * @param paramStr
     * @return
     */
    public static String CvtLRPadStr(String paramStr) {
        String rstStr = "";
        /*
         * "왼쪽"에서부터 "공백" 문자열을 채워넣습니다.
         * 1. 해당 예시는 왼쪽에서부터 지정한 문자열을 포함하여 총 5개의 문자열 개수가 만들어지는데 지정한 문자열을 제외한 공백으로 이를 채웁니다.
         * 2. 해당 예시에는 공백을 다시 "z"라는 문자열로 바꿉니다.
         */
        String lPadStr = "abc";
        lPadStr = String.format("%5s", lPadStr).replace(" ", "z");  // zzabc

        /*
         * "오른쪽"에서부터 "공백" 문자열을 채워넣습니다.
         * 1. 해당 예시는 오른쪽에서부터 지정한 문자열을 포함하여 총 5개의 문자열 개수가 만들어지는데 지정한 문자열을 제외한 공백으로 이를 채웁니다.
         * 2. 해당 예시에서는 공백을 다시 "z"라는 문자열로 바꿉니다.
         */
        String rPadStr = "abc";
        rPadStr = String.format("%-5s", rPadStr).replace(" ", "z"); // abczz
        return rstStr;
    }

    /**
     * 왼쪽, 오른쪽에 숫자를 채워 넣습니다,
     *
     * @param paramStr
     * @return
     */
    public static String CvtRPadStr(String paramStr) {
        String rstStr = "";
        /*
         * 왼쪽에서부터 0의 값을 채워넣습니다.
         * 1. 해당 예시는 왼쪽에서부터 지정한 문자열을 포함하여 총 5개의 문자열 개수가 만들어지는데 지정한 문자열을 제외한 0으로 이를 채웁니다.
         * 2. 해당 예시에는 00123 값이 출력이 됩니다.
         */
        String lPadIntStr = "123";
        lPadIntStr = String.format("%05d", Integer.parseInt(lPadIntStr));                   // 00123
        int LPadInt = Integer.parseInt(lPadIntStr);                                         // 123

        /*
         * 오른쪽에서부터 공백 문자열을 채워넣습니다. (* "%-05d"와 같이 숫자에서 뒤에서부터 채우는것은 존재하지 않습니다.)
         * 1. 해당 예시는 오른쪽에서부터 지정한 문자열을 포함하여 총 5개의 문자열 개수가 만들어지는데 지정한 문자열을 제외한 공백으로 이를 채웁니다.
         * 2. 해당 예시에서는 공백을 다시 "0"라는 문자열로 바꿉니다.
         */
        String rPadIntStr = "123";
        rPadIntStr = String.format("%-5s", rPadIntStr).replace(" ", "0");       // 12300
        int RPadInt = Integer.parseInt(rPadIntStr);                             // 12300

        return rstStr;
    }

}
