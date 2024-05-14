# Spring Boot JPA + QueryDSL

    ⭕️ Spring Boot JPA 활용방법 : QueryDSL

⭕️ 가이드 링크

* [Spring Boot Data JPA + QueryDSL 이해하기 -1 : 정의 및 구성요소](https://adjh54.tistory.com/484)
* [Spring Boot Data JPA + QueryDSL 이해하기 -2 : 초기 환경설정 및 사용예시](https://adjh54.tistory.com/485)
* [Spring Boot Data JPA + QueryDSL 이해하기 -3: Join 활용(내부, 외부, 패치, 세타)](https://adjh54.tistory.com/488)

## 1. 개발환경

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/6b07c184-5c60-4b20-89e3-ea15fadb501f"/>

| 환경 분류                        | 버전      |
|------------------------------|---------|
| JDK                          | Java 17 |
| spring-boot                  | 3.2.5   |
| spring-boot-starter-web      | 3.2.5   |
| spring-boot-starter-data-jpa | 3.2.5   |
| Lombok                       | -       |
| com.querydsl:querydsl-jpa    | 5.0.0   |
| com.querydsl:querydsl-apt    | 5.0.0   |
| com.mysql:mysql-connector-j  | 8.3.0   |

<br/>
<br/>

## 2. 사용방법

    ⭕ 해당 프로젝트 내에는 QuertyDSL 내의 Q-CLASS 생성방법에 대해 포함되어 있습니다. 반드시 참고해주세요.

<br/>

1. application-spring-boot-jpa.yml 파일 내에 spring.jpa.hibernate.ddl-auto 속성 값을 update로 변경합니다.
2. Gradle 빌드를 수행하여 라이브러리를 설치합니다.
3. Gradle Task 중 JavaCompile을 수행하여 Q-CLASS를 생성합니다.
4. Spring Boot Server를 실행합니다.

## 3. 도메인 구조(Entity)

### 3.1. 테이블 구조 및 Dummy 데이터 생성 : QueryDSL Join 관계

<br/>

> 1. INNER JOIN (1:1 관계) 테이블 구조
     <img src="https://github.com/adjh54ir/blog-codes/assets/70501374/d800c632-6566-4b1a-bd4f-17a648dd27bf"/>

<br/>

> 2. INNER JOIN (1:N 관계) 테이블 구조
     <image src="https://github.com/adjh54ir/blog-codes/assets/70501374/143d8ebe-65af-4189-b0fd-917b51e36423"/>


<br/>

> 3. LEFT JOIN 테이블 구조
     <image src="https://github.com/adjh54ir/blog-codes/assets/70501374/5717e256-8cf8-48a1-9bf8-a472bd3feeab"/>


<br/>

> 4. RIGHT JOIN 테이블 구조
     <image src="https://github.com/adjh54ir/blog-codes/assets/70501374/5717e256-8cf8-48a1-9bf8-a472bd3feeab"/>

<br/>

> 5. FETCH JOIN 테이블 구조(관계)
     <img src ="https://github.com/adjh54ir/blog-codes/assets/70501374/c6518883-5cf9-4dd5-a30e-e1f97137d933"/>

<br/>

> 6. THETA JOIN 테이블 구조
     <img src="https://github.com/adjh54ir/blog-codes/assets/70501374/efd67a6c-c620-4e2c-90b5-7499e36b7cc9"/>

<br/>
<br/>

```sql
-- 테스트를 위한 Dummy 데이터 생성 DML
-- 사용자 테이블 데이터 Dummy
INSERT INTO tb_user(user_id, user_nm, user_st)
-- VALUES('adjh54', '박철수', 'A');
-- VALUES('ckas123', '홍길동', 'A');
VALUES ('kim9090', '김미미', 'A');

-- 여권 테이블 데이터 Dummy
INSERT INTO tb_passport (user_sq, passport_id, expired_date, issue_date)
VALUES (1, 'M3848512', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- 주문 테이블 데이터 Dummy
INSERT INTO tb_order(user_sq, order_nm, order_req, order_date)
-- VALUES(1, '짜장면', '단무지 많이 주세요', CURRENT_TIMESTAMP);
-- VALUES(1, '마라탕', '마라탕 맵기는 신라면 정도', CURRENT_TIMESTAMP);
VALUES (1, '카레', '카레의 맵기는 매운맛', CURRENT_TIMESTAMP);


-- 동아리 테이블 데이터 Dummy
INSERT INTO tb_club(club_nm, club_desc, est_date, club_captain_nm)
VALUES ('볼링 동아리', '50년 역사를 자랑하는 볼링 동아리입니다.', CURRENT_TIMESTAMP, '김미미');
-- VALUES('영화 동아리', '최신 영화는 다본다 영화 동아리입니다', CURRENT_TIMESTAMP, "홍길동");
-- VALUES('카페 동아리', '얼어죽어도 아아만 마시는 카페 동아리입니다.', CURRENT_TIMESTAMP, "박철수");
```

️

## 4. API Endpoint

| End point                                        | HTTP METHOD | JPA 분류   | 설명                      |
|--------------------------------------------------|:------------|:---------|-------------------------|
| http://localhost:8000/api/v1/user/userPassports  | POST        | QueryDSL | 사용자와 여권의 INNER JOIN 예시  |
| http://localhost:8000/api/v1/user/userClubs      | POST        | QueryDSL | 사용자와 동아리의 LEFT JOIN 예시  |
| http://localhost:8000/api/v1/user/userClubsRight | POST        | QueryDSL | 사용자와 동아리의 RIGHT JOIN 예시 |
| http://localhost:8000/api/v1/user/userInfo       | POST        | QueryDSL | 사용자의 FETCH JOIN 예시      |
| http://localhost:8000/api/v1/user/userClubAll    | POST        | QueryDSL | 사용자의 THETA JOIN 예시      |



