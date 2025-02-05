package com.adjh.springbootkeycloaksub.dto;

import lombok.*;

import java.util.List;

/**
 * Keycloak 사용자 정보 조회
 *
 * @author : leejonghoon
 * @fileName : KeycloakUserDto
 * @since : 2025. 2. 5.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeycloakUserDto {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailVerified;
    private long createdTimestamp;
    private boolean enabled;
    private boolean totp;
    private List<String> disableableCredentialTypes;
    private List<String> requiredActions;
    private int notBefore;
    private Access access;

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Access {
        private boolean manageGroupMembership;
        private boolean view;
        private boolean mapRoles;
        private boolean impersonate;
        private boolean manage;
    }

    @Builder
    public KeycloakUserDto(String id, String username, String firstName, String lastName, String email, boolean emailVerified, long createdTimestamp, boolean enabled, boolean totp, List<String> disableableCredentialTypes, List<String> requiredActions, int notBefore, Access access) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailVerified = emailVerified;
        this.createdTimestamp = createdTimestamp;
        this.enabled = enabled;
        this.totp = totp;
        this.disableableCredentialTypes = disableableCredentialTypes;
        this.requiredActions = requiredActions;
        this.notBefore = notBefore;
        this.access = access;
    }
}
