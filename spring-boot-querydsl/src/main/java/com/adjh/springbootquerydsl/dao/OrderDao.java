package com.adjh.springbootquerydsl.dao;

import com.adjh.springbootquerydsl.dto.OrderDto;
import org.springframework.stereotype.Repository;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : OrderDao
 * @since : 2024. 5. 16.
 */
@Repository
public interface OrderDao {

    OrderDto selectOrderSumItem(OrderDto orderDto);

    OrderDto selectOrderSumItem2(OrderDto orderDto);

}
