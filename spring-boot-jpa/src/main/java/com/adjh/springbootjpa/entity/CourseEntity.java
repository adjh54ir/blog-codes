package com.adjh.springbootjpa.entity;


import jakarta.persistence.*;
import lombok.Getter;


/**
 * tb_order 테이블과 매핑 객체 : 수업
 *
 * @author : lee
 * @fileName : CourseEntity
 * @since : 4/17/24
 */
@Entity
@Getter
@Table(name = "tb_course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    @Column(name = "course_nm")
    private String courseNm;

}
