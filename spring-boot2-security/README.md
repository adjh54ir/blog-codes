# spring-boot2-security

    ⭕️ Spring Boot 2.7.18 버전 기준으로 JWT를 활용한 로그인 구현 예시 Repository

<br/>
<br/>

## 1. Architecture

----

### 1.1. Spring Boot Architecture

    ⭕️ 전체적인 아키텍처 패턴은 Spring MVC Pattern으로 구성하였습니다.

![springboot](https://user-images.githubusercontent.com/36141397/193234055-b13617ee-3e83-4298-bd17-6215f2a2cfe3.png)

### 1.2. Spring Security Architecture

    ⭕️ Spring Security의 FlowChart입니다.

![image](https://user-images.githubusercontent.com/70501374/210165654-53655b48-e979-4d55-999b-aec199591eb3.png)


<br/>
<br/>

## 2. 개발환경

| 개발환경                                                | 분류                  | 버전     | 설명                                              |
|-----------------------------------------------------|---------------------|--------|-------------------------------------------------|
| java jdk                                            | java version        | 17     | Java                                            |
| spring-boot                                         | spring boot starter | 2.7.18 | Spring Boot                                     |
| spring-boot-web                                     | spring boot starter | 2.7.18 | API 통신을 위해서 사용이 됩니다                             |
| spring-boot-security                                | spring boot starter | 2.7.18 | API 통신에서 보안적 요소를 추가하기 위해 사용이 됩니다                |
| org.mybatis.spring.boot:mybatis-spring-boot-starter | spring boot starter | 2.2.2  | SQLMapper로 MyBatis를 이용하기 위해 사용이 됩니다.            |
| io.jsonwebtoken:jjwt                                | opensource          | 0.9.1  | API 통신에서 Security에서 JWT를 이용한 데이터 통신을 위해 사용이 됩니다 |
| org.postgresql:postgresql                           | opensource          | 42.7.4 | PostgreSQL 데이터베이스를 이용하기 위해 사용됩니다.               |
| com.googlecode.json-simple:json-simple              | opensource          | 1.1.1  | 데이터 바인딩을 위해 사용이 됩니다                             |
| com.fasterxml.jackson.core:jackson-databind         | opensource          | 2.13.4 | 데이터 바인딩을 위해 사용이 됩니다                             |
| com.sun.xml.bind:jaxb-impl                          | opensource          | 4.0.1  | JWT 컨버팅을 위해 사용이 됩니다                             |
| com.sun.xml.bind:jaxb-core                          | opensource          | 4.0.1  | JWT 컨버팅을 위해 사용이 됩니다                             |
| javax.xml.bind:jaxb-api                             | opensource          | 2.4.0  | JWT 컨버팅을 위해 사용이 됩니다                             |
| lombok                                              | spring boot         | -      | Annotation을 이용하는 방법으로 사용이 됩니다.                  |

<br/>
<br/>

## 3. Endpoint

| Endpoint                    | HTTP Method | body                               | 설명                    |
|-----------------------------|-------------|------------------------------------|-----------------------|
| /api/v1/token/generateToken | POST        | { "userId": "adjh54", "userSq": 1} | JWT을 생성하기 위해 사용이 됩니다. |
| /api/v1/user/user           | POST        | { "userId": "adjh54"}              | 로그인을 위해 사용이 됩니다       |

<br/>
<br/>


