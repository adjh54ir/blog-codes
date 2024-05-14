package com.adjh.springbootquerydsl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserDeviceEntity
 * @since : 2024. 5. 14.
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_user_device")
public class UserDeviceEntity {

    @Id
    @Column(name = "device_sq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("디바이스 시퀀스")
    private Long deviceSq;

    @Column(name = "user_sq")
    @Comment("사용자 시퀀스")
    private int userSq;


    @Column(name = "device_uuid")
    @Comment("디바이스 UUID")
    private String deviceUuid;

    @Column(name = "device_token")
    @Comment("디바이스 토큰(FCM Token)")
    private String deviceToken;

    @Column(name = "reg_date")
    @Comment("디바이스 토큰(FCM Token)")
    private Timestamp regDate;


}
