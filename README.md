# blog-codes

    ⭕️ 티스토리 블로그에 게시한 내용들을 담은 Repository입니다.

<br/>
<br/>

# Repository 설명

    ⭕️ 하위 디렉토리내의 레포지토리들에 대해 설명합니다.

<br/>

## 1. 요약

| Repository                    | 설명                                                                                                                | 완료 여부 |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------- | :-------: |
| java-algorithm                | Java 기반의 알고리즘 별 문제들을 관리하는 Repository입니다.                                                         |  진행중   |
| spring-boot-common            | 공통적으로 사용되는 정규식, 유틸등을 관리하는 Repository입니다.                                                     |   완료    |
| spring-boot-short             | 간단한 문제 처리를 위한 모듈을 구성한 Repository입니다.                                                             |  진행중   |
| spring-boot-3tier-form        | 3-Tier 형태의 아키텍처 기반으로 구성한 form Repository입니다.                                                       |   완료    |
| spring-boot-jpa               | Spring Boot Data 라이브러리를 활용하여 JpaRepository, JPQL, Criteria API를 활용한 예시를 관리하는 Repository입니다. |   완료    |
| spring-boot-querydsl          | JPA와 QueryDSL을 이용하여서 여러가지 사용방법의 예시를 관리하는 Repository입니다.                                   |   완료    |
| spring-boot-chatgpt           | OpenAI의 ChatGPT와의 통신을 통해 프롬프트를 수행하는 예시를 관리하는 Repository입니다.                              |   완료    |
| spring-boot-fcm               | 디바이스를 대상으로 FCM 메시지를 전송하는 예시를 관리하는 Repository입니다.                                         |   완료    |
| spring-boot-scheduler         | Spring Boot Quartz 라이브러리를 활용하여 일정 시간 마다 FCM 메시지를 전송하는 예시를 관리하는 Repository입니다.     |  초 완료  |
| spring-boot-test              | JUnit, MockMVC, Mokito를 활용한 예시를 관리하는 Repository입니다.                                                   |   완료    |
| spring-boot-rabbitmq          | RabbitMQ의 메시지 큐를 생성하는 이벤트 생성자를 구성한 Repository입니다.                                            |   완료    |
| spring-boot-rabbitmq-consumer | RabbitMQ의 메시지 큐를 수신하는 이벤트 소비자를 구성한 Repository입니다                                             |   완료    |

<br/>
<br/>

## 2. 상세 설명

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
- [Dockerfile을 이용한 RabbitMQ 환경 구성 및 실행방법](https://adjh54.tistory.com/496)

<br/>
<br/>
