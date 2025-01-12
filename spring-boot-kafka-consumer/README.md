## spring-boot-kafka-consumer

    ⭕️ Spring Boot Kafka를 활용하여 소비자(Consumer)를 구성합니다.

<br />

⭕️ 가이드 링크

- [Docker 기반 Apache Kafka 환경 구축하기](https://adjh54.tistory.com/637)
- [Apache Kafka 이해하기 -1: 주요 모델 및 구성요소](https://adjh54.tistory.com/636)
- [Apache Kafka 이해하기 -2 : Zookeeper, KRaft(Kafka Raft), 비교](https://adjh54.tistory.com/639)
- [Spring Boot Kafka 이해하고 활용하기 -1 : 주요 특징, 활용 사례, 클래스/메서드](https://adjh54.tistory.com/640)
- [Spring Boot Kafka 이해하고 활용하기 -2 : 환경 구성 및 사용 예시](https://adjh54.tistory.com/641)
- [Spring Boot Kafka 이해하고 활용하기 -1 : 주요 특징, 활용 사례, 클래스/메서드](https://adjh54.tistory.com/640)

<br />
<br />

## 1. Kafka 소비자(Consumer)

    💡 소비자(Consumer)

    - Kafka Topic으로부터 메시지를 구독(Subscribe)하고 읽어오는 애플리케이션이나 서비스를 의미합니다.
    - 하나 이상의 토픽을 구독(Subscribe)하여 메시지를 지속적으로 소비합니다. 구독 과정에서 특정 토픽의 파티션에 할당되어 메시지를 읽어옵니다.
    - Consumer Group을 통해 여러 Consumer가 협력하여 병렬 처리가 가능합니다.

<img src="https://github.com/user-attachments/assets/35320174-8e59-4199-a133-e4a0b72896ae" />

| **기능**                | **설명**                                                   | **세부 사항**                                                                      |
| ----------------------- | ---------------------------------------------------------- | ---------------------------------------------------------------------------------- |
| **메시지 소비**         | 토픽으로부터 메시지를 읽어오는 기본적인 기능을 수행합니다. | - 자동/수동 커밋 지원- 오프셋 관리                                                 |
| **Consumer Group**      | 여러 Consumer를 그룹화하여 병렬 처리를 가능하게 합니다.    | - 파티션당 하나의 Consumer 할당- 자동 로드 밸런싱- Failover 지원                   |
| **오프셋 관리**         | 메시지 소비 위치를 추적하고 관리합니다.                    | - earliest: 가장 처음부터 소비- latest: 최신 메시지부터 소비- 특정 오프셋부터 소비 |
| **재조정(Rebalancing)** | Consumer Group 내에서 파티션을 재분배합니다.               | - Consumer 추가/제거시 자동 재조정- 장애 발생시 자동 복구                          |

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

| 상위 디렉터리 | 파일 명                | 설명                                                                              |
| ------------- | ---------------------- | --------------------------------------------------------------------------------- |
| config        | KafkaConfig            | Kafka 연결 설정을 관리하는 관리파일입니다.                                        |
| component     | KafkaConsumerComponent | Kafka 생산자(Producer)로 부터 생성된 Topic 내의 메시지를 수신하는 컴포넌트입니다. |
| service       | KafkaConsumerService   | Kafka 메시지를 전달 받아서 처리하는 비지니스 로직 서비스입니다.                   |
