package com.adjh.springbootquerydsl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : OrderDto
 * @since : 2024. 5. 16.
 */
@Getter
@ToString
@NoArgsConstructor
public class OrderDto {

    private long orderSq;
    private Long userSq;
    private Timestamp orderDate;
    private int totalPrice;

    @Builder(toBuilder = true)
    public OrderDto(long orderSq, Long userSq, Timestamp orderDate, int totalPrice) {
        this.orderSq = orderSq;
        this.userSq = userSq;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }
}
