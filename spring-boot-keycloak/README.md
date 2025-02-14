# Spring Boot Keycloak

    ⭕️ Spring Boot 환경에서 Keycloak 인증서버와 연동하여서 이를 활용하는 방법입니다.

<br />

⭕️ 가이드 링크

- [Docker Compose를 이용한 Keycloak 환경 구성 및 실행 방법](https://adjh54.tistory.com/644)
- [Google Cloud Console OAuth 2.0 API 액세스 환경 설정하기](https://adjh54.tistory.com/657)
- [Keycloak 이해하기 -1 : 구성 요소, 인증 처리과정, 주요 기능](https://adjh54.tistory.com/645)
- [Keycloak 이해하기 -2 : SAML/OIDC 프로토콜, 인증 흐름(Authentication flow) 종류](https://adjh54.tistory.com/646)
- [Keycloak 이해하기 -3 : 기본 환경 구성 및 로그인/로그아웃 구현](https://adjh54.tistory.com/647)
- [Keycloak 이해하기 -4 : Keycloak 권한 및 종류](https://adjh54.tistory.com/655)
- [Spring Boot 환경에서 Keycloak 활용하기 -1 : OIDC 인증 흐름 구현(Standard Flow)](https://adjh54.tistory.com/648)
- [Spring Boot 환경에서 Keycloak 활용하기 -2 : OIDC 인증 흐름 구현(Direct Access Grants, Implicit Flow)](https://adjh54.tistory.com/649)
- [Spring Boot 환경에서 Keycloak 활용하기 -3 : OIDC 인증 흐름 구현(Service Accounts Roles)](https://adjh54.tistory.com/654)
- [Spring Boot 환경에서 Keycloak 활용하기 -4 : Identity providers Social 소셜 로그인 구현(Google)](https://adjh54.tistory.com/658)
<br />
<br />

## 1. 개발환경

### 1.1. 프로젝트 개발환경

| 환경 분류                               | 버전       |
|-------------------------------------|----------|
| JDK                                 | Java 17  |
| spring-boot                         | 3.3.7    |
| spring-boot-starter-web             | 3.3.7    |
| spring-boot-configuration-processor | 3.3.7    |
| spring-cloud-starter-openfeign      | 4.1.3    |
| keycloak-admin-client               | 26.0.4   |
| spring-cloud-dependencies           | 2023.0.3 |
| Lombok                              | -        |

<br />
<br />

## 2. API Endpoint

| Controller         | EndPoint                                                                                                                                                                     | HTTP Method | 설명                                            |
|--------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|-----------------------------------------------|
| AuthFlowController | http://localhost:8080/api/v1/authFlow/directAccess                                                                                                                           | POST        | Direct Access Grants Flow : 토큰을 즉시 요청하는 방법    |
| AuthFlowController | http://localhost:8080/api/v1/authFlow/standardFlow                                                                                                                           | GET         | Standard Flow : 로그인 페이지 출력 및 로그인              |
| AuthFlowController | http://localhost:8080/api/v1/authFlow/clientCredentials                                                                                                                      | POST        | Client Credentials : 토큰 발급                    |
|                    |                                                                                                                                                                              |             |                                               |
| KeycloakController | http://localhost:8080/api/v1/keycloak/callback                                                                                                                               | GET         | Keycloak 로그인 이후 리다이렉트 URI                     |
| KeycloakController | http://localhost:8080/api/v1/keycloak/logout                                                                                                                                 | GET         | 특정 Realm의 사용자들을 로그아웃                          |
|                    |                                                                                                                                                                              |             |                                               |
| 서비스 B로 통신          | http://localhost:8081/api/v1/keycloak/receive/token                                                                                                                          | GET         | spring-boot-app에서 발급된 토큰을 spring-boot-sub로 전달 |
|                    |                                                                                                                                                                              |             |                                               |
| Keycloak 통신        | http://localhost:9001/realms/dev-realm/protocol/openid-connect/auth?client_id=spring-boot-app&response_type=code&redirect_uri=http://localhost:8080/api/v1/keycloak/callback | GET         | 로그인 페이지                                       |
| Keycloak 통신        | http://localhost:9001/realms/dev-realm/protocol/openid-connect/logout?redirect_uri=http://localhost:8080/api/v1/keycloak/logout&client_id=spring-boot-app                    | GET         | 로그아웃 페이지                                      |


