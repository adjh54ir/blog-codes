# spring-boot-redis

    ⭕️ Spring Boot Redis 기반의 API 서버에서 Redis를 동작하는 예시들을 구성한 Repository입니다.

<br />
<br />

🔗 가이드 링크

- [Redis(Remote Dictionary Server) 이해하기 -1 : 구조 및 특징, 아키텍처](https://adjh54.tistory.com/447)
- [Redis(Remote Dictionary Server) 이해하기 -2 : MacOS 로컬 환경 구성 및 명령어](https://adjh54.tistory.com/448)
- [Dockerfile을 이용한 Redis 환경 구성 및 실행방법](https://adjh54.tistory.com/449)
- [RedisTemplate API Document](https://adjh54.tistory.com/462)
- [ValueOperations API Document](https://adjh54.tistory.com/460)
- [HashOperations API Document](https://adjh54.tistory.com/461)

## 1. 개발환경

| 환경 분류                     | 버전    |
|---------------------------|-------|
| JDK                       | 17    |
| spring-boot               | 3.3.1 |
| spring-boot-starter-web   | 3.3.1 |
| spring-boot-starter-redis | 3.3.1 |
| Lombok                    | -     |

<br/>
<br/>

## 2. Redis 클래스

<img src="https://github.com/user-attachments/assets/265ca8f4-879f-4eb0-97f2-831d6b45dd47"/>

| **클래스**                   | **데이터 타입**         | **설명**                                                                     |
|---------------------------|--------------------|----------------------------------------------------------------------------|
| **ValueOperations**       | Strings            | 단일 값에 대한 연산을 수행하는 클래스입니다. 주로 문자열 타입의 데이터를 다룹니다.                            |
| **BitmapOperations**      | Bitmaps            | 비트맵 타입의 데이터를 다루는 클래스입니다. 비트맵에 비트를 설정하거나 검색하는 등의 연산을 수행합니다.                 |
| **BitFieldOperations**    | Bit field          | 비트 필드 타입의 데이터를 다루는 클래스입니다. 비트 필드에 비트를 설정하거나 검색하는 등의 연산을 수행합니다.             |
| **HashOperations**        | Hashes             | 해시 타입의 데이터를 다루는 클래스입니다. 해시에 필드와 값을 추가하거나 제거하는 등의 연산을 수행합니다.                |
| **ListOperations**        | Lists              | 리스트 타입의 데이터를 다루는 클래스입니다. 리스트의 요소를 추가하거나 제거하는 등의 연산을 수행합니다.                 |
| **SetOperations**         | Sets               | 세트 타입의 데이터를 다루는 클래스입니다. 세트에 요소를 추가하거나 제거하는 등의 연산을 수행합니다.                   |
| **ZSetOperations**        | Sorted Sets        | 정렬된 세트 타입의 데이터를 다루는 클래스입니다. 세트의 요소를 추가하거나 제거하면서, 각 요소에 점수를 부여하여 순서를 정렬합니다. |
| **GeoOperations**         | Geospatial Indexes | 지리적 인덱스 타입의 데이터를 다루는 클래스입니다. 지리적 인덱스에 위치를 추가하거나 검색하는 등의 연산을 수행합니다.         |
| **HyperLogLogOperations** | Hyperloglogs       | 하이퍼로그로그 타입의 데이터를 다루는 클래스입니다. 하이퍼로그로그에 요소를 추가하거나 카운트하는 등의 연산을 수행합니다.        |
| **StreamOperations**      | Streams            | 스트림 타입의 데이터를 다루는 클래스입니다. 스트림에 메시지를 추가하거나 읽는 등의 연산을 수행합니다.                  |

<br/>
<br/>

## 3. API 엔드포인트

| **엔드포인트**                             | **HTTP Method** | **메서드 명**   | **설명**                                                        |
|---------------------------------------|-----------------|-------------|---------------------------------------------------------------|
| **/api/v1/redis/singleData/getValue** | POST            | getValue    | Redis 키를 기반으로 단일 데이터의 값을 조회합니다.                               |
| **/api/v1/redis/singleData/setValue** | POST            | setValue    | Redis 단일 데이터 값을 등록/수정합니다. duration 값이 존재하면 메모리 상 유효시간을 지정합니다. |
| **/api/v1/redis/singleData/delete**   | DELETE          | deleteRow   | Redis 키를 기반으로 단일 데이터의 값을 삭제합니다.                               |
| **/api/v1/redis/listData/getList**    | POST            | getListData | Redis 키를 기반으로 다중 데이터(리스트)의 값을 조회합니다.                          |
| **/api/v1/redis/listData/setList**    | POST            | setListData | Redis 다중 데이터 값을 등록/수정합니다. duration 값이 존재하면 메모리 상 유효시간을 지정합니다. |
| **/api/v1/redis/listData/getList**    | DELETE          | deleteList  | Redis 키를 기반으로 다중 데이터의 값을 삭제합니다.                               |

<br/>
<br/>

## 4. 처리 결과

### 4.1. Redis 값 등록

    ⭕️ redis 내의 값을 저장하기 위해 해당 endpoint로 body를 담아서 호출하였습니다.

<img src="https://github.com/user-attachments/assets/06ddac75-246e-402f-ac6c-44b832fd5077"/>

<br/>
<br/>

<img src="https://github.com/user-attachments/assets/555891cb-3ee7-4cbc-964d-cda52e612add"/>

<br />
<br />

### 4.2. Redis 값 수정

    ⭕️ redis 내의 값을 수정하기 위해 해당 endpoint로 body를 담아서 호출하였습니다. 

<img src="https://github.com/user-attachments/assets/b7653980-c1f9-49d0-97f0-119940ca5b93">

<br/>
<br/>

<img src="https://github.com/user-attachments/assets/cc48ae2d-7966-4dca-b959-a7fd52dd5b72">

<br />
<br />

### 4.3. Redis 값 조회

    ⭕️ 이전에 생성한 값을 조회하기 위해 key 값을 전달하여 조회하여 결과를 얻었습니다.

<img src="https://github.com/user-attachments/assets/7d7733c8-e37c-404c-8094-7d562e7e78cf">

<br />
<br />

### 4.4. Redis 값 삭제

    ⭕️  Redis의 값을 삭제하기 위해 key 값을 전송하여서 결과 값으로 1로 반환을 받아서 값이 삭제됨을 확인하였습니다.

<br />
<br />

<img src="https://github.com/user-attachments/assets/4a92e74d-f5da-4cd0-b73d-e9afb124faa9">

<br/>
<br/>

<img src="https://github.com/user-attachments/assets/a6ca40d7-632b-4104-8ffc-1224a132fb21">
