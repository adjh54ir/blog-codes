# Spring Boot FCM

    ⭕️ Spring Boot FCM : 단건 메시지 전송 방법

<br/>
<br/>


⭕️ 가이드 링크

* [Spring Boot Firebase Cloud Message(FCM) 푸시 메시지 API 구축 -1 : firebase-admin](https://adjh54.tistory.com/432)

<br/>
<br/>

## 1. 개발환경

<img src ="https://github.com/adjh54ir/blog-codes/assets/70501374/55ef0be9-6961-408a-b255-4a62343bdc70"/>

<br/>

| 환경 분류                               | 버전      |
|-------------------------------------|---------|
| JDK                                 | Java 17 |
| spring-boot                         | 3.2.5   |
| spring-boot-starter-web             | 3.2.5   |
| Lombok                              | -       |
| com.google.firebase:firebase-admina | 9.2.0   |

## 2. 사용방법

    ⭕ 해당 프로젝트 내에서는 디바이스로 FCM 메시지를 전송하는 방법입니다.



| End point                             | HTTP METHOD | 설명                    |
|---------------------------------------|:------------|-----------------------|
| http://localhost:8080/api/v1/fcm/send | POST        | 디바이스로 FCM 메시지를 전송합니다. |