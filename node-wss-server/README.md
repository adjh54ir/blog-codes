# node-wss-server

    ⭕️ Node + Socket.io 기반의 웹 소켓입니다.

<br/>
<br/>

## 1. 참고 사이트

- [Socket.io 기반 소켓 서버 구축 방법 -1 : 구성 요소 및 흐름 + React 기반 채팅 화면 구성](https://adjh54.tistory.com/548)
- [Socket.io 기반 소켓 서버 구축 방법 -2 : Room, Namespace 별 소켓 통신 방법](https://adjh54.tistory.com/549)
- [PM2(Process Manager 2) 이해하고 주요 특징 알아보기](https://adjh54.tistory.com/551)

<br/>
<br/>

## 2. 웹 소켓 서버 실행 방법

```shell
# 프로젝트 초기 라이브러리 설치
$ npm i

# room Socket Server실행
$ npm run start:room

# workspace Socket Server실행
$ npm run start:workspace

# 로컬 모드로 실행
$ npm run start:loc

######## pm2 실행 과정 ##########

# 개발 모드로 실행 : Clustor Mode
$ npm run start:dev

# 스테이징 모드로 실행 : Clustor Mode
$ npm run start:stg

# 운영 모드로 실행 : Clustor Mode
$ npm run start:prd


```

<br/>
<br/>

## 3. 웹 소켓 실행 경로

### 3.1. 지정한 경로 접속

    ⭕️ http://localhost:5000 으로 지정한 경로에 접속하여 Health Check를 확인합니다.

<img src="https://github.com/user-attachments/assets/7080e697-d262-4d4e-bcb3-8e688f38325c"/>

<br/>
<br/>

### 3.2. 소켓 서버가 실행된 상태로 React App 내에서 소켓 통신을 수행합니다.

<img src ="https://github.com/user-attachments/assets/8d0abc2f-a74a-46af-a836-23dc1e84f759"/>

<br/>
<br/>

### 3.1.1 A 브라우저 -> B 브라우저 전달

<img src ="https://github.com/user-attachments/assets/fcfa65b9-8ab1-4ec3-8bed-1ce7ad6f9730"/>

<br/>
<br/>

### 3.1.2 B 브라우저 -> A 브라우저 전달

<img src="https://github.com/user-attachments/assets/e7e60f82-3d50-4687-af02-51b32eb7bce6"/>
