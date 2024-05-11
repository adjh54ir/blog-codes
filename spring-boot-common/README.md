# Spring Boot Common

    ⭕️ Spring Boot에서 사용되는 공통 기능들을 정의한 Repository 

<br/>
<br/>

⭕️ 가이드 링크

* [정규표현식(RegExp) 이해하기 : 패턴, 문자 클래스, 자주 사용 패턴](https://adjh54.tistory.com/104)
* [IP 주소 반환받는 방법 : 클라이언트 IP, 외부 IP, 내부 IP, 호스트 IP](https://adjh54.tistory.com/443)
* [SecureRandom을 이용한 랜덤 문자열 생성 방법: 숫자, 문자, 특수문자 조합, 임시 비밀번호](https://adjh54.tistory.com/426)
* [Spring Boot 환경에서 Jackson 모듈 활용하기 : JSON 파싱, 직렬화, 역 직렬화, JSON 파일 읽어오기/생성](https://adjh54.tistory.com/375)

## 1. 개발환경

| 환경 분류                                          | 버전      |
|------------------------------------------------|---------|
| JDK                                            | Java 17 |
| spring-boot                                    | 3.2.5   |
| spring-boot-starter-web                        | 3.2.5   |
| Lombok                                         | -       |
| com.googlecode.json-simple:json-simple         | 1.1.1   |
| com.fasterxml.jackson.core:jackson-core        | 2.16.1  |
| com.fasterxml.jackson.core:jackson-annotations | 2.16.1  |
| com.fasterxml.jackson.core:jackson-annotations | 2.16.1  |

<br/>
<br/>

## 2. 파일 구성

    ⭕️ 공통으로 사용되는 파일을 구성에 대한 설명입니다.

| 파일                                    | 설명                               |
|---------------------------------------|----------------------------------|
| commons/constant/RegExpConstants.java | 자주 사용하는 정규식을 정의한 Class 파일입니다.    |
| commons/enums/RegExpEnum.java         | 자주 사용되는 정규식을 Enum 파일로 지정한 파일입니다. |
| commons/utils/ConvertUtils.java       | 데이터 형 변환에 사용되는 Utils 파일입니다.      |
| commons/utils/EncryptUtils.java       | 암호화/복호화에 사용되는 Utils 파일입니다.       |
| commons/utils/NetworkUtils.java       | Network와 관련되어 사용되는 Utils 파일입니다.  |
| commons/utils/NumberUtils.java        | 숫자와 관련되는 Utils 파일입니다.            |
| commons/utils/StringUtils.java        | 문자열과 관련되는 Utils 파일입니다.           |
