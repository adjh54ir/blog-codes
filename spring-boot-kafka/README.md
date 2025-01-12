## spring-boot-kafka

    ⭕️ Spring Boot Kafka를 활용하여 생성자(Producer)를 구성합니다.

<br />

⭕️ 가이드 링크

<br />

- [Docker 기반 Apache Kafka 환경 구축하기](https://adjh54.tistory.com/637)
- [Apache Kafka 이해하기 -1: 주요 모델 및 구성요소](https://adjh54.tistory.com/636)
- [Apache Kafka 이해하기 -2 : Zookeeper, KRaft(Kafka Raft), 비교](https://adjh54.tistory.com/639)
- [Spring Boot Kafka 이해하고 활용하기 -1 : 주요 특징, 활용 사례, 클래스/메서드](https://adjh54.tistory.com/640)
- [Spring Boot Kafka 이해하고 활용하기 -2 : 환경 구성 및 사용 예시](https://adjh54.tistory.com/641)
- [Spring Boot Kafka 이해하고 활용하기 -1 : 주요 특징, 활용 사례, 클래스/메서드](https://adjh54.tistory.com/640)

<br />
<br />

## 1. Kafka 생성자(Producer)

    💡 Kafka 생성자(Producer)

    - Kafka에서 메시지를 생성하고 발행하는 애플리케이션이나 서비스를 의미합니다.
    - 동기/비동기 전송을 지원하며, 다양한 설정으로 성능과 신뢰성을 조절할 수 있습니다.
    - 생성된 메시지를 토픽의 특정 파티션에 분배하는 역할을 합니다

<img src="https://github.com/user-attachments/assets/8f780ff7-8e7f-4c07-85e6-0ae7102969cf" />

<br/>

| **기능**        | **설명**                                                          | **세부 사항**                                                                                                                       |
| --------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| **메시지 발행** | 토픽에 데이터를 전송하는 역할을 담당합니다.                       |                                                                                                                                     |
| **파티션 할당** | 메시지를 특정 토픽의 파티션에 분배하는 역할을 합니다.             | - Round-Robin: 순차적으로 파티션에 할당 - Key-based: 메시지 키를 기반으로 특정 파티션에 할당 - Custom: 사용자 정의 파티션 전략 사용 |
| **신뢰성 보장** | 메시지 전송의 신뢰성 수준을 설정합니다.                           | - acks=0: 메시지 전송 후 응답을 기다리지 않음- acks=1: 리더 파티션의 응답만 기다림- acks=all: 모든 복제본의 응답을 기다림           |
| **배치 처리**   | 성능 향상을 위해 여러 메시지를 묶어서 한 번에 전송할 수 있습니다. |                                                                                                                                     |

<br />
<br />

## 2. 개발환경

### 2.1. 프로젝트 개발환경

| 환경 분류               | 버전    |
| ----------------------- | ------- |
| JDK                     | Java 17 |
| spring-boot             | 3.3.7   |
| spring-boot-starter-web | 3.3.7   |
| spring-kafka            | 3.3.1   |
| Lombok                  | -       |

<br />
<br />

### 2.2. 디렉터리 구조

| 상위 디렉터리 | 파일 명                 | 설명                                                                            |
| ------------- | ----------------------- | ------------------------------------------------------------------------------- |
| config        | KafkaConfig             | Kafka 연결 설정을 관리하는 관리파일입니다.                                      |
|               | TopicConfig             | Kafka Topic을 생성하는 설정 파일입니다.                                         |
| controller    | KafkaProducerController | REST API 호출을 받아서 Kafka 메시지를 생성하는 생성자(Producer) 컨트롤러입니다. |
| service       | KafkaProducerService    | Kafka 메시지를 전달 받아서 처리하는 비지니스 로직 서비스입니다.                 |

<br />
<br />

## 2. API Endpoint

| **HTTP Method** | **Endpoint**                                   | **설명**                         |
| --------------- | ---------------------------------------------- | -------------------------------- |
| POST            | /api/v1/kafka/messages                         | 기본 메시지를 토픽으로 전송      |
| POST            | /api/v1/kafka/messages/withKey                 | 키와 함께 메시지를 토픽으로 전송 |
| POST            | /api/v1/kafka/messages/toPartition/{partition} | 특정 파티션으로 메시지를 전송    |
| POST            | /api/v1/kafka/messages/async                   | 비동기 방식으로 메시지를 전송    |
