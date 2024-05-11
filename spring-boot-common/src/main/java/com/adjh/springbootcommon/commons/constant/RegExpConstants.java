package com.adjh.springbootcommon.commons.constant;

/**
 * 정규식 모음을 관리하는 상수 페이지 입니다.
 *
 * @author : jonghoon
 * @fileName : RegExpConstants
 * @since : 2023/01/16
 */
public class RegExpConstants {

    /**
     * [정규식 모음-1] 패턴을 이용한 정규식
     */
    // 1. 문자열만 허용하는 정규식 - 공백 미 허용
    public static final String REGEXP_PATTERN_CHAR = "^[\\w]*$";

    // 2. 문자열만 허용하는 정규식 - 공백 허용
    public static final String REGEXP_PATTERN_NO_CHAR = "^[\\W]*$";

    // 3. 숫자만 허용하는 정규식
    public static final String REGEXP_PATTERN_NUMBER = "^[\\d]*$";

    // 4. 숫자가 아닌 경우 허용하는 정규식
    public static final String REGEXP_PATTERN_NO_NUMBER = "^[\\D]*$";

    // 5. 공백, 탭을 허용하는 정규식
    public static final String REGEXP_PATTERN_SPACE_CHAR = "^[\\s]*$";

    // 6. 공백, 탭이 아닌 경우를 허용하는 정규식
    public static final String REGEXP_PATTERN_SPACE_NO_CHAR = "^[\\S]*$";


    /**
     * [정규식 모음-2] '문자열'에 대한 정규식
     */
    // 1. 대소구분 없는 영문만 허용하는 정규식
    public static final String REGEXP_EN = "^[a-z|A-Z]*$";

    // 2. 소문자만 허용하는 정규식
    public static final String REGEXP_LOWER_CASE = "^[a-z]*$";

    // 3. 대문자만 허용하는 정규식
    public static final String REGEXP_UPPER_CASE = "^[A-Z]*$";

    // 4. 한글만 허용하는 정규식
    public static final String REGEXP_KR = "^[ㄱ-ㅎ|가-힣]*$";

    // 5. 대소구분 없는 영문과 한글만 허용하는 장규식
    public static final String REGEXP_ONLY_CHAR = "^[a-z|A-Z|ㄱ-ㅎ|가-힣]*$";
    public static final String REGEXP_ONLY_SPACE_CHAR = "^[a-z|A-Z|ㄱ-ㅎ|가-힣| ]*$";

    // 6. 대소구분 없는 영문과 한글, 숫자를 허용하는 정규식
    public static final String REGEXP_CHAR_NUM = "^[0-9a-zA-Zㄱ-ㅎ가-힣]*$";
    public static final String REGEXP_CHAR_SPACE_NUM = "^[0-9a-zA-Zㄱ-ㅎ가-힣 ]*$";


    // 7. 특수문자 제외에 대한 정규식
    public static final String REGEXP_NOT_SPECIAL_CHAR_TYPE1 = "^[^A-Za-z0-9]*$";
    public static final String REGEXP_NOT_SPECIAL_CHAR_TYPE2 = "[\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-_+<>@\\#$%&\\=\\('\"]*$";

    /**
     * [정규식 모음-3] 숫자에 대한 정규식
     */
    // 숫자만 이용하는 정규식
    public static final String REGEXP_NUMBER = "^[0-9]*$";

    // 숫자가 아닌것에 대한 정규식
    public static final String REGEXP_NO_NUMBER = "^[^0-9]*$";

