# Spring Boot Test

    ⭕️ Spring Boot 기반 Junit5, Mokito, MockMVC를 구성한 Repository 입니다.

<br />
<br />

## 1. 구성 가이드

⭕️ 가이드 링크

    💡 해당 프로젝트를 구성하는데 사용한 라이브러리이며 이와 관련된 가이드 문서입니다.

1. [Spring Boot JUnit5 이해하기 -1 : 이론 및 구조](https://adjh54.tistory.com/341)
2. [Spring Boot JUnit5 이해하기 -2 : 환경구성 및 활용 예제](https://adjh54.tistory.com/342)
3. [Spring Boot Mockito 이해하기 : 테스트 흐름 및 사용예시](https://adjh54.tistory.com/346)
4. [Spring Boot MockMvc 이해하기 : 테스트 흐름 및 사용예제](https://adjh54.tistory.com/347)

<br />
<br />

## 2. 개발 환경

      ⭕️ 프로젝트를 구성하면서 사용된 라이브러리 목록입니다.

| 환경 분류                         | 버전      |
|-------------------------------|---------|
| JDK                           | Java 17 |
| spring-boot                   | 3.2.5   |
| spring-boot-starter-web       | 3.2.5   |
| spring-boot-starter-data-jdbc | 3.2.5   |
| mybatis-spring-boot-starter   | 3.0.3   |
| mysql-connector-j             | 8.3.0   |
| junit-jupiter                 | 5.8.1   |
| junit-platform-launcher       | 1.10.2  |
| org.mockito:mockito-core      | 5.8.0   |
| jackson-databind              | 2.16.1  |
| Lombok                        | -       |

## 3. 프로젝트 파일 정보

| 테스트 파일 경로                                   | 분류                           | 설명                                                      |
|---------------------------------------------|------------------------------|---------------------------------------------------------|
| src/test/lifecycle/JUnitLifeCycleTests      | JUnit5 라이프사이클 패턴             | 테스트 클래스 전후 수행 과정을 테스트 합니다.                              |
| src/test/unit/CalculatorTests               | JUnit5 Given-When-Then 패턴    | Given-When-Then 패턴 형태로 테스트를 진행합니다.                      |
| src/test/unit/NestedTests                   | JUnit5 중첩 패턴                 | 여러개의 테스트가 엮여 있는 중첩 패턴 테스트를 진행합니다.                       |
| src/test/service/ServicesTests              | @SpringBootTest 기반 서비스 테스트   | @SpringBootTest 기반으로 DB 데이터를 조회하는 서비스 테스트를 진행합니다.       |
| src/test/mockito/UserServiceMockitoTest     | Mockito 기반 서비스 테스트           | Mockito 기반으로 서비스 수행에 대한 테스트를 진행합니다.                     |
| src/test/mockmvc/UserControllerMockMvcTest1 | Mockmvc 기반 컨트롤러 수행 테스트       | Mockmvc 기반으로 Controller의 API 통신에 대한 테스트를 진행합니다.         |
| src/test/mockmvc/UserControllerMockMvcTest2 | Mockmvc 기반 컨트롤러 수행 + 서비스 테스트 | Mockmvc 기반으로 Controller를 호출하여 Service 수행까지의 테스트를 진행합니다. |

<br />
<br />

## 4. 테스트 상세-1 (Junit5 테스트 : 일반 테스트)

### 1. 클래스/테스트 전후 수행 테스트 : @AfterAll, @BeforeEach, @AfterEach

    ⭕️ src/test/lifecycle/JUnitLifeCycleTests 파일 기준

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/bdba106f-2cac-4f2b-9ce3-c52cf84a0bdf">

<br />
<br />
<br />

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/ae5a54de-b9f5-41ef-a597-8c695e9b3e8d">

<br />
<br />

### 2. Given-When-Then 패턴 테스트 시나리오

    ⭕️ src/test/unit/CalculatorTests 파일 기준

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/ef001d39-fec8-4888-bf79-d8ed3752bc1b">

<br />
<br />
<br />

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/98f59177-5192-4e17-81ca-cae2ebc09d79">

### 3. 중첩 패턴(Nested) 패턴 테스트 시나리오

    ⭕️ src/test/unit/NestedTests 파일 기준

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/6b10906e-b081-417c-9fe1-8a1dc26047c0">


<br />
<br />
<br />

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/b6bb7a86-4176-413d-8a5e-a88f5d8a498c">

<br />
<br />

## 5. 테스트 상세-2 (Junit5 테스트 : 서비스 테스트)

### 1. @SpringBootTest 어노테이션을 사용한 테스트

    ⭕️ src/test/service/ServicesTests 파일 기준

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/7ae9893c-98e4-41df-8922-de67a204741c">

<br />
<br />
<br />

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/de1cbaaf-69f6-4bc7-85e6-ee0c135bc2df">

<br />
<br />
<br />


