# multi-flex-chatgpt

    ⭕️ Spring Boot 기반의 ChatGPT Open API 활용 프로젝트 

⭕️ 가이드 링크

* [Spring Boot 환경에서 ChatGPT API 활용하기 -1 : 정의, 환경구성, 간단한 활용방법](https://adjh54.tistory.com/372)
* [Spring Boot 환경에서 ChatGPT API 활용하기 -2 : 생태계, 레거시, 새로운 모델](https://adjh54.tistory.com/397)

<br/><br/>

## 1. 개발환경

![dacdc261-8c0c-4aa0-9377-718f2d9eda0a](https://github.com/adjh54ir/multi-flex-chatgpt/assets/70501374/dcd52b23-cea1-493e-b47f-4b32f20f39d1)

| 환경 분류                   | 버전      |
|-------------------------|---------|
| JDK                     | Java 17 |
| spring-boot             | 3.2.1   |
| spring-boot-starter-web | 3.2.1   |
| Lombok                  | -       |

<br><br>

## 2. 사용방법

1. src/main/resources/config 패키지가 없다면 구성하며, 해당위치에 application-xxx-local.yml 형태의 파일을 생성합니다.


2. 아래와 OpenAI에서 발급받은 키 값을 넣어줍니다.

 ```java
openai:
secret-key:xxxxx
```

3. application.properties 파일 내에 해당 yml 파일을 참조합니다.

 ```java
spring.profiles.active=multiflex-xxx
```   

<br><br>

## 3. API Endpoint

| End point                                          | HTTP METHOD | 설명                                                                                   |
|----------------------------------------------------|:------------|--------------------------------------------------------------------------------------|
| http://localhost:8000//api/v1/chatGpt/modelList    | GET         | 사용 가능한 모델리스트를 조회합니다                                                                  |
| http://localhost:8000//api/v1/chatGpt/model        | GET         | 유효한 모델인지 확인합니다.                                                                      |
| http://localhost:8000//api/v1/chatGpt/legacyPrompt | POST        | 레거시 모델(gpt-3.5-turbo-instruct, babbage-002, davinci-002)을 사용하여 프롬프트를 입력하고 결과값을 받습니다. |
| http://localhost:8000//api/v1/chatGpt/prompt       | POST        | 새로운 모델(gpt-4, gpt-4 turbo, gpt-3.5-turbo)을 사용하여 프롬프트를 입력하여 결과값을 받습니다.                |

    ⭕️ Request Format

<br/>

### 1. 모델 리스트 조회

![다운로드](https://github.com/adjh54ir/multi-flex-chatgpt/assets/70501374/27c1515a-ff80-4c56-9953-507d7fd91957)

<br/>

### 2. 유효한 모델 조회

![다운로드 (1)](https://github.com/adjh54ir/multi-flex-chatgpt/assets/70501374/51d5e93f-b6e4-4411-b845-a83cd95dff03)

<br/>

### 3. 레거시 모델(gpt-3.5-turbo-instruct, babbage-002, davinci-002) 조회

![다운로드 (2)](https://github.com/adjh54ir/multi-flex-chatgpt/assets/70501374/4b67446b-6874-4039-9407-4835190f839a)

<br/>

### 4. 새로운 모델(gpt-4, gpt-4 turbo, gpt-3.5-turbo) 조회

![다운로드 (3)](https://github.com/adjh54ir/multi-flex-chatgpt/assets/70501374/bc0132c3-7504-4cdc-9be9-e42f091da594)

## 4. Versions

### v0.0.2

* Legacy Model과 Newer Model API 분리 작업 완료
* Open AI Endpoint properties 파일로 분리 작업 완료

<br/>

### v0.0.1

* 초기 개발환경 구축
* RestTemplate을 이용한 간단한 API 통신 방법 구성
* Legacy Model 통신만을 위한 Controller 및 로직 구성