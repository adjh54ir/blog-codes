# blog-codes

    ⭕️ 티스토리 블로그에 게시한 내용들을 담은 Repository입니다.

<br/>
<br/>

# Repository 설명

    ⭕️ 하위 디렉토리내의 레포지토리들에 대해 설명합니다.
    ⭕️ 해당 페이지 마다 각각 레포지토리 설명들이 포함되어 있습니다.

<br/>

## 1. 요약

| Repository 명                 | 설명                                                                                                                                 |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| gitignore                     | 자주 사용되는 gitignore 파일들을 관리하는 디렉토리입니다.                                                                            |
| java-algorithm                | Java 기반의 알고리즘 별 문제들을 관리하는 프로젝트입니다.                                                                            |
| spring-boot-common            | 공통적으로 사용되는 정규식, 유틸등을 관리하는 프로젝트입니다.                                                                        |
| spring-boot-short             | 간단한 문제 처리를 위한 모듈을 구성한 프로젝트입니다.                                                                                |
| spring-boot-3tier-form        | 3-Tier 형태의 아키텍처 기반으로 구성한 form 프로젝트입니다.                                                                          |
| spring-boot-jpa               | spring-boot-jpa 라이브러리를 활용하여 JpaRepository, JPQL, Criteria API를 구성하고 사용하는 API 서버를 구성한 프로젝트입니다.        |
| spring-boot-querydsl          | spring-boot-jpa와 querydsl-jap 라이브러리를 활용한 JPA, QueryDSL을 구성하고 사용하는 API 서버를 구성한 프로젝트입니다.               |
| spring-boot-chatgpt           | spring-boot-web 라이브러리를 활용하여 외부 OpenAI의 ChatGPT와의 통신을 통해 프롬프트를 수행하는 API 서버를 구성한 프로젝트입니다.    |
| spring-boot-fcm               | spring-boot-web과 firebase-admin 라이브러리를 활용한 디바이스를 대상으로 FCM 메시지를 전송하는 API 서버를 구성한 프로젝트입니다.     |
| spring-boot-scheduler         | spring-boot-quartz와 firebase-admin 라이브러리를 활용한 일정 시간마다 FCM 메시지를 전송하는 ‘스케줄러 서버’를 구성한 프로젝트입니다. |
| spring-boot-test              | spring-boot-test와 mockito-core 라이브러리를 활용한 JUnit, MockMVC, Mokito를 활용하여 구성한 프로젝트입니다.                         |
| spring-boot-rabbitmq          | spring-boot-amqp 라이브러리를 활용한 RabbitMQ의 메시지 큐를 생성하는 ‘이벤트 생성자’를 구성한 프로젝트입니다.                        |
| spring-boot-rabbitmq-consumer | spring-boot-amqp 라이브러리를 활용한 RabbitMQ의 메시지 큐를 수신하는 ‘이벤트 소비자’를 구성한 프로젝트입니다.                        |
| spring-boot-async             | spring-boot-web과 spring-boot-quartz 라이브러리를 활용하여 ‘일반 비동기’ 및 ‘배치 비동기’를 구현한 API 서버 프로젝트입니다.          |
| spring-boot-slack             | spring-boot-web와 slack-api-client 라이브러리를 활용하여 ‘Slack WebHook’ 전송 예시를 구현한 API 서버 프로젝트입니다.                 |
| spring-boot-websocket         | spring-boot-websocket 라이브러리를 활용하여 STOMP 프로토콜을 이용한 ‘웹 소켓 서버’ 프로젝트입니다.                                   |
| spring-boot-cors              | spring-boot-web 라이브러리를 활용한 ‘CORS 관리’를 구성한 API 서버 프로젝트입니다.                                                    |
| spring-boot2-security         | spring-boot-security 2.x + jjwt 라이브러리를 활용하여 ‘jwt 기반 로그인 수행 및 리소스 접근’을 구현한 API 서버 프로젝트입니다.        |
| spring-boot3-security         | spring-boot-security 3.x + jjwt 라이브러리를 활용하여 ‘jwt 기반 로그인 수행 및 리소스 접근’을 구현한 API 서버 프로젝트입니다.        |
| spring-boot-mail              | spring-boot-mail 라이브러리를 활용하여 SMPT를 활용하여 메일을 전송하는 API 서버 프로젝트입니다.                                      |
| spring-boot-swagger           | spring-boot 환경에서 Swagger 라이브러리를 활용하여 RESTful 웹 서비스를 설계, 구축, 문서화를 관리하는 API 서버 프로젝트입니다.        |
|                               |                                                                                                                                      |
| node-wss-server               | Node 기반의 PM2 + Redis + Socket.io 라이브러리를 이용한 ‘소켓 서버’를 구성한 프로젝트입니다.                                         |
| react-chatting                | Node + React 기반의 Socket.io-client, Stomp 라이브러리를 이용한 ‘채팅 시스템’을 구성한 프로젝트입니다.                               |
| react-login                   | Node + React 기반의 Spring Boot Security + JWT 기반의 ‘로그인’ 클라이언트를 구성한 프로젝트입니다.                                   |

