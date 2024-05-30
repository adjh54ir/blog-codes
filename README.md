# blog-codes

    ⭕️ 티스토리 블로그에 게시한 내용들을 담은 Repository입니다.

<br/>
<br/>

# Repository 설명

    ⭕️ 하위 디렉토리내의 레포지토리들에 대해 설명합니다.

<br/>

## 1. 요약

| &nbsp;&nbsp;&nbsp;&nbsp; Repository &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; | 설명                                                                                                                                                             | 구성 완료 여부 |
| ------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------- |
| java-algorithm                                                           | Java 기반의 알고리즘 별 문제들을 관리하는 Repository입니다.                                                                                                      | 진행중         |
| spring-boot-common                                                       | Spring Boot 기반 Java 언어를 이용한 환경에서 공통적으로 사용되는 정규식, 유틸등을 관리하는 레포지토리입니다.                                                     | 최초 구성 완료 |
| spring-boot-3tier-form                                                   | Spring Boot 기반 Java 언어를 이용하여 3-Tier 형태의 아키텍처 기반으로 구성한 form 레포지토리입니다.                                                              | 최초 구성 완료 |
| spring-boot-jpa                                                          | Spring Boot 기반 Java 언어를 이용한 환경에서 Spring Boot Data 라이브러리를 활용하여 JpaRepository, JPQL, Criteria API를 활용한 예시를 관리하는 레포지토리입니다. | 최초 구성 완료 |
| spring-boot-querydsl                                                     | Spring Boot 기반의 Java 언어를 이용하며 JPA와 QueryDSL을 이용하여서 여러가지 사용방법의 예시를 관리하는 레포지토리입니다.                                        | 최초 구성 완료 |
| spring-boot-chatgpt                                                      | Spring Boot 기반 Java 언어를 이용한 환경에서 OpenAI의 ChatGPT와의 통신을 통해 프롬프트를 수행하는 예시를 관리하는 레포지토리입니다.                              | 최초 구성 완료 |
| spring-boot-fcm                                                          | Spring Boot 기반의 Java 언어를 이용한 환경에서 디바이스를 대상으로 FCM 메시지를 전송하는 예시를 관리하는 레포지토리입니다.                                       | 최초 구성 완료 |
| spring-boot-scheduler                                                    | Spring Boot 기반의 Java 언어를 이용하며 주요 Spring Boot Quartz 라이브러리를 활용하여 일정 시간 마다 FCM 메시지를 전송하는 예시를 관리하는 레포지토리입니다.     | 최초 구성 완료 |
| spring-boot-test                                                         | Spring Boot 기반의 Java 언어를 이용하며 JUnit, MockMVC, Mokito를 활용한 예시를 관리하는 Repository입니다.                                                        | 진행중         |
| spring-boot-rabbitmq                                                     | Spring Boot 기반의 Java 언어를 이용하여 RabbitMQ의 메시지 큐를 구성하는 Repository입니다                                                                         | 최초 구성 완료         |
| spring-boot-rabbitmq-consumer                                                     | Spring Boot 기반의 Java 언어를 이용하여 RabbitMQ의 메시지 큐를 수신하는 이벤트 소비자를 구성한 Repository입니다                                                                         | 최초 구성 완료         |

<br/>
<br/>

## 2. 상세 설명

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
<br/>


### 3. spring-boot-3tier-form

    ⭕️ Spring Boot & Java 기반의 N-Tier 아키텍처를 기반으로 구성한 3-tier 아키텍처 format입니다.
    ⭕️ 해당 환경은 API Server 용도로 구성이 되어 있습니다.

🔗 Repository 주요 키워드

- 응용 계층(Application Layer) : Controller
- 비즈니스 계층(Business Layer) : Service
- 데이터 계층(Data Layer : Data Access, Database) : DAO, Database

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
<br/>


### 5. spring-boot-querydsl

    ⭕️ Spring Boot & Java 기반의 Spring Data JPA 라이브러리와 QueryDSL을 활용하여 구성된 Repository입니다.

🔗 Repository 주요 키워드

- QueryDSL 개발 환경 구축
- QueryDSL JOIN
- QueryDSL SubQuery

<br/>
<br/>


### 6. spring-boot-chatgpt

    ⭕️ Spring Boot & Java 기반의 OpenAI의 ChatGPT와의 통신을 통해 프롬프트를 수행하는 기능으로 구성된 Repository입니다.


🔗 Repository 주요 키워드

- Open AI : ChatGPT 3.5 활용
- RESTTemplate

<br/>
<br/>


### 7. spring-boot-fcm

    ⭕️ Spring Boot & Java 언어를 이용한 환경에서 디바이스를 대상으로 FCM 메시지를 전송하는 예시를 관리하는 Repository입니다.


🔗 Repository 주요 키워드

- Google Firebase Admin 

<br/>
<br/>


### 8. spring-boot-scheduler

    ⭕️ Spring Boot & Java 언어와 Spring Boot Queartz 라이브러리르 활용하여 스케줄러 서버로 이용되는 Repository입니다.

🔗 Repository 주요 키워드

- Spring Boot Queartz
- Google Firebase Admin
<br/>
<br/>


### 9. spring-boot-test

    ⭕️ Spring Boot & Java 언어를 이용하며 JUnit5, MockMVC, Mokito를 활용한 예시를 관리하는 Repository입니다.


🔗 Repository 주요 키워드

- Spring Boot Test(Junit5)  
- mockito-core
    
<br/>
<br/>

### 10. spring-boot-rabbitmq

    ⭕️ Spring Boot 기반의 Java 언어를 이용하여 RabbitMQ의 메시지 큐를 구성하는 Repository입니다. : 이벤트 생성자


🔗 Repository 주요 키워드

- Event Proceducers
- spring-boot-starter-amqp


<br/>
<br/>


### 11. spring-boot-rabbitmq-consumer

    ⭕️ Spring Boot 기반의 Java 언어를 이용하여 RabbitMQ의 메시지 큐를 수신하는 이벤트 소비자를 구성한 Repository입니다 : 이벤트 소비자 

🔗 Repository 주요 키워드

- Event Consumers
- spring-boot-starter-amqp
<br/>
<br/>