package com.adjh.springbootquerydsl.controller;

import com.adjh.springbootquerydsl.dao.OrderDao;
import com.adjh.springbootquerydsl.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : OrderController
 * @since : 2024. 5. 16.
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    /**
     * 주문에 대한 합계(가격 * 수량)
     *
     * @param orderDto
     * @return
     */
    @PostMapping("/sumOrder")
    public ResponseEntity<OrderDto> selectSumOrder(@RequestBody OrderDto orderDto) {
        OrderDto result = orderDao.selectOrderSumItem(orderDto);
        System.out.println("결과값 :: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 주문에 대한 합계(가격 * 수량)
     *
     * @param orderDto
     * @return
     */
    @PostMapping("/sumOrder2")
    public ResponseEntity<OrderDto> selectSumOrder2(@RequestBody OrderDto orderDto) {
        OrderDto result = orderDao.selectOrderSumItem2(orderDto);
        System.out.println("결과값 :: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