    /**
     * [정규식 모음-4] 사용자 정보 관련 정규식
     */
    // 1. 사용자 이메일에 대한 정규식
    public static final String REGEXP_LIGHT_USER_EMAIL = "^[a-zA-Z0-9]+@[0-9a-zA-Z]+\\.[a-z]+$";  // 언더바(_), 하이픈(-) 제외
    public static final String REGEXP_TIGHT_USER_EMAIL = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}+$"; // 언더바(_), 하이픈(-) 포함 및 길이 지정

    // 2. 사용자 아이디에 대한 정규식 - 영문 숫자 조합 6~10자리
    public static final String REGEXP_USER_ID = "^[a-z]{1}[a-z0-9]{5,10}+$";

    // 3. 사용자 패스워드에 대한 정규식 - 대소문자 + 숫자 + 특수문자 조합으로 10 ~ 128자리로 정의 한다.
    public static final String REGEXP_USER_PW_TYPE1 = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{10,128}+$";

    // 4. 핸드폰 번호 타입1에 대한 정규식 => ex) 01023205454
    public static final String REGEXP_PHONE_TYPE1 = "^[\\d]{11}+$";

    // 5. 핸드폰 번호 타입2에 대한 정규식 => ex) 010-2320-5454
    public static final String REGEXP_PHONE_TYPE2_1 = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})+$";
    public static final String REGEXP_PHONE_TYPE2_2 = "^[\\d]{2,3}-[\\d]{3,4}-[\\d]{4}+$";

    // 6. 핸드폰 번호 타입3에 대한 정규식 => ex) +82-010-2320-5454
    public static final String REGEXP_PHONE_TYPE3 = "^\\+82-01([0|1|6|7|8|9])-([\\d]{3,4})-([\\d]{4})+$";

    // 7. 핸드폰 번호 타입4에 대한 정규식 => ex) +82-10-2320-5454
    public static final String REGEXP_PHONE_TYPE4 = "^\\+82-10-([\\d]{3,4})-([\\d]{4})+$";

    // 8. 주민등록 번호 타입에 대한 정규식
    public static final String REGEXP_REGISTRATION_NUM = "^[\\d]{6}-[1-4][\\d]{6}+$";

    // 9. 우편 번호에 대한 정규식
    public static final String REGEXP_POSTAL_CODE = "^[\\d]{3}-[\\d]{2}+$";

    // 10. IPV4에 대한 정규식
    public static final String REGEXP_IPV4_ADDR = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";

    // 11. IPV6에 대한 정규식
    public static final String REGEXP_IPV6_ADDR = "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4}|([0-9A-Fa-f]{1,4}:){1,7}:|([0-9A-Fa-f]{1,4}:){1,6}:[0-9A-Fa-f]{1,4}|([0-9A-Fa-f]{1,4}:){1,5}(:[0-9A-Fa-f]{1,4}){1,2}|([0-9A-Fa-f]{1,4}:){1,4}(:[0-9A-Fa-f]{1,4}){1,3}|([0-9A-Fa-f]{1,4}:){1,3}(:[0-9A-Fa-f]{1,4}){1,4}|([0-9A-Fa-f]{1,4}:){1,2}(:[0-9A-Fa-f]{1,4}){1,5}|[0-9A-Fa-f]{1,4}:((:[0-9A-Fa-f]{1,4}){1,6})|:((:[0-9A-Fa-f]{1,4}){1,7}|:)|fe80:(:[0-9A-Fa-f]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9A-Fa-f]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])))$";

    // 12. IPV4, IPV6에 대한 정규식
    public static final String REGEXP_IPV4_IPV6_ADDR = "(" + REGEXP_IPV4_ADDR + "|" + REGEXP_IPV6_ADDR + ")";


    /**
     * [정규식 모음-5]
     * 날짜에 대한 정규식
     */
    // [Soft] 날짜에 대한 타입1에 대한 정규식 => ex) 2022-08-03 (YYYY-MM-DD)
    public static final String REGEXP_DATE_TYPE1 = "^[\\d]{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    // [Soft] 날짜에 대한 타입2에 대한 정규식 => ex) 2022/08/03 (YYYY/MM/DD)
    public static final String REGEXP_DATE_TYPE2 = "^[\\d]{4}]\\/(0[1-9]|1[012])\\/(0[1-9]|[12][0-9]|3[01])$";

    // [Soft] 날짜에 대한 타입에 대한 정규식 => ex) 2022.08.03 (YYYY.MM.DD)
    public static final String REGEXP_DATE_TYPE3 = "^[\\d]{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])$";

    // [Hard] 날짜에 대한 정규식 => 19xx 20xx년도에 대해서 추가함 -- 강력하게 유효성 검증
    public static final String REGEXP_DATE_TYPE4 = "^(19|20)[\\d]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    // 날짜에 대한 정규식 - YYYY/MM/DD hh:mi:ss
    public static final String REGEXP_DATE_TYPE5 =
            "([0-2][0-9]{3})\\/([0-1][0-9])\\/([0-3][0-9]) ([0-5][0-9]):([0-5][0-9]):([0-5][0-9])(([\\-\\+]([0-1][0-9])\\:00))?";

    /**
     * [정규식 모음-6] 시간에 대한 정규식
     */
    // 시간에 대한 타입1에 대한 정규식 => ex) 13:47(HH24:mm)
    public static final String REGEXP_TIME_TYPE1 = "^([1-9]|[01][0-9]|2[0-3]):([0-5][0-9])$";

}
