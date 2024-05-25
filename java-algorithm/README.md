# java-algorithm & java-dataStructure

    ⭕️ Java 언어 기반의 알고리즘 & 자료구조 정리 Repository

⭕️ 가이드 링크

## 1. 개발환경

| 환경 분류                   | 버전      |
|-------------------------|---------|
| JDK                     | Java 17 |
| spring-boot             | 3.2.5   |
| spring-boot-starter-web | 3.2.5   |
| Lombok                  | -       |

<br />
<br />

## 2. API Endpoint

| 알고리즘 분류             | Controller                   | End point                                           | HTTP METHOD | 설명                           |
|---------------------|------------------------------|-----------------------------------------------------|-------------|------------------------------|
| 탐색 알고리즘 > 해시        | SearchHashController         | http://localhost:8000/api/v1/search/hash/1          | POST        | [프로그래머스] Level1 - 폰켓몬        |
| 탐색 알고리즘 > 해시        | SearchHashController         | http://localhost:8000/api/v1/search/hash/2          | POST        | [프로그래머스] Level1 - 완주하지 못한 선수 |
| 탐색 알고리즘 > 해시        | SearchHashController         | http://localhost:8000/api/v1/search/hash/3          | POST        | [프로그래머스] level2 - 전화번호 목록    |
| 탐색 알고리즘 > 해시        | SearchHashController         | http://localhost:8000/api/v1/search/hash/4          | POST        | [프로그래머스] level2 : 의상         |
| 탐색 알고리즘 > 해시        | SearchHashController         | http://localhost:8000/api/v1/search/hash/5          | POST        | [프로그래머스] level3 : 베스트 앨범     |
| 탐색 알고리즘 > 해시        | SearchHashController         | http://localhost:8000/api/v1/search/hash/5          | POST        | [?] ? : 음식 주문                |
|                     |                              |                                                     |             |                              |
| 탐색 알고리즘 > 동적계획법     | DynamicProgrammingController | http://localhost:8000/api/v1/search/dp/1            | POST        | [?] ? : 숫자 카드                |
|                     |                              |                                                     |             |                              |
| 탐색 알고리즘 > 이진 탐색     | SearchBinaryController       | http://localhost:8000/api/v1/search/binary/1        | POST        | 이진 탐색 예시                     |
|                     |                              |                                                     |             |                              |
| 탐색 알고리즘 > 그리디 알고리즘  | SearchGreedyController       | http://localhost:8000/api/v1/search/greedy/1        | POST        | [프로그래머스] level3 - 거스름돈 계산    |
| 탐색 알고리즘 > 그리디 알고리즘  | SearchGreedyController       | http://localhost:8000/api/v1/search/greedy/2        | POST        | [프로그래머스] level1 - 체육복        |
|                     |                              |                                                     |             |                              |
| 탐색 알고리즘 > 투포인터 알고리즘 | TwoPointerController         | http://localhost:8000/api/v1/search/twoPointer/1    | POST        | [백준] 2018 - 수들의 합5           |
| 탐색 알고리즘 > 투포인터 알고리즘 | TwoPointerController         | http://localhost:8000/api/v1/search/twoPointer/2    | POST        | [백준] 1940 - 주몽               |
| 탐색 알고리즘 > 투포인터 알고리즘 | TwoPointerController         | http://localhost:8000/api/v1/search/twoPointer/3    | POST        | [백준] 1253 - 좋은수              |
|                     |                              |                                                     |             |                              |
| 완전 탐색 > 비트 마스크      | BitMaskController            | http://localhost:8000/api/v1/exhausive/bitMask1     | POST        | [프로그래머스] level1 - [1차] 비밀지도  |
|                     |                              |                                                     |             |                              |
| 완전 탐색 > 브루트포스       | BruteForceController         | http://localhost:8000/api/v1/exhausive/bruteForce/1 | POST        | [프로그래머스] level1 - 최소직사각형     |
| 완전 탐색 > 브루트포스       | BruteForceController         | http://localhost:8000/api/v1/exhausive/bruteForce/2 | POST        | [프로그래머스] level1 - 모의고사       |
| 완전 탐색 > 브루트포스       | BruteForceController         | http://localhost:8000/api/v1/exhausive/bruteForce/3 | POST        | [프로그래머스] leve2 - 카펫          |
|                     |                              |                                                     |             |                              |
| 자료구조 > 스텍           | StackController              | http://localhost:8000//api/v1/datastruct/stack/1    | POST        | [프로그래머스] Level1 - 같은 수는 싫어   |
| 자료구조 > 스텍           | StackController              | http://localhost:8000//api/v1/datastruct/stack/2    | POST        | [프로그래머스] Level2 - 올바른 괄호     |
| 자료구조 > 스텍           | StackController              | http://localhost:8000//api/v1/datastruct/stack/3    | POST        | [프로그래머스] Level2 - 괄호 회전하기    |
| 자료구조 > 스텍           | StackController              | http://localhost:8000//api/v1/datastruct/stack/4    | POST        | [프로그래머스] Level2 - 주식가격       |
|                     |                              |                                                     |             |                              |
| 자료구조 > 큐            | QueueController              | http://localhost:8000//api/v1/datastruct/queue/1    | POST        | [프로그래머스] Level1 - 명예의 전당(1)  |
| 자료구조 > 큐            | QueueController              | http://localhost:8000//api/v1/datastruct/queue/2    | POST        | [프로그래머스] level2 - 기능 개발      |
| 자료구조 > 큐            | QueueController              | http://localhost:8000//api/v1/datastruct/queue/3    | POST        | [프로그래머스] level2 - 프로세스       |
| 자료구조 > 큐            | QueueController              | http://localhost:8000//api/v1/datastruct/queue/4    | POST        | [프로그래머스] level2- 다리를 지나는 트럭  |
|                     |                              |                                                     |             |                              |