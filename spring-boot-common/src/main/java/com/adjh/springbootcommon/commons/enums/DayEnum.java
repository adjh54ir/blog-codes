package com.adjh.springbootcommon.commons.enums;

public enum DayEnum {

    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일"),
    SATURDAY("토요일"),
    SUNDAY("일요일");

    private final String korean;

    DayEnum(String korean) {
        this.korean = korean;
    }

    public String getKoreanTranslation() {
        return this.korean;
    }
}
