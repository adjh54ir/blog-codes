package com.adjh.springboot3tierform.utils;

import lombok.NoArgsConstructor;

/**
 * 데이터를 마스킹 처리하는 Utils
 * - 이름 마스킹 처리 함수 : maskName()
 * - 휴대폰 번호 마스킹 처리 함수 : maskPhoneNumber()
 * - 이메일 주소 마스킹 처리 함수 : maskEmail()
 * - 계좌번호 마스킹 처리 함수 : maskAccountNumber()
 *
 * @author : jonghoon
 * @fileName : MaskingUtils
 * @since : 7/26/24
 */
@NoArgsConstructor
public class MaskingUtils {

    /**
     * 이름에 대한 마스킹을 수행합니다.
     *
     * @param name {String} : 마스킹 이전 이름
     * @return {String} 마스킹 이후 이름
     */
    public static String maskName(String name) {

        // 기본 형식에 맞지 않는 이름의 경우, 파라미터 값을 그대로 반환합니다.
        if (name == null || name.length() < 2) {
            return name;
        }

        int length = name.length();
        StringBuilder maskedName = new StringBuilder(name);

        // [CASE1] 이름이 2자리 인 경우 => 첫 번째 문자에 대해 마스킹
        if (length == 2) {
            maskedName.setCharAt(0, '*');
        }
        // [CASE2] 이름이 3자리 인 경우 => 두 번째 문자에 대해 마스킹
        else if (length == 3) {
            maskedName.setCharAt(1, '*');
        }
        // [CASE3] 이름이 4자리 인 경우 => 두 번째, 세 번째 문자에 대해 마스킹
        else if (length == 4) {
            maskedName.setCharAt(1, '*');
            maskedName.setCharAt(2, '*');
        }
        // [CASE4] 이름이 5자리 이상인 경우 => 첫 번째와 마지막 문자를 제외한 모든 문자에 대해 마스킹
        else if (length == 5) {
            maskedName.setCharAt(1, '*');
            maskedName.setCharAt(2, '*');
            maskedName.setCharAt(3, '*');
        }
        // [CASE5] 이름이 5자리 이상인 경우 => 첫 번째와 마지막 문자를 제외한 모든 문자에 대해 마스킹
        else {
            for (int i = 1; i < length - 1; i++) {
                maskedName.setCharAt(i, '*');
            }
        }

        return maskedName.toString();
    }

    /**
     * 휴대폰 번호에 대한 마스킹을 수행합니다.
     *
     * @param phoneNumber {String}  마스킹 이전 휴대폰 번호
     * @return {String} 마스킹 된 휴대폰 번호
     */
    public static String maskPhoneNumber(String phoneNumber) {

        // 기본 형식에 맞지 않는 핸드폰 번호의 경우, 파라미터 값을 그대로 반환합니다.
        if (phoneNumber == null || phoneNumber.length() < 10) {
            return phoneNumber;
        }

        StringBuilder maskedPhoneNumber = new StringBuilder(phoneNumber);
        int length = phoneNumber.length();

        // [CASE1] 핸드폰 번호의 하이픈이 포함된 경우
        if (phoneNumber.contains("-")) {
            String[] parts = phoneNumber.split("-");
            if (parts.length == 3) {

                // [CASE2-1] 가운데 번호가 3자리인 경우
                if (parts[1].length() == 3) {
                    maskedPhoneNumber.replace(4, 7, "***");     // 가운데 3자리를 마스킹
                }
                // [CASE2-2] 가운데 번호가 4자리인 경우
                else if (parts[1].length() == 4) {
                    maskedPhoneNumber.replace(4, 8, "****");    // 가운데 4자리를 마스킹
                }
            }

        }
        // [CASE2] 핸드폰 번호의 하이픈이 포함되지 않은 경우
        else {
            // [CASE2-1] 가운데 번호가 3자리인 경우
            if (length == 10) {
                maskedPhoneNumber.replace(3, 6, "***");     // 가운데 3자리를 마스킹
            }
            // [CASE2-2] 가운데 번호가 4자리인 경우
            else if (length == 11) {
                maskedPhoneNumber.replace(3, 7, "****");    // 가운데 4자리를 마스킹
            }
        }
        return maskedPhoneNumber.toString();
    }

    /**
     * 이메일 주소에 대한 마스킹을 수행합니다.
     *
     * @param email {String} : 마스킹 이전 이메일 주소
     * @return {String} 마스킹 된 이메일 주소
     */
    public static String maskEmail(String email) {

        // 기본 형식에 맞지 않는 이메일 주소의 경우, 파라미터 값을 그대로 반환합니다.
        if (email == null || !email.contains("@")) {
            return email;
        }

        String[] parts = email.split("@");
        String idPart = parts[0];           // 아이디
        String domainPart = parts[1];       // 이메일 도메인

        // 기본 형식에 맞지 않는 이메일 주소의 경우, 파라미터 값을 그대로 반환합니다.
        if (idPart.length() <= 3) {
            return email;
        }

        StringBuilder maskedEmail = new StringBuilder();
        maskedEmail.append(idPart.substring(0, 3));
        for (int i = 3; i < idPart.length(); i++) {
            maskedEmail.append('*');
        }
        maskedEmail.append('@').append(domainPart);

        return maskedEmail.toString();
    }


    /**
     * 계좌번호에 대한 마스킹을 수행합니다.
     *
     * @param accountNumber {String} : 마스킹 이전 계좌번호
     * @return {String} 마스킹 된 계좌번호
     */
    public static String maskAccountNumber(String accountNumber) {

        // 기본 형식에 맞지 않는 계좌번호의 경우, 파라미터 값을 그대로 반환합니다.
        if (accountNumber == null || accountNumber.length() < 7) {
            return accountNumber;
        }

        int length = accountNumber.length();
        StringBuilder maskedAccount = new StringBuilder(accountNumber);

        // 계좌번호의 앞 3자리와 마지막 3자리를 제외한 부분을 마스킹
        for (int i = 3; i < length - 3; i++) {
            maskedAccount.setCharAt(i, '*');
        }

        return maskedAccount.toString();
    }

    /**
     * 주소에 대한 마스킹을 수행합니다.
     *
     * @param address {String} : 마스킹 이전 주소
     * @return {String} 마스킹 된 주소
     */
    public static String maskAddress(String address) {

        // 기본 형식에 맞지 않는 주소의 경우, 파라미터 값을 그대로 반환합니다.
        if (address == null || address.length() < 10) {
            return address;
        }

        // 주소를 공백 기준으로 나눔
        String[] parts = address.split(" ");

        // 상세 주소를 마스킹
        StringBuilder maskedAddress = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            maskedAddress.append(parts[i]).append(" ");
        }
        maskedAddress.append("***");

        return maskedAddress.toString();
    }
}
