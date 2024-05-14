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
 * @fileName : UserDeviceAlarmEntity
 * @since : 2024. 5. 14.
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_user_device_alarm")
public class UserDeviceAlarmEntity {

    @Id
    @Column(name = "alarm_sq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("알람 시퀀스")
    private Long alarmSq;

    @Column(name = "device_sq")
    @Comment("디바이스 시퀀스")
    private int deviceSq;

    @Column(name = "alarm_tm")
    @Comment("알람 시간")
    private Timestamp alarmTm;


    @Column(name = "alarm_title")
    @Comment("알람 제목")
    private String alarmTitle;

    @Column(name = "alarm_content")
    @Comment("알람 내용")
    private String alarmContent;

    @Column(name = "use_yn")
    @Comment("사용여부")
    private int useYn;

}
