# Spring Boot External Network

    ⭕️ Spring Boot를 기반으로 외부 통신을 하는 다양한 방법(RestTemplate, WebClient, Open Feign)에 대해서 알아봅니다.

⭕️ 가이드 링크

- [Spring Boot Web 활용 : RestTemplate 이해하기](https://adjh54.tistory.com/234)
- [Spring Boot Webflux 이해하기 -1 : 흐름 및 주요 특징 이해](https://adjh54.tistory.com/232)
- [Spring Boot Webflux 이해하기 -2 : 활용하기](https://adjh54.tistory.com/233)
- [Spring Cloud OpenFeign 이해하고 활용하기 -1 : 주요 개념 및 환경 구성, 활용 예시](https://adjh54.tistory.com/616)

<br/>
<br/>

## 1. 외부 통신 클라이언트 비교 : RestTemplate, WebClient, OpenFeign

       💡 외부 통신 클라이언트 비교 : RestTemplate, WebClient, OpenFeign
    
    - Spring Boot 환경에서 사용하는 외부 통신 클라이언트로는 RestTemplate, WebClient, OpenFeign를 이용합니다. 각각에 대해서 비교해 봅니다.
    
      1. RestTemplate
      - Spring 3.0부터 제공된 전통적인 HTTP 클라이언트로 동기식으로 데이터 통신이 처리되며 응답을 받을 때까지 블로킹 방식으로 응답을 기다리는 통신이 이루어집니다.
    
      2. WebClient
      - WebFlux의 일부인 Webclient는 비동기적인 방식으로 HTTP 요청을 보내고 응답을 기다리지 않는 논-블로킹 방식으로 통신이 이루어집니다.
    
      3. OpenFeign
      - Spring Cloud의 일부인 OpenFeign는 선언적 REST 클라이언트로, 인터페이스와 어노테이션만으로 HTTP API 클라이언트를 작성하며 동기식으로 데이터 처리되며 응답을 받을 때까지 블로킹 방식으로 응답을 기다리는 통신이 이루어집니다.

| 특징      | RestTemplate                      | WebClient               | OpenFeign                   |
|---------|-----------------------------------|-------------------------|-----------------------------|
| 구현 방식   | '명령형' 방식으로 HTTP 요청을 직접 구현         | '함수형' 방식으로 체이닝 구현       | '선언형' 방식으로 인터페이스만 정의        |
| 코드 복잡도  | URL, HTTP 메서드, 요청/응답 처리를 모두 직접 작성 | 메서드 체이닝으로 직관적 구현        | 어노테이션 기반으로 간단하게 정의          |
| 비동기 지원  | 동기-블로킹 방식만 지원                     | 비동기-논블로킹 방식 지원          | 동기-블로킹 방식만 지원               |
| 유지보수성   | 코드가 길어지고 반복적인 작성 필요               | 모듈화된 코드로 유지보수 용이        | 인터페이스 수정만으로 변경 가능           |
| 테스트 용이성 | MockRestServiceServer 사용 필요       | WebTestClient로 쉽게 테스트   | 인터페이스 기반으로 쉽게 Mock 가능       |
| 오류 처리   | try-catch로 직접 처리 필요               | onError() 등으로 선언적 처리    | ErrorDecoder로 중앙 집중적 처리 가능  |
| 로드밸런싱   | 별도 설정 필요                          | LoadBalancerExchange 통합 | Ribbon과 통합되어 자동 지원          |
| 서킷브레이커  | 별도 구현 필요                          | Resilience4j와 쉽게 통합     | Hystrix/Resilience4j와 쉽게 통합 |

<br/>
<br/>

## 2. 개발환경

    ⭕️ Kotlin의 기본적인 문법을 적용하며 Spring Boot Framework를 이용하여 화면을 구성합니다.

| 환경 분류                          | 버전       |
|--------------------------------|----------|
| JDK                            | Java 17  |
| Kotlin                         | 1.9.25   |
| spring-boot                    | 3.3.5    |
| spring-boot-starter-web        | 3.3.5    |
| spring-boot-starter-webflux    | 3.3.5    |
| jackson-databind               | latest   |
| spring-cloud-starter-openfeign | 4.1.3    |
| spring-cloud                   | 2023.0.3 |
| Lombok                         | -        |

<br/>
<br/>

## 3. API Endpoint

| End point                                                     | HTTP METHOD | 통신방법         | 설명                                 |
|---------------------------------------------------------------|-------------|--------------|------------------------------------|
| http://localhost:8000/api/v1/openFeign/getPosts               | POST        | OpenFeign    | 게시물 전체를 조회합니다.                     |
| http://localhost:8000/api/v1/openFeign/getPost/{postId}       | POST        | OpenFeign    | 특정 게시물을 조회합니다.                     |
| http://localhost:8000/api/v1/openFeign/getPostsFilter         | POST        | OpenFeign    | 필터링된 게시물 전체를 조회합니다.                |
| http://localhost:8000/api/v1/openFeign/getPostFilter/{postId} | POST        | OpenFeign    | 필터링된 특정 게시물을 조회합니다.                |
| http://localhost:8000/api/v1/openFeign/getTodos               | POST        | OpenFeign    | 인증이 필요한 할일 목록을 조회합니다.              |
| http://localhost:8000/api/v1/openFeign/getTodo/{postId}       | POST        | OpenFeign    | 인증이 필요한 특정 할일을 조회합니다.              |
| http://localhost:8000/api/v1/restTemplate/getPost             | POST        | RestTemplate | RestTemplate을 이용한 외부 통신을 수행합니다.    |
| http://localhost:8000/api/v1/webClient/getPosts               | GET         | WebClient    | WebClient를 이용해 게시물 전체를 조회합니다.      |
| http://localhost:8000/api/v1/webClient/getPostById/{id}       | GET         | WebClient    | WebClient를 이용해 특정 게시물을 조회합니다.      |
| http://localhost:8000/api/v1/webClient/getPostByUserId        | GET         | WebClient    | WebClient를 이용해 특정 사용자의 게시물을 조회합니다. |
| http://localhost:8000/api/v1/webClient/getPostList            | POST        | WebClient    | WebClient를 이용해 게시물 목록을 조회합니다.      |



