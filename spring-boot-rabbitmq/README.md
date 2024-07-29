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

### 1.1. 프로젝트 개발환경

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

### 1.2. 디렉터리 구조

| 상위 디렉터리        | 파일 명                       | 설명                                                                                      |
|----------------|----------------------------|-----------------------------------------------------------------------------------------|
| config         | RabbitMqConnConfig         | RabbitMQ와의 연결과 데이터 통신을 AMQP를 이용하기 위한 RabbitMQTemplate을 구성한 클래스입니다.                      |
|                | RabbitMqDefaultConfig      | RabbitMQ에서 사용되는 Exchange 방식(Direct, Fanout, Header, Topic)을 구성한 클래스입니다.                 |
|                | RabbitMqDeadLetterConfig   | RabbitMQ에서 사용되는 Dead Lettering 처리를 위해 구성한 클래스입니다.                                       |
|                | RabbitMqClassicQueueConfig | RabbitMQ에서 클래식 큐(Classic Queue)를 구성한 클래스입니다.                                            |
|                | RabbitMqQuorumQueueConfig  | RabbitMQ에서 쿼럼 큐(Quorum Queue)를 구성한 클래스입니다.                                              |
| model/constant | ConstQueueAttr             | RabbitMQ에서 큐를 구성하는데 사용하는 속성 키에 대한 정의 클래스입니다                                             |
| controller     | -                          | RabbitMQ에서 Consumer로 구성한 큐에 데이터를 전송 처리를 관리하는 Controller 클래스입니다.                         |
| service        | -                          | RabbitMQ에서 RabbitTemplate을 이용하여 특정 Exchange와 Route key를 이용하여 메시지 적재 처리를 하는 비즈니스 클래스입니다. |


## 2. 개발 처리 구성도 

### 2.1. Exchange 종류 별 구성도

    ️⭕ 해당 구성은 프로젝트의 config/RabbitMqDefaultConfig 파일 내에서 확인 할 수 있습니다.

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/25101a53-f282-4a83-9794-e6f78619664e"/>

<br />
<br />

### 2.2. Dead Lettering 처리 구성도

    ⭕️ 해당 구성은 프로젝트의 config/RabbitMqDeadLetterConfig 파일 내에서 확인 할 수 있습니다.

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/ac1a846f-014e-439b-aa4a-a1a13191a4c1"/>

<br />
<br />

### 2.3. 클래식 큐 처리 구성도





## 3. RabbitMQ Exchange 종류

### 3.1. Direct Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/86d7040c-c0d2-455c-8ad5-9cfce565596f"/>

<br />

### 3.2. Fanout Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/657177ce-8118-4616-b8d6-a3652114b550"/>


<br />

### 3.3. Headers Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/7a2536ca-a0fc-466c-81be-57a42c35d580"/>

<br />

### 3.4. Topic Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/4402a333-dbb3-4a86-bc5d-1654321ef428"/>

<br />

### 3.5. Dead Letter Exchange

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/b8f71b00-cacb-46e5-b745-0633d0c702aa"/>


<br />

## 4. Queue 종류 




<br />
<br />

## 4. API Endpoint

| End point                                              | HTTP METHOD | 분류                           | 설명                                                                       |
|--------------------------------------------------------|:------------|:-----------------------------|--------------------------------------------------------------------------|
| http://localhost:8080/api/v1/producer/exchange/direct  | POST        | Direct Exchange              | 생산자(Proceduer)가 Direct Exchange 메시지를 전송합니다.                              |
| http://localhost:8080/api/v1/producer/exchange/fanout  | POST        | Fanout Exchange              | 생산자(Proceduer)가 Fanout Exchange 메시지를 전송합니다.                              |
| http://localhost:8080/api/v1/producer/exchange/headers | POST        | Headers Exchange             | 생산자(Proceduer)가 Header Exchange 메시지를 전송합니다.                              |
| http://localhost:8080/api/v1/producer/exchange/topic   | POST        | Topic Exchange               | 생산자(Proceduer)가 Topic Exchange 메시지를 전송합니다.                               |
| http://localhost:8080/api/v1/producer/exchange/topic   | POST        | Dead Letter Exchange & Queue | 생산자(Proceduer)의 메시지 전송 중 오류가 발생하여 Dead Letter Exchange를 통해 QUEUE에 적재합니다. |

