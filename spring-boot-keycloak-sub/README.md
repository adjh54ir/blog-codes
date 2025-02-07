# Spring Boot Keycloak Sub

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

| 환경 분류                          | 버전      |
|--------------------------------|---------|
| JDK                            | Java 17 |
| spring-boot                    | 3.3.7   |
| spring-boot-starter-web        | 3.3.7   |
| spring-cloud-starter-openfeign | 4.1.3   |
| Lombok                         | -       |

<br />
<br />

## 2. OIDC Autentication Flow

| **인증 흐름 종류**                             | **설명**                                                                                                     | **사용처**                                              |
|------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| **Standard Flow**                        | - OAuth 2.0의 Authorization Code Flow 기반으로 인증을 수행하며, 사용자는 Keycloak 로그인 페이지로 리다이렉트 되어서 인증을 수행하는 인증방식을 의미합니다. | 웹 애플리케이션                                             |
| **Direct Access Grants**                 | - 직접 REST API를 통해 자격 증명(아이디/비밀번호)을 담아서 통신하며 별도의 리다이렉션 없이 즉시 토큰을 받을 수 있는 인증방식을 의미합니다.                       | 신뢰할 수 있는 애플리케이션(모바일 애플리케이션, 백엔드 시스템에서 직접 인증이 필요한 경우) |
| **Implicit Flow**                        | - 간소화된 인증흐름으로 인가 코드(Auth Code) 없이 직접 액세스 토큰을 받아서 인증을 수행하는 방식을 의미합니다.                                       | 모바일 앱, 단일 페이지 애플리케이션(SPA)                            |
| **Service Accounts Roles**               | - 애플리케이션 시스템 간에 인증 기반의 통신을 위한 방법으로, 클라이언트 자체적으로 API를 호출하여 다른 서비스와의 통신을 할 때 사용하는 인증방식입니다.                   | 마이크로서비스 간 통신(서버-서버간 통신), 자동화된 프로세스                   |
| **OAuth 2.0 Device Authorization Grant** | - 스마트 TV나 IoT 기기와 같이 제한된 입력 기능을 가진 디바이스를 위한 인증 방식입니다.                                                      | 스마트 TV 애플리케이션, IoT 디바이스, 게임 콘솔, 프린터 및 스캐너            |
| **OIDC CIBA Grant**                      | - 클라이언트가 사용자의 직접적인 상호작용 없이 인증을 시작하는 인증 방식입니다.- 사용자가 인증을 요청한 디바이스와 실제 인증을 수행하는 디바이스가 물리적으로 분리된 방식입니다.       | 금융 서비스, 공유 디바이스, 스마트홈 시스템                            |

<br/>
<br/>

## 3. API Endpoint

| Controller        | EndPoint                                            | HTTP Method | 설명                                                 |
|-------------------|-----------------------------------------------------|-------------|----------------------------------------------------|
| ReceiveController | http://localhost:8081/api/v1/keycloak/receive/token | GET         | Service Accounts Roles 구현방식 중 토큰을 전달받아서 사용자 정보를 조회 |