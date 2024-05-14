package model;

import lombok.*;

/**
 * FCM 전송을 위한 디바이스 정보 조회 DTO
 *
 * @author : lee
 * @fileName : FcmSendDeviceDto
 * @since : 2/26/24
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmSendDeviceDto {

    private int userSq;

    private String deviceToken;

    private String planNm;

    private String dvcTkn;

    @Builder
    public FcmSendDeviceDto(int userSq, String deviceToken, String planNm, String dvcTkn) {
        this.userSq = userSq;
        this.deviceToken = deviceToken;
        this.planNm = planNm;
        this.dvcTkn = dvcTkn;
    }
}
