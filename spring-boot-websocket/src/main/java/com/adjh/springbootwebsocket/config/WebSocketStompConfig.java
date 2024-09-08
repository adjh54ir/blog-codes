package com.adjh.springbootwebsocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * STOMP를 사용하여 메시지 브로커를 설정합니다
 * WebSocket 메시지 브로커의 설정을 정의하는 메서드들을 제공합니다. 이를 통해 메시지 브로커를 구성하고 STOMP 엔드포인트를 등록할 수 있습니다.
 *
 * @author : jonghoon
 * @fileName : WebSocketStompConfig
 * @since : 8/15/24
 */

@Configuration                      // 설정 클래스로 지정합니다.
@EnableWebSocketMessageBroker       // WebSocket 메시지 브로커를 활성화합니다.
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 메시지 브로커를 설정합니다.
     *
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*
         * enableSimpleBroker : 메시지 브로커를 활성화 하고 WebSocket에서 전송을 위한 Prefix를 지정합니다.
         * setApplicationDestinationPrefixes: WebSocket에서 전송을 위한 Prefix를 저장합니다.
         */

        // 구독 경로
        config.enableSimpleBroker("/pub");

        // 발행 경로
        config.setApplicationDestinationPrefixes("/sub");
    }

    /**
     * STOMP 엔드포인트를 등록하는 메서드입니다.
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         * addEndpoint : 클라이언트가 WebSocket에 연결하기 위한 엔드포인트를 "/ws-stomp"로 설정합니다.
         * withSockJS : WebSocket을 지원하지 않는 브라우저에서도 SockJS를 통해 WebSocket 기능을 사용할 수 있게 합니다.
         */
        registry
                .addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }




}
