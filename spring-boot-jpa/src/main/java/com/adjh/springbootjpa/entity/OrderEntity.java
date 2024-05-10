package com.adjh.springbootjpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * tb_order 테이블과 매핑 객체
 *
 * @author : lee
 * @fileName : OrderEntity
 * @since : 4/9/24
 */
@Entity
@Table(name = "tb_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_sq")
    @Comment("주문 시퀀스")
    private long orderSq;

    @Column(name = "user_sq", insertable = false, updatable = false)
    @Comment("사용자 시퀀스")
    private Long userSq;

    @Column(name = "order_nm")
    @Comment("주문명")
    private String orderNm;

    @Column(name = "order_req")
    @Comment("주문 요청 정보")
    private String orderReq;

    @Column(name = "order_date")
    @Comment("주문일자")
    @CreationTimestamp
    private Timestamp orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sq")
    private UserEntity userInfo;
}
