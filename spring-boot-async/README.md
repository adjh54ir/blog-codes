# Spring Boot Async

    ⭕️ Spring Boot 기반의 Java 언어를 이용하여 비동기 처리를 위한 방법에 대한 예시들을 구성한 Repository입니다.

<br/>

🔗 가이드 링크

- [Spring Boot Async 비동기 처리 이해하기 -1 : 주요 어노테이션 및 비동기 반환 유형](https://adjh54.tistory.com/544)
- [Spring Boot Async 비동기 처리 이해하기 -2 : Executor](https://adjh54.tistory.com/547)

<br/>
<br/>

## 1. 개발환경

    ⭕️ Spring Boot Async의 경우는 내장된 Spring Framework를 이용하여서 비동기 처리를 수행합니다.

| 환경 분류                  | 버전    |
| -------------------------- | ------- |
| JDK                        | Java 17 |
| spring-boot                | 3.2.5   |
| spring-boot-starter-web    | 3.3.1   |
| spring-boot-starter-quartz | 3.3.1   |
| spring-boot-starter-test   | 3.3.1   |
| Lombok                     | -       |

<br/>
<br/>

## 2. 비동기의 이해 : 반환타입

    ⭕️ Spring Boot Async의 경우는 내장된 Spring Framework를 이용하여서 비동기 처리를 수행합니다.

### 1. 비동기 반환 타입이 존재하지 않는 경우 : void

```java
/**
 * [Async] 반환 유형이 존재하지 않는 경우 : void
 */
@Async
@Override
public void asyncVoidType() {
    System.out.println("Execute method asynchronously. :: " + Thread.currentThread().getName());
}
```

<br/>
<br/>

### 2. 비동기 반환 타입이 존재하는 경우 : Future

    ⭕️ Spring Boot Async의 경우는 내장된 Spring Framework를 이용하여서 비동기 처리를 수행합니다.

<img src="https://github.com/user-attachments/assets/eea9de12-8810-4653-bf1b-2ec2b4445b0f">

| 클래스            | 설명                                                                                   | 주요 기능                                                                                                         | 단점                                                                                        |
| ----------------- | -------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| Future            | 비동기 작업의 결과를 나타내는 객체입니다.                                              | get 메서드를 통해 결과를 얻을 수 있습니다. 작업 완료 전까지 get 호출 시 블로킹됩니다.                             | get 메서드 호출 시 블로킹될 수 있으며, 콜백 기능이 없습니다.                                |
| ListenableFuture  | Future의 확장으로, 비동기 작업 완료 시 콜백을 등록할 수 있는 기능을 제공합니다.        | 콜백을 통해 비동기 작업 완료 시 특정 작업을 수행할 수 있으며, 효율적인 비동기 프로그래밍이 가능합니다.            | 콜백 등록 시 주의가 필요하며, Future 객체의 다른 제한사항도 동일하게 적용됩니다.            |
| CompletableFuture | Future의 확장으로, 복잡한 비동기 흐름 처리를 위한 다양한 유틸리티 메서드를 제공합니다. | 콜백을 통해 비동기 작업 완료 시 특정 작업을 수행할 수 있으며, 여러 비동기 작업을 조합하거나 체이닝할 수 있습니다. | 복잡한 비동기 작업의 경우 코드 가독성이 떨어질 수 있으며, 예외 처리를 적절히 다뤄야 합니다. |

<br/>
<br/>

## 3. 비동기의 이해 : Executor

    ⭕️ Spring Boot Async의 경우는 내장된 Spring Framework를 이용하여 SimpleAsyncTaskExecutor를 구성하고 이외에는 java.util.concurrent 패키지 내에서 ThreadPoolTaskExecutor, ScheduledThreadPoolExecutor, ForkJoinPool를 구현합니다.



<img src="https://github.com/user-attachments/assets/b793ac91-211d-415c-911e-19bc556f8504"/>

<br/>

| Executor 구현체 종류        | 설명                                                            | 특징                                                                                                               |
| --------------------------- | --------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| SimpleAsyncTaskExecutor     | 각 작업을 새로운 스레드에서 실행하며, 스레드 풀을 사용하지 않음 | 설정이 간단하고, 많은 작업을 짧은 시간 내에 실행하면 자원 부족 가능성이 있음.                                      |
| ThreadPoolTaskExecutor      | 스레드 풀을 사용하여 비동기 작업을 효율적으로 처리              | 스레드 풀의 크기, 큐 용량, 스레드 이름 접두사 등 다양한 설정이 가능하며 과도한 스레드 생성 방지 설정이 가능합니다. |
| ScheduledThreadPoolExecutor | 주기적 또는 일정 시간 후에 작업을 실행                          | 주기적 작업이나 지연된 작업을 예약하며 주기적인 작업 스케줄링에 사용합니다.                                        |
| ForkJoinPool                | 병렬 처리를 위해 설계된 Executor                                | 작업을 작은 단위로 분할하여 병렬로 처리합니다.                                                                     |
