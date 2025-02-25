package com.adjh.springbootcommon.commons.utils;

import lombok.RequiredArgsConstructor;

import java.util.Base64;

/**
 * 암호화/복호화에 사용되는 공통 유틸
 *
 * @author : lee
 * @fileName : EncryptUtils
 * @Method @since : 2/16/24
 * - encodeStringToBase64()     : 문자열을 BASE64형으로 인코딩
 * - decodeBase64ToString()     : BASE64형을 문자열로 디코딩
 */
@RequiredArgsConstructor
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

    /**
     * [공통함수] ASE256 암호화
     *
     * @param encodeValue
     * @param saltKey
     * @return
     */
    public static String encodeASE256(String encodeValue, String saltKey) {
        return ASE256Modules.encrypt_AES(encodeValue, saltKey);
    }

    /**
     * [공통함수] ASE256 복호화
     *
     * @param encodeValue
     * @param saltKey
     * @return
     */
    public static String decodeASE256(String encodeValue, String saltKey) {
        return ASE256Modules.decrypt_AES(encodeValue, saltKey);
    }


}
