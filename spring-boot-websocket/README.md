# Spring Boot WebSocket

    ⭕️ Spring Boot WebSocket을 활용하는 방법의 예시를 담은 Repository 입니다.

<br/>

- [Spring Boot WebSocket + STOMP 이해하고 구성하기 -1: 초기 구성 및 간단 소켓 연결](https://adjh54.tistory.com/573)

<br/>
<br/>

## 1. 개발환경

    ⭕️ Spring Boot WebSocket을 구성하기 위한 환경입니다.

| 환경 분류                         | 버전    |
|-------------------------------|-------|
| JDK                           | 17    |
| spring-boot                   | 3.3.2 |
| spring-boot-starter-websocket | 3.3.2 |
| Lombok                        | -     |

<br/>
<br/>

## 2. API Endpoint

| Endpoint                       | HTTP Method | 설명                              |
|--------------------------------|-------------|---------------------------------|
| http://localhost:8081/ws-stomp | -           | 구성한 WebSocket 연결하기 위한 엔드포인트입니다. |
| /api/v1/pub/messages           | -           | 메시지를 '전송'하기 위해 사용되는 엔드포인트입니다.   |
| /api/v1/sub/message            | -           | 메시지를 '수신'하기 위해 사용되는 엔드포인트입니다.   |

(* 해당 엔드포인트의 경우 HTTP Method가 없는 이유는 WebSocket 연결 이후 메시지로써 통신을 하기에 별도의 HTTP Method를 이용하지 않습니다.)

<br/>
<br/>

## 3. 결과 화면

### 1. Chrome Extension을 이용한 WebSocket 연결 화면

<img src="https://github.com/user-attachments/assets/55ef6ddd-94eb-441c-a7b2-c8e3534f2eb1">

<br/>
<br/>

### 2. 실제 해당 Websocket의 연결을 통해 구성한 채팅방 화면

<img src="https://github.com/user-attachments/assets/52f26ee4-a952-4e42-8219-2d469d9fc535">





