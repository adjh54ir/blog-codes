package com.adjh.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ의 설정파일 입니다.
 *
 * @author : jonghoon
 * @fileName : RabbitMqConfig
 * @since : 10/15/23
 */
@Configuration
public class RabbitMqConfig {

    // 1
    /**
     * RabbitMQ 접속 정보를 가져옵니다.
     */
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.port}")
    private int port;


    /**
     * Direct Exchange 구성
     *
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        // direct.exchange 이름의 Direct Exchange 구성
        return new DirectExchange("exchange.direct");
    }

    /**
     * Fanout Exchange 구성
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        // fanout.exchange 이름의 Fanout Exchange 구성
        return new FanoutExchange("exchange.fanout");
    }

    /**
     * Topic Exchange 구성
     *
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        // topic.exchange 이름의 Topic Exchange
        return new TopicExchange("exchange.topic");
    }

    /**
     * Headers Exchange 구성
     *
     * @return
     */
    @Bean
    HeadersExchange headersExchange() {
        // headers.exchange 이름의 Headers Exchange
        return new HeadersExchange("exchange.headers");
    }

    /**
     * Queue 구성
     *
     * @return
     */
    @Bean
    Queue queue1() {
        // queue1 이름의 큐를 구성합니다.
        return new Queue("queue1", false);
    }

    @Bean
    Queue queue2() {
        // queue2 이름의 큐를 구성합니다.
        return new Queue("queue2", false);
    }

    @Bean
    Queue queue3() {
        // queue3 이름의 큐를 구성합니다.
        return new Queue("queue3", false);
    }

    @Bean
    Queue queue4() {
        // queue4 이름의 큐를 구성합니다.
        return new Queue("queue4", false);
    }

    @Bean
    Queue queue5() {
        // queue5 이름의 큐를 구성합니다.
        return new Queue("queue5", false);
    }

    /**
     * Direct Exchange 와 Queue1 간의 바인딩을 수행합니다.
     * - Direct Exchange 방식으로 Queue1와 라우팅 키(Routing key)를 기반으로 바인딩 수행.
     *
     * @param directExchange
     * @param queue1
     * @return
     */
    @Bean
    Binding directBinding(DirectExchange directExchange, Queue queue1) {
        return BindingBuilder
                .bind(queue1)
                .to(directExchange)
                .with("order.pizza");     // 라우팅 키 (Routing key)
    }

    /**
     * Fanout Exchange 와 Queue2 간의 바인딩을 수행합니다.
     * - Fanout Exchange 방식으로 Queue2, Queue3와의 바인딩 수행(* 일괄 Queue2, Queue3에게 전송예정)
     *
     * @param fanoutExchange
     * @param queue2
     * @return
     */
    @Bean
    Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue queue2) {
        return BindingBuilder
                .bind(queue2)
                .to(fanoutExchange);            // 바인딩 된 모든 큐로 라우팅이 됩니다.
    }

    /**
     * Fanout Exchange 와 Queue3 간의 바인딩을 수행합니다.
     * - Fanout Exchange 방식으로 Queue2, Queue3와의 바인딩 수행(* 일괄 Queue2, Queue3에게 전송예정)
     *
     * @param fanoutExchange
     * @param queue3
     * @return
     */
    @Bean
    Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue queue3) {
        return BindingBuilder
                .bind(queue3)
                .to(fanoutExchange);            // 바인딩 된 모든 큐로 라우팅이 됩니다.
    }


    /**
     * Headers Exchange 와 Queue4 간의 바인딩을 수행합니다.
     * - Headers Exchange 방식으로 Queue4와 Header 값을 조건으로 바인딩 수행
     *
     * @param headersExchange
     * @param queue4
     * @return
     */
    @Bean
    Binding headersBinding(HeadersExchange headersExchange, Queue queue4) {
        return BindingBuilder
                .bind(queue4)
                .to(headersExchange)
                .where("x-api-key")     // Header 내에 "x-api-key" 라는 값이 존재하는 경우
                .exists();
    }

    /**
     * Topic Exchange 와 Queue5 간의 바인딩을 수행합니다.
     * - Topic Exchange 방식으로 Queue5와 특정 라우팅 패턴(Routing Pattern)을 기반으로 바인딩 수행
     *
     * @param topicExchange
     * @param queue5
     * @return
     */
    @Bean
    Binding topicBinding(TopicExchange topicExchange, Queue queue5) {
        return BindingBuilder
                .bind(queue5)
                .to(topicExchange)
                .with("order.*");
    }


    /**
     * RabbitMQ와의 연결을 위한 ConnectionFactory을 구성합니다.
     * - Application.properties의 RabbitMQ의 사용자 정보를 가져와서 RabbitMQ와의 연결에 필요한 ConnectionFactory를 구성합니다.
     *
     * @return ConnectionFactory
     */
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    /**
     * 메시지를 전송하고 수신하기 위한 JSON 타입으로 메시지를 변경합니다.
     * Jackson2JsonMessageConverter를 사용하여 메시지 변환을 수행합니다. JSON 형식으로 메시지를 전송하고 수신할 수 있습니다
     *
     * @return
     */
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    /**
     * 구성한 ConnectionFactory, MessageConverter를 통해 템플릿을 구성합니다.
     *
     * @param connectionFactory
     * @param messageConverter
     * @return
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

}