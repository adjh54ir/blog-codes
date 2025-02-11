package com.blog.springbootkeycloak.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakUserResetPwDto
 * @since : 2025. 2. 11.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeycloakUserResetPwDto {

    private String type;
    private String value;
    private boolean temporary;
    private String username;

    public KeycloakUserResetPwDto(String type, String value, boolean temporary, String username) {
        this.type = type;
        this.value = value;
        this.temporary = temporary;
        this.username = username;
    }
}
