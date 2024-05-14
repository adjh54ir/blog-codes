package com.adjh.springbootquerydsl.entity.enums;

import lombok.Getter;

/**
 * 사용자의 상태를 관리합니다.
 *
 * @author : lee
 * @fileName : UserStEnum
 * @since : 4/8/24
 */
@Getter
public enum UserStEnum {
    A("ACTIVE", "활성화"),        // 활성화
    D("DELETE", "삭제"),        // 삭제
    W("WITHDRAWAL", "회원탈퇴");    // 회원탈퇴

    private final String value;
    private final String desc;

    UserStEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
