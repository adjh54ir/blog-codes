package com.adjh.springboot3security.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ValidTokenDto
 * @since : 10/12/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidTokenDto {

    private boolean isValid = false;
    private String errorName = "";

    @Builder(toBuilder = true)
    public ValidTokenDto(boolean isValid, String errorName) {
        this.isValid = isValid;
        this.errorName = errorName;
    }
}
