# spring-boot-webflux

    ⭕️ 반응형 프로그래밍(Reactive Programming)을 구축하기 위한 Spring WebFlux를 활용하는 예시들을 구성한 Repository입니다.

<br />
<br />

🔗 가이드 링크

- [Spring Boot Webflux 이해하기 -1 : 흐름 및 주요 특징 이해](https://adjh54.tistory.com/232)
- [Spring Boot Webflux 이해하기 -2 : 활용하기](https://adjh54.tistory.com/233)
- [반응형 프로그래밍(Reactive Programming), Spring WebFlux를 활용하여 구현하기-1 : 전반적인 흐름](https://adjh54.tistory.com/627)

## 1. 개발환경

    ⭕️ 반응형 프로그래밍(Reactive Programming)을 구축하기 위한 개발환경을 구축합니다.

| 환경 분류                          | 버전            |
|--------------------------------|---------------|
| JDK                            | 17            |
| spring-boot                    | 3.3.6         |
| spring-boot-starter-webflux    | 3.3.6         |
| spring-boot-starter-data-r2dbc | 3.3.6         |
| spring-boot-starter-mail       | 3.3.6         |
| postgresql:r2dbc-postgresql    | 1.0.7.RELEASE |
| reactor-test                   | 3.7.1         |
| spring-boot-starter-test       | 3.3.6         |
| Lombok                         | -             |

<br/> 

    ⭕️ build.gradle 파일 추가

<br/>

    ext {
        set('bootVer', "3.3.6")
        set('jacksonVer', "2.16.1")
    }   

    dependencies {
        // Spring Boot Starter
        implementation "org.springframework.boot:spring-boot-starter-webflux:${bootVer}"        // Webflux
        implementation "org.springframework.boot:spring-boot-starter-data-r2dbc:${bootVer}"     // R2DBC
        implementation "org.springframework.boot:spring-boot-starter-mail:${bootVer}"                      // Mail
    
        // OpenSource
        implementation 'org.postgresql:r2dbc-postgresql:1.0.7.RELEASE'                          // R2DBC - PostgresSQL
        implementation "com.fasterxml.jackson.core:jackson-databind:${jacksonVer}"              // Jackson Databind
    
        // Runtime & Compile & test
        runtimeOnly 'org.postgresql:postgresql'                                                 // Postgres
        compileOnly 'org.projectlombok:lombok'                                                  // Lombok
    
        annotationProcessor 'org.projectlombok:lombok'                                          // Lombok
        testImplementation 'io.projectreactor:reactor-test:3.7.1'                               // Reactor Test
        testImplementation 'org.springframework.boot:spring-boot-starter-test'                  // JUnit
        testRuntimeOnly 'org.junit.platform:junit-platform-launcher'                            // JUnit
    }

<br/>
<br/>

## 2. 계층 구조

| 계층(Layer)                 | 주요 특징                                                                                                                                                                                              |
|---------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 표현 계층(Presentation Layer) | - 함수형 방식의 경우는 Router Functions와 Handler Functions이 위치합니다. <br/> -  주석 기반 방식(annotation-based)에서는 Controller가 해당 계층에 위치합니다.  <br/> - HTTP 요청을 받아 적절한 비즈니스 로직으로 전달 <br/>- 클라이언트와의 직접적인 통신 처리를 수행합니다. |
| 비즈니스 계층(Service Layer)    | - 실제 비즈니스 로직 구현 <br/> - 트랜잭션 관리 및 도메인 규칙 적용 <br/> - Reactive 스트림을 활용한 비동기 처리                                                                                                                       |
| 영속성 계층(Persistence Layer) | - R2DBC나 Reactive MongoDB 등을 통한 데이터 접근 <br/> - Reactive 리포지토리 패턴 구현 <br/> - 비동기 데이터 CRUD 연산 처리                                                                                                     |
| 도메인 계층(Domain Layer)      | - 비즈니스 엔티티와 값 객체 정의 <br/> - 도메인 이벤트와 규칙 관리 <br/> - Reactive 스트림과 호환되는 도메인 모델 구현                                                                                                                    |

<img alt="layer" src="https://github.com/user-attachments/assets/0ef1084c-7ad9-46ea-9289-83b9fa7c1d7b"/>

<br/>
<br/>

## 3. 처리과정

    💡 구성 시나리오
    
        - 해당 시나리오는 비동기 병렬 처리를 위한 비즈니스 로직 처리 과정입니다.
        - 시나리오에서는 임의의 사용자를 10명을 등록하여 비동기 API 통신을 확인합니다.
        - 해당 등록을 위해서 사용자 아이디 조회 → 사용자 등록 → 이메일 전송 처리를 수행합니다.
    
      1. UserServiceTest → RouterConfig
    
         - Test 클래스 내에서는 WebClient를 통해 사용자를 등록하는 API 호출을 수행합니다. 해당 과정에서 총 10번의 사용자 등록을 하도록 호출하였습니다.
    
      2. RouterConfig → UserHandler
    
        - 호출된 엔드포인트를 기반으로 처리를 위한 UserHandler의 registerUser()메서드를 호출합니다.
    
      4. UserHandler → UserService
    
        - UserHandler에서는 UserService를 호출하고 처리과정중 성공을 하면 “1”을 반환하고 에러가 발생하면 “0”을 반환합니다.
    
      5. UserSerivce → UserServiceImpl
    
        - UserServiceImpl 구현체에서 인터페이스의 비즈니스 로직을 처리합니다.
        - 비즈니스 로직은 사용자 아이디를 기반으로 사용자 조회 → 중복 체크를 통과하면 사용자 등록 → 사용자 등록이 완료되면 이메일을 전송하는 처리과정을 비동기로 처리를 수행합니다.
    
      6. UserServiceImpl → UserRepository
    
        - 비즈니스 로직 처리중 데이터 처리를 위해서 UserRepository를 호출합니다.
    
      7. UserRepository → ReactiveCrudRepository
    
        - ReactiveCrudRepository로 부터 상속을 받아서 구현합니다. 이를 통해 데이터베이스에 접근하여 비동기적 데이터를 조회합니다.
    
      8. 해당 처리가 완료되면 최종적으로 처리된 값을 반환받습니다.

<img alt="process" src="https://github.com/user-attachments/assets/8371aa66-6384-4a13-8be2-954d295fdb29"/>


<br/>
<br/>

## 4. 결과화면

### 4.1. 스레드 로그 확인

    💡 스레드 로그 확인
    - 로그를 보면 여러 개의 서로 다른 actor-tcp-nio 스레드(1부터 10까지)가 동시에 같은 SQL 문을 실행하고 있음을 확인할 수 있습니다.
    - 이는 Spring WebFlux의 비동기-논블로킹 특성을 보여주는 것으로, 하나의 요청이 순차적으로 처리되는 것이 아니라 여러 스레드가 동시에 병렬로 처리되고 있다는 것을 의미합니다.

<img src="https://github.com/user-attachments/assets/c825b1ac-b20e-4b6e-8e7f-c7afeaddc7d0"/>


<br/>

### 4.2. 비동기 병렬 처리 수행

    💡 비동기 병렬 처리 수행

    - 아래의 로그를 확인하더라도 ‘일괄적’으로 SELECT 작업을 수행하고 INSERT 작업을 수행하는 하고 있지는 않습니다.
    - 각각 스레드별로 맞게 SELECT를 수행하고 INSERT를 수행하고 있음을 확인할 수 있습니다.

<img src="https://github.com/user-attachments/assets/d4878f4c-7905-470d-b91f-c20cf0e2423c">