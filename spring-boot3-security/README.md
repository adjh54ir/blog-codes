# spring-boot3-security

    ⭕️ Spring Boot 3.3.4 버전 기준으로 JWT를 활용한 로그인 구현 예시 Repository

## 1. 참고 사이트

⭕️ [Spring Boot Security 3.x + JWT 이해하기 -1 : 구조 및 Client / Server 처리과정](https://adjh54.tistory.com/576)  
⭕️ [Spring Boot Security 3.x + JWT 이해하기 -2 : 환경설정 및 구성](https://adjh54.tistory.com/577)


<br/>
<br/>

## 2. Architecture

----

### 2.1. Spring Security + JWT 처리과정: Client 관점

    ⭕️ Spring Security + JWT 처리과정: Client 관점
    
    1. Client는 로그인을 수행합니다.
    
    - 사용자 아이디, 비밀번호를 기준으로 로그인을 수행합니다.
    

    2. Client는 API Server로 API 호출 시 사용자 정보를 함께 전달하여 ‘인증’을 요청합니다 : 인증(Authentication)
        
    - 사용자 아이디, 비밀번호를 기준으로 API Server로 호출을 하면 데이터베이스를 조회하여 실제 인증이 된 회원인지 여부를 확인합니다.
    

    3. API Server에서 클라이언트에게 인증 결과 값을 반환해 줍니다.
    
    - 데이터베이스 조회 결과를 통해서, 회원 여부에 따라 맞는 응답 결과를 반환해 줍니다.
    

    4. 로그인 성공 시 '인가(권한 부여)'로 JWT를 발급하며, 조회된 사용자 정보를 응답 값으로 전달합니다.
    
    - 로그인 성공 시 JWT와 사용자 정보를 함께 반환해 주고, 실패인 경우에는 에러 코드 및 메시지를 전달하며, 다시 로그인을 요청합니다.
    

    5. 클라이언트는 전달받은 JWT 내부 스토리지에 저장합니다.
    
    - 발급받은 JWT를 유지하며, 이후 API 호출마다 이를 Header에 포함하여 전달하기 위해 저장해 둡니다.
    

    6. 클라이언트가 API Server로 API 통신 시 JWT를 Header에 추가하여 호출합니다.
    
    - 이를 통해 인가받은 사용자만 API 통신을 수행할 수 있음을 확인합니다.

<img src="https://github.com/user-attachments/assets/0a6b8dce-7843-452c-b52c-871879577cb1" alt="" />


<br />
<br />

### 2.2. Spring Security + JWT 처리과정 : API Server

    ⭕️ Spring Security + JWT 처리과정 : API Server
    
        - 클라이언트의 호출 이후 내부적으로 처리되는 API Server의 과정에 대해 확인합니다.
    
      1. 클라이언트의 API 호출 : 엔드포인트(api/v1/user/login)
      
        - 클라이언트는 사용자 아이디, 비밀번호를 기반으로 API 호출을 수행합니다.
        
      2. 서버 내에서 해당 엔드포인트에 대한 감지 하여 이를 처리합니다. : CustomAuthenticationFilter
      
        - CustomAuthenticationFilter 내에서 사전에 지정한 엔드포인트를 통해서 수행처리 합니다.
    
      3. 감지 이후 Filter에서 우선적으로 감지하여 해당 엔드포인트로 호출되는 정보를 조회합니다. : JwtAuthorizationFilter
    
        - 3.1. JWT가 필요가 없는 URL인 경우는 다음 필터를 수행하도록 처리가 됩니다.
        - 3.2. JWT가 필요한 경우는 JWT에 대한 존재 및 유효성을 검증합니다.
    
      4. 사용자 아이디/비밀번호를 감지하여 전달합니다.: CustomAuthenticationFilter
        
        - CustomAuthenticationFilter 내에서 사용자 아이디/비밀번호 값을 CustomAuthenticationProvider로 전달을 합니다.
    
      5. 전달받은 값을 통해 데이터베이스 내의 사용자 정보를 조회하여 사용자 여부를 확인합니다. : CustomAuthenticationProvider
      
        - 데이터베이스를 호출하여 사용자를 조회하여 실제 사용자 여부인지 여부를 성공(CustomAuthSuccessHandler)과 실패(CustomAuthFailureHandler)로 전달을 합니다.
    
      6. 이전과정에서 사용자 여부가 비교되어서 상황에 따른 처리를 수행합니다
    
        - 6.1. 성공 시, 조회된 사용자 정보와 토큰을 발급하여 클라이언트에게 전달합니다.    
        - 6.2. 실패 시, 클라이언트에게 오류 코드와 오류 메시지를 전달합니다.

  <img src="https://github.com/user-attachments/assets/b947a0f8-cc39-4fca-801b-ec4d1370225e" alt=""/>

<br/>
<br/>

## 3. 개발 환경

### 3.1. 프로젝트 환경

    ⭕️ 해당 프로젝트를 구성한 개발 환경입니다.

| 개발환경                                                | 분류                  | 버전     | 설명                         |
|-----------------------------------------------------|---------------------|--------|----------------------------|
| java jdk                                            | java version        | 17     | Java 개발 키트                 |
| spring-boot                                         | spring boot starter | 3.3.4  | Spring Boot 프레임워크          |
| spring-boot-web                                     | spring boot starter | 3.3.4  | Spring Boot 웹 애플리케이션 개발 지원 |
| spring-boot-security                                | spring boot starter | 3.3.4  | Spring Security 통합         |
| org.mybatis.spring.boot:mybatis-spring-boot-starter | spring boot starter | 3.0.3  | MyBatis와 Spring Boot 통합    |
| io.jsonwebtoken:jjwt                                | opensource          | 0.12.6 | JSON Web Token 생성 및 검증     |
| com.fasterxml.jackson.core:jackson-databind         | opensource          | 2.16.1 | JSON 데이터 바인딩 라이브러리         |
| jakarta.xml.bind:jakarta.xml.bind-api               | opensource          | 4.0.2  | XML 바인딩 API                |
| org.postgresql:postgresql                           | opensource          | -      | PostgreSQL JDBC 드라이버       |
| lombok                                              | spring boot         | -      | Java 코드 생성 라이브러리           |

<br />
<br />

### 3.2. 구성 패키지 구조 및 파일 설명

    ⭕️ 구성 패키지 구조 및 파일 설명
    
    - Spring Boot Security 3.4.3 버전 + JWT 0.12.6 버전을 활용하여 구성한 프로젝트의 패키지 구조 및 설명입니다.

<img src="https://github.com/user-attachments/assets/8f094b6c-7a11-4470-9ab2-732ed1d8097d" alt="">

| 패키지                | 파일 명                         | 설명                                                                          |
|--------------------|------------------------------|-----------------------------------------------------------------------------|
| common.utils       | TokenUtils                   | - JWT의 구성요소를 생성하고 최종적으로 JWT를 생성하여 유효성을 체크하는 유틸입니다.                          |
| config             | DataSourceConfig             | - 데이터베이스와 연결 및 SQL Mapper(MyBatis)와의 연결을 담당하는 설정 클래스입니다.                    |
| config             | WebConfig                    | - HTTP 통신에서 CORS, Header에 대한 설정을 담당하는 설정 클래스입니다.                            |
| config             | WebSecurityConfig            | - Spring Security 환경 설정을 구성하기 위한 설정 클래스입니다.                                 |
| config.filter      | CustomAuthenticationFilter   | - 아이디와 비밀번호 기반의 데이터를 Form 데이터로 전송을 받아 '인증'을 담당하는 필터 역할의 클래스입니다.             |
| config.filter      | HeaderFilter                 | - HTTP 응답에 CORS(Cross-Origin Resource Sharing) 관련 헤더를 설정하는 필터 역할의 클래스입니다.   |
| config.filter      | JwtAuthorizationFilter       | - 지정한 URL 별 JWT 유효성 검증을 수행하며 직접적인 사용자 '인증'을 확인하는 필터 역할의 클래스입니다.             |
| config.handler     | CustomAuthenticationProvider | - 전달받은 사용자의 아이디와 비밀번호를 기반으로 비즈니스 로직을 처리하여 사용자의 ‘인증’에 대해서 검증을 수행하는 클래스입니다.   |
| config.handler     | CustomAuthFailureHandler     | - 사용자의 '인증'에 대해 실패하였을떄, 수행하여 사용자에게 응답 값을 제공해주는 Handler입니다.                  |
| config.handler     | CustomAuthSuccessHandler     | - 사용자의 '인증'에 대해 성공하였을때, 수행하여 사용자에게 사용자 정보 및 JWT에 대한 응답 값을 제공해주는 Handler입니다. |
| config.interceptor | JwtTokenInterceptor          | - API 요청 시 사전에 토큰에 대한 정합성을 체크하는 인터셉터 역할을 하는 클래스입니다.                         |
| controller         | TokenController              | - 토큰 생성 테스트를 위해 임시로 구성한 Controller                                          |
| controller         | UserController               | - 로그인 테스트를 위해 임시로 구성한 Controller                                            |
| mapper             | UserMapper                   | - 사용자 관련 SQL Mapper 인터페이스 입니다.                                              |
| model.dto          | UserDetailsDto               | - Spring Security에서 사용되는 UserDetailsDto 클래스 입니다.                            |
| model.dto          | UserDto                      | - tb_user 테이블과 매핑되는 DTO 클래스입니다.                                             |
| service            | UserService                  | - 사용자 서비스 인터페이스입니다.                                                         |
| service.impl       | UserDetailsServiceImpl       | - 사용자 인증 정보를 로드하고 UserDetails 객체를 생성하는 역할을 담당                               |
| service.impl       | UserServiceImpl              | - 사용자 서비스의 구현체 클래스입니다.                                                      |