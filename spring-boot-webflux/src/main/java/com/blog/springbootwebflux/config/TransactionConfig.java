package com.blog.springbootwebflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 트랜잭션을 관리합니다.
 *
 * @author : leejonghoon
 * @fileName : TransactionConfig
 * @since : 2024. 12. 27.
 */
@Configuration
public class TransactionConfig {

    /**
     * 트랜잭션을 관리하는 연산자
     *
     * @param txManager
     * @return
     */
    @Bean
    public TransactionalOperator transactionalOperator(ReactiveTransactionManager txManager) {
        // 트랜잭션 속성 설정
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);       // 트랜잭션의 격리 수준 설정
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);      // 트랜잭션의 전파 동작 정의
        definition.setTimeout(30);                                                          // 트랜잭션의 타임아웃 정의
        return TransactionalOperator.create(txManager);
    }


}
