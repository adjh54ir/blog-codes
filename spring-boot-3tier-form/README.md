# Spring Boot 3-Tier Form

    ⭕️ Spring Boot 기반의 3-Tier-Architecture 기반으로 구성된 API 구성 Form

<br />
<br />

## 1. 구성 가이드

⭕️ 가이드 링크 : 아키텍처

    💡 해당 프로젝트를 구성하는데 사용한 가이드 문서입니다.

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


<br />

⭕️ 가이드 링크 : 적용 기술

    💡 해당 프로젝트를 구성하는데 사용한 라이브러리이며 이와 관련된 가이드 문서입니다.

| 적용 기술          &nbsp;           | 기술 내용                                                                   | 관련 글          &nbsp;&nbsp;           |
|---------------------------------|-------------------------------------------------------------------------|--------------------------------------|
| MyBatis + PostgreSQL            | Spring Boot 내에서 MyBatis & PostgreSQL 연동하기                               | [링크](https://adjh54.tistory.com/65)  |
| log4jdbc-log4j2                 | MyBatis Query Formatter 이해하고 적용하기 : log4jdbc-log4j2 4.1                 | [링크](https://adjh54.tistory.com/228) |
| Spring Boot JDBC + HikariCP     | HikariCP 이해하고 적용하기 (with. MyBatis)                                      | [링크](https://adjh54.tistory.com/73)  |
| Spring Boot Log4j2              | Spring Boot Log4j2 이해하기 -1 : 주요 특징, 구성 요소, yml 설정방법                     | [링크](https://adjh54.tistory.com/388) |
| Spring Boot Access Log          | Spring Boot Tomcat Access Log 이해하고 설정하기                                 | [링크](https://adjh54.tistory.com/127) |
| Spring Boot Swagger: openAPI UI | Swagger 이해하고 적용하기 : SpringDoc openAPI UI                                | [링크](https://adjh54.tistory.com/72)  |
| Spring Boot Aop                 | Spring Boot AOP(Aspect-Oriented Programming) 이해하고 설정하기                  | [링크](https://adjh54.tistory.com/133) |
| Spring Boot Validation          | Spring Boot Validation 이해하기 : 데이터 유효성 검증                                | [링크](https://adjh54.tistory.com/77)  |
| Spring Boot Devtool             | Spring Boot Devtools 이해하고 설정하기                                          | [링크](https://adjh54.tistory.com/62)  |
| Lombok                          | Lombok 이해하고 적용하기 -1 : 설정 및 기초                                           | [링크](https://adjh54.tistory.com/71)  |
| Lombok                          | Lombok 이해하고 적용하기 -2 : 심화 및 적용                                           | [링크](https://adjh54.tistory.com/152) |
| Lombok                          | 생성자 패턴 - Builder() 심화 속성 이해하기 -1                                        | [링크](https://adjh54.tistory.com/96)  |
| jackson : databind              | Spring Boot 환경에서 Jackson 모듈 활용하기 : JSON 파싱, 직렬화, 역 직렬화, JSON 파일 읽어오기/생성 | [링크](https://adjh54.tistory.com/375) |

<br />
<br />

## 2. 웹 서버 프로젝트 구조

      ⭕️ 해당 프로젝트 구조는 애플리케이션 3-Tier Architecture 구조로 구성되어 있습니다.  
      ⭕️ 웹 API 서버의 역할만을 수행하는 구조입니다.

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/05206d36-248b-4cff-b7de-6132f79488c2">

1. 응용 계층(Application Layer) : Controller
    - 애플리케이션 서버로 들어오는 '요청'과 '응답'을 반환해주는 계층으로 수행이 됩니다.
    - 클라이언트는 JSON / XML 형태의 데이터 구조 요청에 따라 해당 처리 이후 값을 반환해주는 역할을 수행합니다.

<br />

2. 비즈니스 계층(Business Layer) : Service
    - 서버 내에서 비즈니스 로직을 처리하는 계층으로 수행이 됩니다.
    - 데이터 계층으로부터 전달 받은 데이터를 기반으로 비즈니스 로직을 처리하여 최종적으로 응용계층(Controller)로 전달하는 역할을 수행합니다.

<br />

3. 데이터 계층(Data Layer : Data Access, Database) : DAO, Database
    - 데이터베이스와 데이터베이스에 접근(Data Access)하여 데이터 처리하는 계층으로 수행이 됩니다.
    - 데이터베이스에 접근하여 데이터를 가져와 추가 데이터 조작을 수행하여 최종적으로 비즈니스 계층(Server)로 전달하는 역할을 수행합니다.
    - MyBatis를 이용하여 Dao와 Mapper간의 매핑을 통해서 객체(Object) 형태로 데이터를 반환 받습니다.

<br />
<br />

## 3. 개발 환경

      ⭕️ 프로젝트를 구성하면서 사용된 라이브러리 목록입니다.

| 환경 분류                                       | 버전      |
|---------------------------------------------|---------|
| JDK                                         | Java 17 |
| spring-boot                                 | 3.2.5   |
| spring-boot-starter-jdbc                    | 3.2.5   |
| spring-boot-starter-validation              | 3.2.5   |
| spring-boot-starter-log4j2                  | 3.2.5   |
| spring-boot-starter-aop                     | 3.2.5   |
| spring-boot-starter-test                    | 3.2.5   |
| spring-boot-starter-configuration-processor | 3.2.5   |
| Lombok                                      | -       |
| mybatis-spring-boot-starter                 | 3.0.3   |
| org.postgresql:postgresql                   | 42.7.3  |
| log4jdbc-log4j2-jdbc4.1                     | 1.16.0  |
| jackson-databind                            | 2.16.1  |
| jackson-dataformat-yaml                     | 2.16.1  |

<br />
<br />

## 4. 사용방법

    ⭕️ 해당 프로젝트는 Java JDK 17, Gradle, PostgreSQL 환경에서 수행이 됩니다.

1. Java JDK 설정 : 아래의 링크를 통해서 Java JDK를 설정합니다.  
   [참고] [MacOS 환경에서 Java JDK 설정 및 변경하기 : homebrew, 다운로드 파일](https://adjh54.tistory.com/216)  
   (*** 기존의 사용중인 JDK가 있으시면 IntelliJ IDE 툴 내에서는 아래의 링크를 통해서 변경하시면 됩니다.
   <br />
   [참고] [IntelliJ에서 JDK 버전 변경 방법](https://adjh54.tistory.com/355)

   <br />

2. 로컬 PostgreSQL 설정 : 로컬 데이터베이스로 PostgreSQL을 이용하여 구성됩니다. 이를 설정합니다.  
   [참고] [MacOS에서 PostgreSQL 로컬 데이터베이스 구성 방법](https://adjh54.tistory.com/80)

   <br />
3. Gradle Build : build.gradle 파일을 기준으로 라이브러리가 설치됩니다.

   <br />

4. 로컬환경에서 사용하기 위해서는 resources/config/application-spring-boot-local.yml 파일을 수정합니다

- postgreSQL 접속 정보 수정

   <br />

5. Spring Boot Server Start : 개발 API 서버가 실행됩니다.

<br />
<br />

## 5. API Endpoint

| End point                               | HTTP METHOD | 설명              |
|-----------------------------------------|:------------|-----------------|
| http://localhost:8000/api/v1/user/users | POST        | 사용자 리스트를 조회합니다. |
| http://localhost:8000/api/v1/user/user  | POST        | 사용자를 등록합니다.     |
| http://localhost:8000/api/v1/user/user  | PUT         | 사용자를 수정합니다.     |
| http://localhost:8000/api/v1/user/user  | DELETE      | 사용자를 삭제합니다.     |









