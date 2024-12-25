// import library
const app = require("express")(); // 외부 라이브러리 사용
const cors = require("cors"); // 외부 라이브러리 사용
const http = require("http"); // Node 제공 모듈 사용

/*
 * 1. WebSocket Server 설정 : port, cors 지정
 */
const port = 5001;
app.set("port", port); // 웹 소켓 포트를 지정합니다
app.use(cors({ origin: "*" }));
// cors를 지정해줍니다.

/*
 * 2. WebSocket Server 설정값 및 socket.io 구성
 */
const server = http.createServer(app).listen(port); // 설정 값에 따르는 서버 정보 구축
const io = require("socket.io")(server, {
  cors: {
    origin: "*",
    methods: ["*"], // 모든 메서드 허용
  },
}); // 설정 값에 따라 socket.io 구축

/*
 * 3. 루트 엔드포인트로 접속 시 health Check 수행
 */
app.get("/", (req, res, next) => {
  res.send("Hello world!"); // 화면상으로 출력
  console.log("====== Health Check ======="); // 로그 출력
});

/*
 * 4. 소켓 연결 시작
 */
io.on("connection", (socket) => {
  console.log("a user connected");

  /**
   * [임의구성] Socket 10개의 방 생성합니다.
   */
  for (let i = 1; i <= 10; i++) {
    const roomName = `room-${i}`;

    /**
     * [ALL] "joinRoom-x" 이벤트 발생 시, "room-x"라는 방에 참가시키고 socket.room 속성에 방 이름 저장합니다.
     */
    socket.on(`joinRoom-${i}`, () => {
      console.log(`User joined ${roomName}`);
      socket.join(roomName); // 방 참가
      socket.room = roomName; // socket 정보 중 room 속성에 참가한 방 이름 지정
    });
    //

    /**
     * [ALL] "leaveRoom-x" 이벤트 발생 시, "room-x"라는 방에 나갑니다.
     */
    socket.on(`leaveRoom-${i}`, () => {
      console.log(`User leaved ${roomName}`);
      socket.leave(roomName);
    });
  }

  /**
   * [ANY ROOM] "roomMessage" 이벤트 발생 시, socket.room 속성의 특정 방에 "roomMessage" 이벤트를 저장한 소켓에게 전달합니다.
   */
  socket.on("roomMessage", (msg) => {
    console.log(`${socket.room} 번 방에 전달된 메시지 :`, msg);
    io.to(socket.room).emit("roomMessage", msg);
  });

  /**
   * [ALL] "disconnect" 이벤트 발생 시, 소켓 연결을 종료 및 방을 나갑니다.
   */
  socket.on("disconnect", () => {
    console.log("a user disconnected");
    socket.disconnect();
    socket.leave(socket.room);
  });
});
