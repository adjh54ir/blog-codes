# Spring Boot Slack

    ⭕️ Spring Boot 환경에서 Slack 활용하는 예시를 담은 Repository 입니다.

⭕️ 가이드 링크
<br/>

- [Spring Boot 환경에서 Slack Incoming Webhook 이해하고 구성하기 -1 : 초기 구성 및 간단 메시지 전송](https://adjh54.tistory.com/568)

<br/>
<br/>

## 1. 개발환경

    ⭕️ Spring Boot Async의 경우는 내장된 Spring Framework를 이용하여서 비동기 처리를 수행합니다.

| 환경 분류                          | 버전      |
|--------------------------------|---------|
| JDK                            | Java 17 |
| spring-boot                    | 3.3.3   |
| spring-boot-starter-web        | 3.3.3   |
| com.slack.api:slack-api-client | 1.43.0  |
| Lombok                         | -       |

<br/>
<br/>

## 2. API Endpoint

| Endpoint             | HTTP Method | 설명                                     |
|----------------------|-------------|----------------------------------------|
| api/v1/slack/message | POST        | Slack Channel URL을 기반으로 텍스트 메시지를 전송합니다 |
|                      |             |                                        |