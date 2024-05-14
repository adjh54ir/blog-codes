package com.adjh.springbootjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : OrderItemEntity
 * @since : 2024. 5. 14.
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_order_item")
public class OrderItemEntity {

    @Id
    @Column(name = "order_item_sq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("물품 시퀀스")
    private Long orderItemSq;

    @Column(name = "order_sq")
    @Comment("주문 시퀀스")
    private int orderSq;

    @Column(name = "order_item_nm")
    @Comment("물품 명")
    private String orderItemNm;

    @Column(name = "order_item_quantity")
    @Comment("물품 수량")
    private String orderItemQuantity;

    @Column(name = "order_item_price")
    @Comment("물품 수량")
    private String orderItemPrice;

}
