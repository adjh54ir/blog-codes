# Spring Boot Slack

    ⭕️ Spring Boot 환경에서 Slack 활용하는 예시를 담은 Repository 입니다.

⭕️ 가이드 링크
<br/>

- [Spring Boot 환경에서 Slack Incoming Webhook 이해하고 구성하기 -1 : 초기 구성 및 간단 메시지 전송](https://adjh54.tistory.com/568)
- [Spring Boot 환경에서 Slack Incoming Webhook 이해하고 구성하기 -2 : 다양한 메시지 전송 방법](https://adjh54.tistory.com/569)
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

| Endpoint                  | HTTP Method | 설명                                                 |
|---------------------------|-------------|----------------------------------------------------|
| api/v1/slack/message      | POST        | Slack Channel URL을 기반으로 텍스트 메시지를 전송합니다             |
| api/v1/slack/exBlock      | POST        | Slack Channel URL을 기반으로 블록을 추가한 메시지를 전송하는 예시입니다.   |
| api/v1/slack/exAttachment | POST        | Slack Channel URL을 기반으로 첨부파일을 추가한 메시지를 전송하는 예시입니다. |

<br/>
<br/>

## 3. 결과 화면

### 1. 텍스트 전송

```java
/**
 * 간단한 Slack 메시지 전송 서비스
 *
 * @param paramText 전송 메시지
 * @return Slack 응답 값
 */
public WebhookResponse sendToSimpleText(String paramText) {

    Payload payload = Payload.builder().text(paramText).build();        // Slack에 전달할 Payload 구성
    WebhookResponse response;
    try {
        response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
        // WebhookResponse(code=200, message=OK, body=ok)
        System.out.println(response);
        return response;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

```


<img width="640" alt="다운로드 (1)" src="https://github.com/user-attachments/assets/efe11b8e-eed0-44e1-80e4-7a8a99d58f8f">

<br/>
<br/>

### 2. 이모지 전송

```java
/**
 * 이모지 사용예시
 *
 * @param paramText
 * @return
 */
public WebhookResponse sendToEmojiEx1(String paramText) {
    WebhookResponse response;
    Payload payload = Payload.builder()
            .text("안녕하세요! :smile:")
            .build();

    try {
        response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
        return response;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

<img width="1305" src="https://github.com/user-attachments/assets/03406bdc-0d94-47a0-b82c-290c58e188a6"/>
<img width="1305" src="https://github.com/user-attachments/assets/c35fe930-24a9-4c24-b041-b29b5258b99c"/>


<br/>
<br/>

### 3. 링크 전송

```java
/**
 * 링크 사용예시
 *
 * @param paramText
 * @return
 */
public WebhookResponse sendToLinkEx1(String paramText) {
    WebhookResponse response;
    Payload payload = Payload.builder()
            .text("자세한 내용은 <https://adjh54.tistory.com/|Contributor9 블로그>를 참조하세요.")
            .build();

    try {
        response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
        return response;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

```

<img width="1305" alt="image" src="https://github.com/user-attachments/assets/47afe8b3-0039-41d7-998d-2b27d209a89d">


<br/>
<br/>

### 4. 멘션 전송

```java
/**
 * 멘션 사용예시
 *
 * @param paramText
 * @return
 */
public WebhookResponse sendToMentionEx1(String paramText) {
    WebhookResponse response;
    Payload payload = Payload.builder()
            .text("안녕하세요, <@adjh54ir>! 채널 <#C07MVNXCUJK|general>을 확인해주세요.")
            .build();

    try {
        response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
        return response;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

<img width="1292" alt="image" src="https://github.com/user-attachments/assets/82e20531-e29c-4e57-b827-8d13ca545688">

<br/>
<br/>

### 5. 코드 블록 전송

```java
/**
 * 코드 블록 예시
 *
 * @param paramText
 * @return
 */
public WebhookResponse sendToCodeBlockEx1(String paramText) {
    WebhookResponse response;
    Payload payload = Payload.builder()
            .text("```\npublic class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}\n```")
            .build();

    try {
        response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
        return response;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}
```


<img width="1300" alt="image" src="https://github.com/user-attachments/assets/ab1d53b2-399b-46e7-8946-ba17e54a5f1a">



<br/>
<br/>

### 6. 블록 전송

```java
/**
 * 블록을 이용한 버튼 및 버튼 링크 구성 예시
 *
 * @param paramText
 * @return
 */
public WebhookResponse sendToBlockEx1(String paramText) {
    WebhookResponse response;
    Payload payload = Payload
            .builder()
            .blocks(Arrays.asList(
                    SectionBlock.builder().text(MarkdownTextObject.builder().text("버튼을 눌러주세요:").build()).build(),
                    ActionsBlock.builder().elements(
                            Arrays.asList(ButtonElement.builder()
                                    .text(PlainTextObject.builder().text("클릭").build())
                                    .actionId("button_click")
                                    .url("https://adjh54.tistory.com/")
                                    .build()
                            )).build()
            ))
            .build();
    try {
        response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
        return response;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

<img width="1298" alt="image" src="https://github.com/user-attachments/assets/e96bbee5-b962-4c1f-beb4-6b91ce46369f">

<br/>
<br/>

### 7. 첨부파일 전송

```java
/**
 * 첨부 파일을 포함한 예시
 *
 * @param paramText
 * @return
 */
public WebhookResponse sendToAttachmentEx1(String paramText) {
    WebhookResponse response;
    Payload payload = Payload.builder()
            .text("메시지와 함께 첨부 파일")
            .attachments(Collections.singletonList(
                    Attachment.builder()
                            .fallback("이미지를 불러올 수 없습니다.")
                            .imageUrl("https://avatars.githubusercontent.com/u/70501374?v=4")
                            .build()
            ))
            .build();
    try {
        response = slack.send(webhookUrl, payload);                     // Slack 메시지 전송
        return response;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```

<img width="1298" alt="image" src="https://github.com/user-attachments/assets/53414764-003e-4041-9333-f2381b5bde51">

<br/>
<br/>







