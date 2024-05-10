package com.adjh.springbootjpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserOrderDto
 * @since : 2024. 5. 7.
 */
@Getter
@ToString
@NoArgsConstructor
public class UserOrderDto {
    private long userSq;

    private String userId;

    private String userNm;

    private String userSt;

    private long orderSq;

    private String orderNm;

    private String orderReq;

    private Timestamp orderDate;

    @Builder(toBuilder = true)
    public UserOrderDto(long userSq, String userId, String userNm, String userSt, long orderSq, String orderNm, String orderReq, Timestamp orderDate) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
        this.orderSq = orderSq;
        this.orderNm = orderNm;
        this.orderReq = orderReq;
        this.orderDate = orderDate;
    }
}
