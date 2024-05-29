# Spring Boot RabbitMQ (Event Proceducers)

    ⭕️ Spring Boot RabbitMQ를 활용하여 이벤트 생성자(Event Proceducers)를 구성합니다.

<br />

⭕️ 가이드 링크

* [Spring Boot AMQP RabbitMQ 이해하기 -1 : 구조 및 종류](https://adjh54.tistory.com/284)
* [Spring Boot AMQP RabbitMQ 이해하기 -2 : 로컬 환경 구성](https://adjh54.tistory.com/285)
* [Spring Boot AMQP RabbitMQ 이해하기 -3 : Java 구축 및 간단 예제](https://adjh54.tistory.com/292)
* [Spring Boot AMQP RabbitMQ 이해하기 -4 : RabbitMQ Exchange 유형 별 이해 및 사용 예시](https://adjh54.tistory.com/497)
* [Dockerfile을 이용한 RabbitMQ 환경 구성 및 실행방법](https://adjh54.tistory.com/496)

<br />
<br />

## 1. 개발환경

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/25101a53-f282-4a83-9794-e6f78619664e"/>

| 환경 분류                                       | 버전      |
|---------------------------------------------|---------|
| JDK                                         | Java 17 |
| spring-boot                                 | 3.2.5   |
| spring-boot-starter-web                     | 3.2.5   |
| spring-boot-starter-amqp                    | 3.2.5   |
| Lombok                                      | -       |
| com.fasterxml.jackson.core:jackson-databind | 2.16.1  |

<br />
<br />

## 2. Exchange 종류

### 2.1. Direct Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/86d7040c-c0d2-455c-8ad5-9cfce565596f"/>

<br />

### 2.2. Fanout Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/657177ce-8118-4616-b8d6-a3652114b550"/>


<br />

### 2.3. Headers Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/7a2536ca-a0fc-466c-81be-57a42c35d580"/>

<br />

### 2.4. Topic Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/4402a333-dbb3-4a86-bc5d-1654321ef428"/>


<br />
<br />

## 4. API Endpoint

| End point                                            | HTTP METHOD | Exchange 분류      | 설명                                          |
|------------------------------------------------------|:------------|:-----------------|---------------------------------------------|
| http://localhost:8080/api/v1/producer/directMessage  | POST        | Direct Exchange  | 생산자(Proceduer)가 Direct Exchange 메시지를 전송합니다. |
| http://localhost:8080/api/v1/producer/fanoutMessage  | POST        | Fanout Exchange  | 생산자(Proceduer)가 Fanout Exchange 메시지를 전송합니다. |
| http://localhost:8080/api/v1/producer/headersMessage | POST        | Headers Exchange | 생산자(Proceduer)가 Header Exchange 메시지를 전송합니다. |
| http://localhost:8080/api/v1/producer/topicMessage   | POST        | Topic Exchange   | 생산자(Proceduer)가 Topic Exchange 메시지를 전송합니다.  |

