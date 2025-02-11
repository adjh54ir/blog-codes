package com.blog.springbootkeycloak.dto;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakUserSearchDto
 * @since : 2025. 2. 11.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeycloakUserSearchDto {
    private Integer first;
    private Integer max;
    private String search;
    private String username;
    private String email;
    private Boolean enabled;
    private Boolean exact;
}
