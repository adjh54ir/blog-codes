package com.adjh.springbootcommon.commons.utils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 문자열을 이용하는 공통유틸입니다.
 *
 * @author : lee
 * @fileName : StringUtils
 * @Method @since : 2/16/24
 * - makeUUID()                             : UUID 생성 함수
 * - generateRandomNum()                    : 자릿수(digit) 만큼 랜덤한 숫자를 반환 받습니다.
 * - generateRangeRandomNum()               : 자릿수(digit) 만큼 랜덤한 숫자를 반환 받습니다.
 * - generateRandomStr()                    : 시작 범위(start)와 종료 범위(end) 값을 받아서 랜덤한 숫자를 반환 받습니다.
 * - generateRandomMixStr()                 : 자릿수(length) 만큼 랜덤한 숫자 + 문자 조합을 대문자/소문자에 따라 반환 받습니다.
 * - generateRandomMixCharNSpecialChar()    : 자릿수(length) 만큼 랜덤한 문자와 특수문자 조합의 랜덤한 문자열을 출력합니다.
 * - generateRandomMixNumNSpecialChar()     : 자릿수(length) 만큼 랜덤한 숫자와 특수문자 조합의 랜덤한 문자열을 출력합니다.
 * - generateRandomMixAll()                 : 자릿수(length) 만큼 랜덤한 숫자 + 문자 + 특수문자 조합의 랜덤한 문자열을 출력합니다.
 * - generateRandomTempPasswordType1()      : 자릿수(length) 만큼 랜덤한 숫자 소문자 조합의 랜덤한 문자열을 출력합니다.
 * - generateRandomTempPasswordType2()      : 자릿수(length) 만큼 랜덤한 숫자 소문자 조합의 랜덤한 문자열을 출력합니다.
 */
@RequiredArgsConstructor
public class StringUtils {


    /**
     * 문자열의 NULL, 빈값을 체크합니다.
     * true: NULL 혹은 빈 값
     * false: 값이 존재함
     *
     * @param param
     * @return
     */
    public static boolean isStrEmpty(String param) {
        if (param == null) {
            return true;
        }
        if (param.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * [공통함수] UUID 생성 함수
     *
     * @param isHyphen {boolean} 하이픈 사용 여부
     * @return {string} UUID 값 반환
     */
    public static String makeUUID(boolean isHyphen) {
        String result = "";
        if (isHyphen) {
            result = UUID.randomUUID().toString();
        } else {
            result = UUID.randomUUID().toString().replace("-", "");
        }
        return result;
    }

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


    /**
     * 자릿수(length) 만큼 랜덤한 숫자 소문자 조합의 랜덤한 문자열을 출력합니다.
     *
     * @param length 문자의 범위
     * @return String 숫자 + 소문자 조합의 랜덤한 문자열을 출력합니다.
     */
    public static String generateRandomTempPasswordType1(int length) {
        SecureRandom secureRandom = new SecureRandom();
        /*
         * 1. 소문자의 범위: 97 ~ 122
         * 2. 숫자의 범위 : 48 ~ 57
         * -- 대문자의 범위(제외): 65 ~ 90
         * -- 특수문자의 범위(제외): 33 ~ 47, 58 ~ 64, 91 ~ 96
         */
        String tempPassword =
                IntStream.concat(
                                IntStream.rangeClosed(48, 57),
                                IntStream.rangeClosed(97, 122))
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
    public static String generateRandomTempPasswordType2(int length) {
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
