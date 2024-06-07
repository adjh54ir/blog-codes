package com.adjh.springbootshort.modules.calculate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * SecureRandom을 이용한 랜덤 문자열 생성 방법
 *
 * @author : jonghoon
 * @fileName : CalcSecureRandomModule
 * @link : https://adjh54.tistory.com/426
 * @since : 6/8/24
 */
@Component
@RequiredArgsConstructor
public class CalcSecureRandomModule {
    /**
     * 자릿수(digit) 만큼 랜덤한 숫자를 반환 받습니다.
     *
     * @param length 자릿수
     * @return 랜덤한 숫자
     */
    public static int generateRandomNum(int length) {
        SecureRandom secureRandom = new SecureRandom();
        int upperLimit = (int) Math.pow(10, length);
        return secureRandom.nextInt(upperLimit);
    }

    /**
     * 시작 범위(start)와 종료 범위(end) 값을 받아서 랜덤한 숫자를 반환 받습니다.
     *
     * @param start 시작 범위
     * @param end   종료 범위
     * @return 랜덤한 숫자
     */
    public static int generateRangeRandomNum(int start, int end) {
        SecureRandom secureRandom = new SecureRandom();
        return start + secureRandom.nextInt(end + 1);
    }

    /**
     * 자릿수(length) 만큼 랜덤한 문자열을 대문자/소문자에 따라 반환 받습니다.
     *
     * @param length      자릿수
     * @param isUpperCase 대문자 여부
     * @return 랜덤한 문자열
     */
    public static String generateRandomStr(int length, boolean isUpperCase) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(secureRandom.nextInt(alphabet.length())));
        }
        return isUpperCase ? sb.toString().toUpperCase() : sb.toString().toLowerCase();
    }

    /**
     * 자릿수(length) 만큼 랜덤한 숫자 + 문자 조합을 대문자/소문자에 따라 반환 받습니다.
     *
     * @param length      자릿수
     * @param isUpperCase 대문자 여부
     * @return 랜덤한 숫자 + 문자 조합의 문자열
     */
    public static String generateRandomMixStr(int length, boolean isUpperCase) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return isUpperCase ? sb.toString() : sb.toString().toLowerCase();
    }

    /**
     * 자릿수(length) 만큼 랜덤한 문자와 특수문자 조합의 랜덤한 문자열을 출력합니다.
     *
     * @param length 문자의 범위
     * @return String 문자 + 특수 문자 조합 문자열
     */
    public static String generateRandomMixCharNSpecialChar(int length) {
        SecureRandom secureRandom = new SecureRandom();

        /*
         * 1. 특수문자의 범위: 33 ~ 47, 58 ~ 64, 91 ~ 96
         * 2. 대문자의 범위: 65 ~ 90
         * 3. 소문자의 범위: 97 ~ 122
         * -- 제외 대상 범위(숫자) : 48 ~ 57
         */
        String charNSpecialChar = IntStream.concat(
                        IntStream.rangeClosed(33, 47),
                        IntStream.rangeClosed(58, 126))
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(charNSpecialChar.charAt(secureRandom.nextInt(charNSpecialChar.length())));
        }
        return builder.toString();
    }

    /**
     * 자릿수(length) 만큼 랜덤한 숫자와 특수문자 조합의 랜덤한 문자열을 출력합니다.
     *
     * @param length 문자의 범위
     * @return String 숫자 + 특수 문자 조합 문자열
     */
    public static String generateRandomMixNumNSpecialChar(int length) {
        SecureRandom secureRandom = new SecureRandom();

        /*
         * 1. 특수문자의 범위 33 ~ 47, 58 ~ 64, 91 ~ 96
         * 2. 숫자의 범위 : 48 ~ 57
         * -- 대문자의 범위(제외): 65 ~ 90
         * -- 소문자의 범위(제외): 97 ~ 122
         */
        String charNSpecialChar = IntStream.concat(
                        IntStream.rangeClosed(33, 64),
                        IntStream.concat(
                                IntStream.rangeClosed(91, 96),
                                IntStream.rangeClosed(123, 126)))
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(charNSpecialChar.charAt(secureRandom.nextInt(charNSpecialChar.length())));
        }
        return builder.toString();
    }

    /**
     * 자릿수(length) 만큼 랜덤한 숫자 + 문자 + 특수문자 조합의 랜덤한 문자열을 출력합니다.
     *
     * @param length 문자의 범위
     * @return String 숫자 + 문자 + 특수 문자 조합 문자열
     */
    public static String generateRandomMixAll(int length) {
        SecureRandom secureRandom = new SecureRandom();
        /*
         * 1. 특수문자의 범위 33 ~ 47, 58 ~ 64, 91 ~ 96
         * 2. 숫자의 범위 : 48 ~ 57
         * 3. 대문자의 범위: 65 ~ 90
         * 4. 소문자의 범위: 97 ~ 122
         */
        String charNSpecialChar =
                IntStream.rangeClosed(33, 126)
                        .mapToObj(i -> String.valueOf((char) i))
                        .collect(Collectors.joining());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(charNSpecialChar.charAt(secureRandom.nextInt(charNSpecialChar.length())));
        }
        return builder.toString();
    }


}
