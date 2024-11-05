package com.adjh.springbootoauth2.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * 카카오 로그인 사용자 정보 DTO
 *
 * @refrence: https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info
 */
@Getter
@ToString
public class KakaoUserInfoDto {
    private Long id;                        // 카카오 사용자의 고유 ID
    private Boolean hasSignedUp;            // 사용자가 앱에 가입했는지 여부
    private String connectedAt;             // 사용자가 앱과 연결된 시간
    private String synchedAt;               // 사용자 정보가 마지막으로 동기화된 시간
    private Map<String, Object> properties; // 사용자 속성을 저장하는 맵
    private KakaoAccount kakaoAccount;      // 카카오 계정 정보
    private Partner forPartner;             // 파트너를 위한 정보

    @Getter
    public static class Properties {
        private String nickname;            // 사용자의 닉네임
        private String profileImage;        // 사용자의 프로필 이미지 URL
        private String thumbnailImage;      // 사용자의 썸네일 이미지 URL
    }

    @Getter
    public static class KakaoAccount {
        private boolean profileNeedsAgreement;          // 프로필 정보 제공 동의 여부
        private boolean profileNicknameNeedsAgreement;  // 닉네임 정보 제공 동의 여부
        private boolean profileImageNeedsAgreement;     // 프로필 이미지 제공 동의 여부
        private Profile profile;                        // 사용자 프로필 정보
        private boolean nameNeedsAgreement;             // 이름 정보 제공 동의 여부
        private String name;                            // 사용자 이름
        private boolean emailNeedsAgreement;            // 이메일 정보 제공 동의 여부
        private boolean isEmailValid;                   // 이메일 유효성 여부
        private boolean isEmailVerified;                // 이메일 인증 여부
        private String email;                           // 사용자 이메일
        private boolean ageRangeNeedsAgreement;         // 연령대 정보 제공 동의 여부
        private String ageRange;                        // 사용자 연령대
        private boolean birthyearNeedsAgreement;        // 출생연도 정보 제공 동의 여부
        private String birthyear;                       // 사용자 출생연도
        private boolean birthdayNeedsAgreement;         // 생일 정보 제공 동의 여부
        private String birthday;                        // 사용자 생일
        private String birthdayType;                    // 생일 타입 (음력/양력)
        private boolean genderNeedsAgreement;           // 성별 정보 제공 동의 여부
        private String gender;                          // 사용자 성별
        private boolean phoneNumberNeedsAgreement;      // 전화번호 정보 제공 동의 여부
        private String phoneNumber;                     // 사용자 전화번호
        private boolean ciNeedsAgreement;               // CI 정보 제공 동의 여부
        private String ci;                              // 연계정보 (CI)
        private String ciAuthenticatedAt;               // CI 발급 시각

        @Getter
        public static class Profile {
            private String nickname; // 사용자 닉네임
            private String thumbnailImageUrl; // 사용자 썸네일 이미지 URL
            private String profileImageUrl; // 사용자 프로필 이미지 URL
            private boolean isDefaultImage; // 기본 이미지 사용 여부
            private boolean isDefaultNickname; // 기본 닉네임 사용 여부
        }
    }

    public static class Partner {
        private String uuid; // 파트너용 고유 식별자
    }
}