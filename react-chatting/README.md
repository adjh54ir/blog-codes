# react-chatting

    ⭕️ Node + Socket.io를 기반으로 구성된 WebSocket 연결 구성 환경
    ⭕️ Java + STOMP를 기반으로 구성된 WebSocket 연결 구성 환경

    ⭕️ 이 둘의 WebSocket 연결하고 이를 활용하여 각각의 채팅 화면을 구성합니다.

<br/>
<br/>

## 1. 참고 사이트

### 1.1. Node + Socket.io를 활용한 WebSocket 참고 링크

- [Socket.io 기반 소켓 서버 구축 방법 -1 : 구성 요소 및 흐름 + React 기반 채팅 화면 구성](https://adjh54.tistory.com/548)
- [Socket.io 기반 소켓 서버 구축 방법 -2 : Room, Namespace 별 소켓 통신 방법](https://adjh54.tistory.com/549)
- [Socket.io 기반 소켓 서버 구축 방법 -3: PM2 클러스터링 + Redis 기반 분산 처리](https://adjh54.tistory.com/567)
- [PM2(Process Manager 2) 이해하고 주요 특징 알아보기](https://adjh54.tistory.com/551)

<br/>

### 1.2. Java + STOMP를 활용한 WebSocket 참고 링크

- [Spring Boot WebSocket + STOMP 이해하고 구성하기 -1: 초기 구성 및 간단 소켓 연결](https://adjh54.tistory.com/573)
- [STOMP 기반 소켓 서버 활용하기 : @stomp/stompjs, sockjs-client](https://adjh54.tistory.com/574)

<br/>
<br/>

## 2. 개발환경

    ⭕️ Spring Boot WebSocket을 구성하기 위한 환경입니다.

| 구성             | 분류      | 환경 버전 |
| ---------------- | --------- | --------- |
| Node             | 공통      | 21.7.1    |
| react            | 공통      | 18.3.1    |
| react-router     | 공통      | 6.26.0    |
| typescript       | 공통      | 4.9.5     |
| socket.io-client | socket.io | 4.9.5     |
| @stomp/stompjs   | stomp     | 7.0.0     |
| stompjs          | stomp     | 2.3.3     |

<br/>
<br/>

## 3. 프로젝트 실행 방법

```shell
# 라이브러리 설치
$ npm i

# 프로젝트 실행
$ npm start

```

<br/>
<br/>

## 4. 라우팅

    ⭕️ 각각 페이지 Path 뿐만 아니라 컴포넌트 별 설명을 확인해봅니다.

| **Path**                | **Component**              | **설명**                                                                                   |
| ----------------------- | -------------------------- | ------------------------------------------------------------------------------------------ |
| **/**                   | Navigate to /main          | - main으로 리다이렉트를 시킵니다.                                                          |
| **/main**               | MainComponent              | - 메인 페이지로 각각 Socket.io / STOMP에 대한 테스트를 할수 있는 공간으로 분리 되었습니다. |
| **/socketIo**           | SocketIoComponent          | - 기본적인 Socket.io를 채팅으로 테스트 해 볼 수 있는 컴포넌트                              |
| **/socketIo/room**      | SocketIoRoomComponent      | - Socket.io의 Room을 채팅으로 이용한 테스트 해 볼 수 있는 컴포넌트                         |
| **/socketIo/namespace** | SocketIoNameSpaceComponent | - Socket.io의 NameSpace을 채팅으로 이용한 테스트 해 볼 수 있는 컴포넌트                    |
| **/socketIo/redis**     | SocketIoRedisComponent     | - Socket.io와 Redis를 활용한 채팅으로 테스트 해 볼 수 있는 컴포넌트                        |
| **/stomp**              | StompComponent             | - STOMP를 이용한 채팅으로 테스트 해 볼 수 있는 컴포넌트                                    |

<br/>
<br/>

## 5. 결과 화면

### 1.SocketIoComponent

<img src = "https://github.com/user-attachments/assets/e9e8f8ca-68f6-41f8-91be-c00dd9bb00b5">

<br/>

### 2.SocketIoRoomComponent

<img src = "https://github.com/user-attachments/assets/f8cd3e57-287f-406b-8c79-807df53b3bee">

<br/>
<br/>

<img src ="https://github.com/user-attachments/assets/b239fd5f-222d-4078-9e92-17cfb362c594">

<br/>
<br/>

### 3. SocketIoNameSpaceComponent

<img src="https://github.com/user-attachments/assets/d9f645bb-d93d-4f30-badf-73c5f2b077c8">

<br/>
<br/>

### 4. SocketIoRedisComponent

<img src ="https://github.com/user-attachments/assets/a8d795e4-796c-4151-9a7b-fc35fa56f2ae">

<br/>
<br/>

### 5. StompComponent

<img src="https://github.com/user-attachments/assets/3c33a090-8fab-41be-9083-3fc4f8c5cf2b">

<br/>
<br/>