<br/>
<br/>

## 2. Java + Spring Boot 기반 상세 설명

### 목차

[1. java-algorithm](#1-java-algorithm)  
[2. spring-boot-common](#2-spring-boot-common)  
[3. spring-boot-3tier-form](#3-spring-boot-3tier-form)  
[4. spring-boot-jpa](#4-spring-boot-jpa)  
[5. spring-boot-querydsl](#5-spring-boot-querydsl)  
[6. spring-boot-chatgpt](#6-spring-boot-chatgpt)  
[7. spring-boot-fcm](#7-spring-boot-fcm)  
[8. spring-boot-scheduler](#8-spring-boot-scheduler)  
[9. spring-boot-test](#9-spring-boot-test)  
[10. spring-boot-rabbitmq](#10-spring-boot-rabbitmq)  
[11. spring-boot-rabbitmq-consumer](#11-spring-boot-rabbitmq-consumer)  
[12. spring-boot-async](#12-spring-boot-async)  
[13. spring-boot-slack](#13-spring-boot-slack)  
[14. spring-boot-websocket](#14-spring-boot-websocket)  
[15. spring-boot2-security](#15-spring-boot2-security)  
[16. spring-boot3-security](#16-spring-boot3-security)  
[17. spring-boot-cors](#17-spring-boot-cors)

<br/>
<br/>

### 1. java-algorithm

    ⭕️ 알고리즘 & 자료구조를 응용한 문제에 대한 풀이들을 가지고 있습니다.

1. 알고리즘

- 탐색 알고리즘 > 해시
- 탐색 알고리즘 > 동적계획법
- 탐색 알고리즘 > 이진탐색
- 탐색 알고리즘 > 그리디 알고리즘
- 탐색 알고리즘 > 투포인터 알고리즘
- 탐색 알고리즘 > 완전 탐색: 비트 마스크, 브루트포스

<br/>

2. 자료구조

- 스택
- 큐

<br/>

🔗 참고 URL

- [Java 알고리즘 & 자료구조](https://adjh54.tistory.com/category/Java/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%20%26%20%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0)

<br/>
<br/>

### 2. spring-boot-common

    ⭕️ Java 언어에 대한 공통 기능들을 가지고 있는 Repository입니다.

🔗 Repository 주요 키워드

- 정규식
- 형 변환 유틸
- 암호화/복호화 유틸
- Network 유틸
- 숫자 유틸
- 문자열 유틸

<br/>

🔗 참고 URL

- [정규표현식(RegExp) 이해하기 : 패턴, 문자 클래스, 자주 사용 패턴](https://adjh54.tistory.com/104)
- [IP 주소 반환받는 방법 : 클라이언트 IP, 외부 IP, 내부 IP, 호스트 IP](https://adjh54.tistory.com/443)
- [SecureRandom을 이용한 랜덤 문자열 생성 방법: 숫자, 문자, 특수문자 조합, 임시 비밀번호](https://adjh54.tistory.com/426)
- [Spring Boot 환경에서 Jackson 모듈 활용하기 : JSON 파싱, 직렬화, 역 직렬화, JSON 파일 읽어오기/생성](https://adjh54.tistory.com/375)

<br/>
<br/>

### 3. spring-boot-3tier-form

    ⭕️ Spring Boot & Java 기반의 N-Tier 아키텍처를 기반으로 구성한 3-tier 아키텍처 format입니다.
    ⭕️ 해당 환경은 API Server 용도로 구성이 되어 있습니다.

🔗 Repository 주요 키워드

- 응용 계층(Application Layer) : Controller
- 비즈니스 계층(Business Layer) : Service
- 데이터 계층(Data Layer : Data Access, Database) : DAO, Database

<br/>

🔗 참고 URL  
[1. 계층화된 아키텍처(Layered Architecture) : N Tier Architecture](https://adjh54.tistory.com/105)  
[2. 개발 환경에 따라 각각 환경 파일 구성 방법: application.properties](https://adjh54.tistory.com/200)  
[3. 소프트웨어 아키텍처 10가지 패턴 -1 : 정의, 사용 사례](https://adjh54.tistory.com/453)  
[4. 소프트웨어 아키텍처 10가지 패턴 -2 : 정의, 사용 사례](https://adjh54.tistory.com/454)  
[6. 생성자 패턴 이해하기 : 점층적 생성자, 자바 빈즈, Builder 패턴)](https://adjh54.tistory.com/78)  
[7. 메서드 체이닝(Method Chaining) , 플루언트 인터페이스(Fluent Interface), 빌더 패턴(Builder Pattern)](https://adjh54.tistory.com/440)  
[8. RESTful API 설계 방법 -1 : 이해하기](https://adjh54.tistory.com/150)  
[9. RESTful API 설계 방법 -2 : 구성하기](https://adjh54.tistory.com/151)  
[10. Spring Web Annotation 이해하고 사용하기 -1 : 환경 구성](https://adjh54.tistory.com/311)  
[11. Spring Web Annotation 이해하고 사용하기 -2 : 요청 및 응답](https://adjh54.tistory.com/312)  
[12. Spring Web Annotation 이해하고 사용하기 -3 : 예외 처리 및 주입](https://adjh54.tistory.com/313)  
[13. MacOS 환경에서 Java JDK 설정 및 변경하기 : homebrew, 다운로드 파일](https://adjh54.tistory.com/216)  
[14. MacOS에서 PostgreSQL 로컬 데이터베이스 구성 방법](https://adjh54.tistory.com/80)  
[15. Global Exception 이해하고 구성하기 : Controller Exception](https://adjh54.tistory.com/79)  
[16. Business Exception 이해하고 구성하기 : Service Exception](https://adjh54.tistory.com/89)

<br/>
<br/>

### 4. spring-boot-jpa

    ⭕️ Spring Boot & Java 기반의 Spring Data JPA 라이브러리를 활용하여 구성된 Repository입니다.

🔗 Repository 주요 키워드

- JpaRepository
- JPQL
- Criteria API
- Name Method
- @Query
- NamedQuery

<br/>

🔗 참고 URL

- [Spring Boot Data JPA 이해하기 -1: ORM, JPA, Hibernate, QueryDSL 이론](https://adjh54.tistory.com/421)
- [Spring Boot Data JPA 이해하기 -2 : 초기 환경 구성 + JpaRepository 활용 방법](https://adjh54.tistory.com/422)
- [Spring Boot Data JPA 이해하기 -3 : JpaRepository 활용 방법 - Query Method, @Query, NamedQuery](https://adjh54.tistory.com/481)
- [Spring Boot Data JPA + JPQL 이해하기 -1 : 정의 및 기본동작](https://adjh54.tistory.com/479)
- [Spring Boot Data JPA + Criteria API 이해하기 -1 : 정의 및 기본동작](https://adjh54.tistory.com/483)

<br/>
<br/>

### 5. spring-boot-querydsl

    ⭕️ Spring Boot & Java 기반의 Spring Data JPA 라이브러리와 QueryDSL을 활용하여 구성된 Repository입니다.

🔗 Repository 주요 키워드

- QueryDSL 개발 환경 구축
- QueryDSL JOIN
- QueryDSL SubQuery

<br/>

🔗 참고 URL

- [Spring Boot Data JPA + QueryDSL 이해하기 -1 : 정의 및 구성요소](https://adjh54.tistory.com/484)
- [Spring Boot Data JPA + QueryDSL 이해하기 -2 : 초기 환경설정 및 사용예시](https://adjh54.tistory.com/485)
- [Spring Boot Data JPA + QueryDSL 이해하기 -3: Join 활용(내부, 외부, 패치, 세타)](https://adjh54.tistory.com/488)
- [Spring Boot Data JPA + QueryDSL 이해하기 -4 : 서브쿼리](https://adjh54.tistory.com/489)

<br/>
<br/>

### 6. spring-boot-chatgpt

    ⭕️ Spring Boot & Java 기반의 OpenAI의 ChatGPT와의 통신을 통해 프롬프트를 수행하는 기능으로 구성된 Repository입니다.

🔗 Repository 주요 키워드

- Open AI : ChatGPT 3.5 활용
- RESTTemplate

<br/>

🔗 참고 URL

- [Spring Boot 환경에서 ChatGPT API 활용하기 -1 : 정의, 환경구성, 간단한 활용방법](https://adjh54.tistory.com/372)
- [Spring Boot 환경에서 ChatGPT API 활용하기 -2 : 생태계, 레거시, 새로운 모델](https://adjh54.tistory.com/397)

<br/>
<br/>

### 7. spring-boot-fcm

    ⭕️ Spring Boot & Java 언어를 이용한 환경에서 디바이스를 대상으로 FCM 메시지를 전송하는 예시를 관리하는 Repository입니다.

🔗 Repository 주요 키워드

- Google Firebase Admin

<br/>

🔗 참고 URL

- [Spring Boot Firebase Cloud Message(FCM) 푸시 메시지 API 구축 -1 : firebase-admin](https://adjh54.tistory.com/432)

<br/>
<br/>

### 8. spring-boot-scheduler

    ⭕️ Spring Boot & Java 언어와 Spring Boot Queartz 라이브러리르 활용하여 스케줄러 서버로 이용되는 Repository입니다.

🔗 Repository 주요 키워드

- Spring Boot Queartz
- Google Firebase Admin

<br/>

🔗 참고 URL

- [Spring Boot Firebase Cloud Message(FCM) 푸시 메시지 API 구축 -1 : firebase-admin](https://adjh54.tistory.com/432)
- [Spring Boot Firebase Cloud Message(FCM) 구축 -2 : 예약 발송](https://adjh54.tistory.com/438)

<br/>
<br/>

### 9. spring-boot-test

    ⭕️ Spring Boot & Java 언어를 이용하며 JUnit5, MockMVC, Mokito를 활용한 예시를 관리하는 Repository입니다.

🔗 Repository 주요 키워드

- Spring Boot Test(Junit5)
- mockito-core

<br/>

🔗 참고 URL

1. [Spring Boot JUnit5 이해하기 -1 : 이론 및 구조](https://adjh54.tistory.com/341)
2. [Spring Boot JUnit5 이해하기 -2 : 환경구성 및 활용 예제](https://adjh54.tistory.com/342)
3. [Spring Boot Mockito 이해하기 : 테스트 흐름 및 사용예시](https://adjh54.tistory.com/346)
4. [Spring Boot MockMvc 이해하기 : 테스트 흐름 및 사용예제](https://adjh54.tistory.com/347)

<br/>
<br/>

### 10. spring-boot-rabbitmq

    ⭕️ Spring Boot 기반의 Java 언어를 이용하여 RabbitMQ의 메시지 큐를 구성하는 Repository입니다. : 이벤트 생성자

🔗 Repository 주요 키워드

- Event Proceducers
- spring-boot-starter-amqp

<br/>

🔗 참고 URL

- [Spring Boot AMQP RabbitMQ 이해하기 -1 : 구조 및 종류](https://adjh54.tistory.com/284)
- [Spring Boot AMQP RabbitMQ 이해하기 -2 : 로컬 환경 구성](https://adjh54.tistory.com/285)
- [Spring Boot AMQP RabbitMQ 이해하기 -3 : Java 구축 및 간단 예제](https://adjh54.tistory.com/292)
- [Spring Boot AMQP RabbitMQ 이해하기 -4 : RabbitMQ Exchange 유형 별 이해 및 사용 예시](https://adjh54.tistory.com/497)
- [Spring Boot AMQL RabbitMQ 이해하기 -5 : TTL 및 데드 레터링 사용예시](https://adjh54.tistory.com/501)
- [Dockerfile을 이용한 RabbitMQ 환경 구성 및 실행방법](https://adjh54.tistory.com/496)

<br/>
<br/>

### 11. spring-boot-rabbitmq-consumer

    ⭕️ Spring Boot 기반의 Java 언어를 이용하여 RabbitMQ의 메시지 큐를 수신하는 이벤트 소비자를 구성한 Repository입니다 : 이벤트 소비자

🔗 Repository 주요 키워드

- Event Consumers
- spring-boot-starter-amqp

<br/>

🔗 참고 URL

- [Spring Boot AMQP RabbitMQ 이해하기 -1 : 구조 및 종류](https://adjh54.tistory.com/284)
- [Spring Boot AMQP RabbitMQ 이해하기 -2 : 로컬 환경 구성](https://adjh54.tistory.com/285)
- [Spring Boot AMQP RabbitMQ 이해하기 -3 : Java 구축 및 간단 예제](https://adjh54.tistory.com/292)
- [Spring Boot AMQP RabbitMQ 이해하기 -4 : RabbitMQ Exchange 유형 별 이해 및 사용 예시](https://adjh54.tistory.com/497)
- [Spring Boot AMQL RabbitMQ 이해하기 -5 : TTL 및 데드 레터링 사용예시](https://adjh54.tistory.com/501)
- [Dockerfile을 이용한 RabbitMQ 환경 구성 및 실행방법](https://adjh54.tistory.com/496)

<br/>
<br/>

### 12. spring-boot-async

    ⭕️ Spring Boot 기반의 Java 언어를 이용하여 비동기 처리를 위한 방법에 대한 예시들을 구성한 Repository입니다.

🔗 Repository 주요 키워드

- @Async, @AsyncConfig, Thread Pool, 비동기 반환 유형, Executor

<br/>

🔗 참고 URL

- [Spring Boot Async 비동기 처리 이해하기 -1 : 주요 어노테이션 및 비동기 반환 유형](https://adjh54.tistory.com/544)
- [Spring Boot Async 비동기 처리 이해하기 -2 : Executor](https://adjh54.tistory.com/547)

<br/>
<br/>

### 13. spring-boot-slack

    ⭕️ spring-boot-web와 slack-api-client 라이브러리를 활용하여 ‘Slack WebHook’ 전송 예시를 구현한 API 서버 프로젝트입니다.

🔗 Repository 주요 키워드

- slack, slack webhook, 모니터링

<br/>

🔗 참고 URL

- [Spring Boot 환경에서 Slack Incoming Webhook 이해하고 구성하기 -1 : 초기 구성 및 간단 메시지 전송](https://adjh54.tistory.com/568)
- [Spring Boot 환경에서 Slack Incoming Webhook 이해하고 구성하기 -2 : 다양한 메시지 전송 방법](https://adjh54.tistory.com/569)

<br/>
<br/>

### 14. spring-boot-websocket

    ⭕️ spring-boot-websocket 라이브러리를 활용하여 STOMP 프로토콜을 이용한 ‘웹 소켓 서버’ 프로젝트입니다.

🔗 Repository 주요 키워드

- STMOP, websocket server

<br/>

🔗 참고 URL

- [Spring Boot WebSocket + STOMP 이해하고 구성하기 -1: 초기 구성 및 간단 소켓 연결](https://adjh54.tistory.com/573)

<br/>
<br/>

### 15. spring-boot2-security

    ⭕️ Spring Boot 2.x 환경에서 spring-boot-security 2.x + jjwt 라이브러리를 활용하여 ‘jwt 기반 로그인 수행 및 리소스 접근’을 구현한 API 서버 프로젝트입니다.

🔗 Repository 주요 키워드

- spring boot security, JWT, Access Token, Refresh Token

<br/>

🔗 참고 URL

- [Spring Boot Security 이해하기 -1 : 2.7.x 버전 구조 및 파일 이해](https://adjh54.tistory.com/91)
- [Spring Boot Security 이해하기 -2 : Spring Boot 2.x 버전 환경 구성하기](https://adjh54.tistory.com/92)
- [Spring Boot Security 이해하기 -3: JWT(JSON Web Token) 이해하기](https://adjh54.tistory.com/93)
- [Spring Boot Security 이해하기 -4: JWT 환경 설정 및 구성 하기](https://adjh54.tistory.com/94)

<br/>
<br/>

### 16. spring-boot3-security

    ⭕️ Spring Boot 3.x 환경에서 spring-boot-security 3.x + jjwt 라이브러리를 활용하여 ‘jwt 기반 로그인 수행 및 리소스 접근’을 구현한 API 서버 프로젝트입니다.

<br/>

🔗 Repository 주요 키워드

- spring boot security, JWT, Access Token, Refresh Token

<br/>

🔗 참고 URL

- [Spring Boot Security 3.x + JWT 이해하기 -1 : 구조 및 Client / Server 처리과정](https://adjh54.tistory.com/576)
- [Spring Boot Security 3.x + JWT 이해하기 -2 : 환경설정 및 구성](https://adjh54.tistory.com/577)
- [Spring Boot Security 3.x + JWT 이해하기 -3 : Refresh Token 활용한 자동 갱신 방법](https://adjh54.tistory.com/583)

<br/>
<br/>

### 17. spring-boot-cors

    ⭕️ spring-boot-web 라이브러리를 활용한 ‘CORS 관리’를 구성한 API 서버 프로젝트입니다.

🔗 Repository 주요 키워드

- spring boot web, cors

<br/>

🔗 참고 URL

- [교차 출처 리소스 공유 : CORS(Cross Origin Resource Sharing) 이해하기](https://adjh54.tistory.com/586)
- [Spring Boot 환경에서 CORS(Cross Origin Resource Sharing) 이해하고 활용하기 -1](https://adjh54.tistory.com/587)

<br/>
<br/>

## 3. 기타 상세 설명

### 0. 목차

[1. node-wss-server](#1-node-wss-server)  
[2. react-chatting](#2-react-chatting)  
[3. react-login](#3-react-login)

<br/>
<br/>

### 1. node-wss-server

    ⭕️ Node 기반의 PM2 + Redis + Socket.io 라이브러리를 이용한 ‘소켓 서버’를 구성한 프로젝트입니다.

🔗 Repository 주요 키워드

- Node, PM2, Redis, Socket.io, Socket Server

<br/>

🔗 참고 URL

- [Socket.io 기반 소켓 서버 구축 방법 -3: PM2 클러스터링 + Redis 기반 분산 처리](https://adjh54.tistory.com/567)
- [Socket.io 기반 소켓 서버 구축 방법 -1 : 구성 요소 및 흐름 + React 기반 채팅 화면 구성](https://adjh54.tistory.com/548)
- [Socket.io 기반 소켓 서버 구축 방법 -2 : Room, Namespace 별 소켓 통신 방법](https://adjh54.tistory.com/549)
- [PM2(Process Manager 2) 이해하고 주요 특징 알아보기](https://adjh54.tistory.com/551)

### 2. react-chatting

    ⭕️ Node + React 기반의 Socket.io-client, Stomp 라이브러리를 이용한 ‘채팅 시스템’을 구성한 프로젝트입니다.

🔗 Repository 주요 키워드

- Node, Socket.io-client, STOMP

<br/>

🔗 참고 URL

- [STOMP 기반 소켓 서버 활용하기 : @stomp/stompjs, sockjs-client](https://adjh54.tistory.com/574)

<br/>
<br/>

### 3. react-login

    ⭕️ Node + React 기반의 Spring Boot Security + JWT 기반의 ‘로그인’ 클라이언트를 구성한 프로젝트입니다.

🔗 Repository 주요 키워드

- Node, React, Spring Boot Security + JWT

🔗 참고 URL

- [Spring Boot Security 3.x + JWT 이해하기 -1 : 구조 및 Client / Server 처리과정](https://adjh54.tistory.com/576)
- [Spring Boot Security 3.x + JWT 이해하기 -2 : 환경설정 및 구성](https://adjh54.tistory.com/577)
- [Spring Boot Security 3.x + JWT 이해하기 -3 : Refresh Token 활용한 자동 갱신 방법](https://adjh54.tistory.com/583)
- [Axios Interceptor 동작 방법 이해하고 활용하기: JWT, 특정 URL 제외](https://adjh54.tistory.com/582)
