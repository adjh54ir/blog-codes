# Spring Boot JPA

    ⭕️ Spring Boot JPA 활용방법 : JPARepository, JPQL, Criteria API, Name Method, @Query, NamedQuery QueryDSL 

<br/>
<br/>

⭕️ 가이드 링크

* [Spring Boot Data JPA 이해하기 -1: ORM, JPA, Hibernate, QueryDSL 이론](https://adjh54.tistory.com/421)
* [Spring Boot Data JPA 이해하기 -2 : 초기 환경 구성 + JpaRepository 활용 방법](https://adjh54.tistory.com/422)
* [Spring Boot Data JPA 이해하기 -3 : JpaRepository 활용 방법 - Query Method, @Query, NamedQuery](https://adjh54.tistory.com/481)
* [Spring Boot Data JPA + JPQL 이해하기 -1 : 정의 및 기본동작](https://adjh54.tistory.com/479)
* [Spring Boot Data JPA + Criteria API 이해하기 -1 : 정의 및 기본동작](https://adjh54.tistory.com/483)
* [Spring Boot Data JPA + QueryDSL 이해하기 -1 : 정의 및 구성요소](https://adjh54.tistory.com/484)
* [Spring Boot Data JPA + QueryDSL 이해하기 -2 : 초기 환경설정 및 사용예시](https://adjh54.tistory.com/485)
* [Spring Boot Data JPA + QueryDSL 이해하기 -3: Join 활용(내부, 외부, 패치, 세타)](https://adjh54.tistory.com/488)
  <br/>
  <br/>

## 1. 개발환경

<img src="https://github.com/adjh54ir/blog-codes/assets/70501374/6b07c184-5c60-4b20-89e3-ea15fadb501f">

| 환경 분류                       | 버전      |
|-----------------------------|---------|
| JDK                         | Java 17 |
| spring-boot                 | 3.2.5   |
| spring-boot-starter-web     | 3.2.5   |
| Lombok                      | -       |
| com.querydsl:querydsl-jpa   | 5.0.0   |
| com.querydsl:querydsl-apt   | 5.0.0   |
| com.mysql:mysql-connector-j | 8.3.0   |

<br/>
<br/>

## 2. 사용방법

    ⭕ 해당 프로젝트 내에는 JpaRepository, JPQL, QueryDSL이 함께 포함되어 있습니다.

️

## 3. API Endpoint

| End point                                                 | HTTP METHOD | JPA 분류        | 설명                        |
|-----------------------------------------------------------|:------------|:--------------|---------------------------|
| http://localhost:8000/api/v1/userRepo/users               | POST        | JpaRepository | 사용자 조회 예시                 |
| http://localhost:8000/api/v1/userRepo/user                | POST        | JpaRepository | 사용자 등록 예시                 |
| http://localhost:8000/api/v1/userRepo/user                | PUT         | JpaRepository | 사용자 수정 예시                 |
| http://localhost:8000/api/v1/userRepo/user                | DELETE      | JpaRepository | 사용자 삭제 예시                 |
| http://localhost:8000/api/v1/userJpql/userTypedQuery      | POST        | JPQL          | TypedQuery 타입으로 데이터 반환 예시 |
| http://localhost:8000/api/v1/userJpql/userQuery           | POST        | JPQL          | Query 타입으로 데이터 반환 반환 예시   |
| http://localhost:8000/api/v1/userJpql/nameBaseUser        | POST        | JPQL          | 이름 기준 파라미터 바인딩 예시         |
| http://localhost:8000/api/v1/userJpql/placeBaseUser       | POST        | JPQL          | 위치 기준 파라미터 바인딩 예시         |
| http://localhost:8000/api/v1/userJpql/entityProjectUser   | POST        | JPQL          | 엔티티 프로젝션을 이용한 데이터 조회 예시   |
| http://localhost:8000/api/v1/userJpql/embeddedProjectUser | POST        | JPQL          | 임베디드 프로젝션을 이용한 데이터 조회 예시  |
| http://localhost:8000/api/v1/userJpql/scalarProjectUser   | POST        | JPQL          | 스칼라 프로젝션을 이용한 데이터 조회 예시   |
| http://localhost:8000/api/v1/userJpql/innerJoinUser       | POST        | JPQL          | INNER JOIN 방식 예시          |
| http://localhost:8000/api/v1/userJpql/outerLeftJoinUser   | POST        | JPQL          | OUTER LEFT JOIN 방식 예시     |
| http://localhost:8000/api/v1/userJpql/oneUser             | POST        | JPQL          | 단건 조회 방식 예시               |
| http://localhost:8000/api/v1/userJpql/manyUser            | POST        | JPQL          | 다건 조회 방식 예시               |
| http://localhost:8000/api/v1/userCriteria/user            | POST        | Criteria API  | 사용자 조회 예시                 |
| http://localhost:8000/api/v1/userCriteria/users           | POST        | Criteria API  | 사용자 리스트 조회 예시             |
| http://localhost:8000/api/v1/userCriteria/orderUser       | POST        | Criteria API  | 동적 사용자 정렬 예시              |
| http://localhost:8000/api/v1/userCriteria/userInfo        | POST        | Criteria API  | 동적 where 절 구성 예시          |
| http://localhost:8000/api/v1/user/userPassports           | POST        | QueryDSL      | 사용자와 여권의 INNER JOIN 예시    |
| http://localhost:8000/api/v1/user/userClubs               | POST        | QueryDSL      | 사용자와 동아리의 LEFT JOIN 예시    |
| http://localhost:8000/api/v1/user/userClubsRight          | POST        | QueryDSL      | 사용자와 동아리의 RIGHT JOIN 예시   |
| http://localhost:8000/api/v1/user/userInfo                | POST        | QueryDSL      | 사용자의 FETCH JOIN 예시        |
| http://localhost:8000/api/v1/user/userClubAll             | POST        | QueryDSL      | 사용자의 THETA JOIN 예시        |