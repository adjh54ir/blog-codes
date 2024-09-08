package com.adjh.springbootwebsocket.config;

import com.adjh.springbootwebsocket.config.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 웹 소켓 설정 구성
 *
 * @author : jonghoon
 * @fileName : WebSocketConfig
 * @since : 8/15/24
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * WebSocket Handler를 등록합니다.
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("[+] 최초 WebSocket 연결을 위한 등록 Handler");
        registry
                .addHandler(new ChatWebSocketHandler(), "ws-stomp")    // 클라이언트에서 /chat 경로로 WebSocket 연결을 시도하면 ChatWebSocketHandler 클래스에서 이를 처리합니다.
                .setAllowedOrigins("*");                                    // 모든 도메인 또는 IP에서 WebSocket 연결을 허용합니다.
    }
}
