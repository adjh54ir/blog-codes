package com.adjh.springbootcommon.commons.utils;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : ObjectUtils
 * @since : 2025. 2. 4.
 * - isStrEmpty()                           : 객체의 빈값 여부를 체크합니다.
 */
@RequiredArgsConstructor
public class ObjectUtils {

    /**
     * Object NULL, 빈 값을 체크하는 유효성 검증 함수
     * true: 빈 값
     * false : 값 존재
     *
     * @param obj
     * @return
     */
    public static boolean isObjEmpty(Object obj) {
        boolean isEmpty = false;
        if (Objects.isNull(obj)) {
            return true;
        }
        return isEmpty;
    }


}
