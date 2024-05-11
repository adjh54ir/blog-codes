package com.adjh.springbootcommon.commons.utils;

import java.util.Base64;

/**
 * 암호화/복호화에 사용되는 공통 유틸
 *
 * @author : lee
 * @fileName : EncryptUtils
 * @since : 2/16/24
 */
public class EncryptUtils {
    /**
     * [공통함수] 문자열을 BASE64형으로 인코딩
     *
     * @param stringData 문자열
     * @return String
     */
    public static String encodeStringToBase64(String stringData) {
        return Base64.getEncoder().encodeToString(stringData.getBytes());
    }

    /**
     * [공통함수] BASE64형을 문자열로 디코딩
     *
     * @param base64Str 문자열
     * @return String
     */
    public static String decodeBase64ToString(String base64Str) {
        // String => Bytes => String 변환 작업
        byte[] decodedBytes = Base64.getDecoder().decode(base64Str);
        return new String(decodedBytes);
    }
}
