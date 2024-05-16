package com.adjh.springbootquerydsl.entity;

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
    private long orderSq;

    @Column(name = "product_id")
    @Comment("물품 아이디")
    private String productId;

    @Column(name = "quantity")
    @Comment("항목 수량")
    private int quantity;

    @Column(name = "price")
    @Comment("물품 수량")
    private int price;

}
