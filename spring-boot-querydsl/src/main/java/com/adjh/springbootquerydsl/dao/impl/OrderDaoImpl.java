package com.adjh.springbootquerydsl.dao.impl;

import com.adjh.springbootquerydsl.dao.OrderDao;
import com.adjh.springbootquerydsl.dto.OrderDto;
import com.adjh.springbootquerydsl.entity.QOrderEntity;
import com.adjh.springbootquerydsl.entity.QOrderItemEntity;
import com.adjh.springbootquerydsl.entity.QOrderItemSubEntity;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : OrderDaoImpl
 * @since : 2024. 5. 16.
 */
@Repository
public class OrderDaoImpl implements OrderDao {


    private final JPAQueryFactory queryFactory;

    public OrderDaoImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private final QOrderEntity qOrder = QOrderEntity.orderEntity;
    private final QOrderItemEntity qOrderItem = QOrderItemEntity.orderItemEntity;
    private final QOrderItemSubEntity qOrderItemSubEntity = QOrderItemSubEntity.orderItemSubEntity;

    /**
     * 주문에 대한 합계를 구합니다.
     *
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto selectOrderSumItem3(OrderDto orderDto) {
        return queryFactory
                .select(
                        Projections.fields(OrderDto.class,
                                qOrder.orderSq,
                                qOrder.orderDate,
                                qOrderItemSubEntity.totalPrice
                        )
                )
                .from(qOrder, qOrderItemSubEntity)
                .fetchOne();
    }



    /**
     * 주문에 대한 합계를 구합니다.
     *
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto selectOrderSumItem(OrderDto orderDto) {
        return queryFactory
                .select(
                        Projections.fields(OrderDto.class,
                                ExpressionUtils
                                        .as(JPAExpressions
                                                .select(qOrderItem.price.multiply(qOrderItem.quantity).sum())
                                                .from(qOrderItem)
                                                .where(qOrderItem.orderSq.eq(qOrder.orderSq)
                                                ), "totalPrice"),
                                qOrder.orderDate)
                )
                .from(qOrder)
                .where(qOrder.orderSq.eq(orderDto.getOrderSq()))
                .fetchOne();
    }

    /**
     * 주문에 대한 합계를 구합니다.
     *
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto selectOrderSumItem2(OrderDto orderDto) {
        return queryFactory
                .select(
                        Projections.fields(OrderDto.class,
                                qOrder.orderSq,
                                qOrder.orderDate,
                                qOrderItem.price.multiply(qOrderItem.quantity).sum().as("totalPrice")
                        )
                )
                .from(qOrder)
                .join(qOrderItem).on(qOrder.orderSq.eq(qOrderItem.orderSq))
                .where(qOrder.orderSq.eq(orderDto.getOrderSq()))
                .groupBy(qOrder.orderSq)
                .fetchOne();

    }

}
