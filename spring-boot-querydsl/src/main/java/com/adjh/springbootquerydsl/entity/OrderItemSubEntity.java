package com.adjh.springbootquerydsl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * OrderItem 테이블의 SubQuery를 위한 가상 VIEW
 *
 * @author : lee
 * @fileName : OrderItemSubEntity
 * @since : 2024. 5. 17.
 */

@Entity
@Subselect(
        "SELECT SUM(price * quantity) AS total_price, order_sq " +
                "FROM tb_order_item " +
                "GROUP BY order_sq "
)
@Immutable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemSubEntity {
    @Id
    @Column(name = "order_sq")
    @Comment("주문 시퀀스")
    private Long orderSq;

    @Column(name = "total_price")
    @Comment("총 가격 합계")
    private int totalPrice;

    public OrderItemSubEntity(Long orderSq, int totalPrice) {
        this.orderSq = orderSq;
        this.totalPrice = totalPrice;
    }
}
