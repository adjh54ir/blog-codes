package com.adjh.springbootcommon.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 임시 패스워드를 만들기 위한 모듈
 *
 * @author : jonghoon
 * @fileName : PasswordModules
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class PasswordModules {

    /**
     * 자릿수(length) 만큼 랜덤한 숫자 소문자 조합의 랜덤한 문자열을 출력합니다.
     *
     * @param length 문자의 범위
     * @return String 숫자 + 소문자 조합의 랜덤한 문자열을 출력합니다.
     */
    public static String generateRandomPw1(int length) {
        SecureRandom secureRandom = new SecureRandom();
        /*
         * 1. 소문자의 범위: 97 ~ 122
         * 2. 숫자의 범위 : 48 ~ 57
         * -- 대문자의 범위(제외): 65 ~ 90
         * -- 특수문자의 범위(제외): 33 ~ 47, 58 ~ 64, 91 ~ 96
         */
        String tempPassword =
                IntStream.rangeClosed(33, 126)
                        .mapToObj(i -> String.valueOf((char) i))
                        .collect(Collectors.joining());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(tempPassword.charAt(secureRandom.nextInt(tempPassword.length())));
        }
        return builder.toString();
    }

    /**
     * 자릿수(length) 만큼 랜덤한 숫자 소문자 조합의 랜덤한 문자열을 출력합니다.
     *
     * @param length 문자의 범위
     * @return String 숫자 + 소문자 조합의 랜덤한 문자열을 출력합니다.
     */
    public static String generateRandomPw2(int length) {
        SecureRandom secureRandom = new SecureRandom();
        /*
         * 1. 소문자의 범위 : 97 ~ 122
         * 2. 대문자의 범위 : 65 ~ 90
         * 3. 일부 허용 특수문자 : !@#$%^&_=+
         */
        String tempPasswordStr = IntStream.concat(
                        IntStream.concat(
                                IntStream.rangeClosed(65, 90),
                                IntStream.rangeClosed(97, 122)),
                        "!@#$%^&_=+".chars())
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(tempPasswordStr.charAt(secureRandom.nextInt(tempPasswordStr.length())));
        }
        return builder.toString();
    }
}
