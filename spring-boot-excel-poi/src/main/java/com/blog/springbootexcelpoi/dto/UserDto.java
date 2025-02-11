package com.blog.springbootexcelpoi.dto;

import lombok.*;

/**
 * 엑셀 데이터 파싱 정보
 *
 * @author : leejonghoon
 * @fileName : UserDto
 * @since : 2025. 2. 11.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private int number;      // 순번
    private String name;     // 이름
    private int age;         // 나이
    private String gender;   // 성별
    private String contact;  // 연락처

    @Builder
    public UserDto(int number, String name, int age, String gender, String contact) {
        this.number = number;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }
}
