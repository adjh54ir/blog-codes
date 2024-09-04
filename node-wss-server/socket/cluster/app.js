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
 * 3. Redis 설정합니다.
 */
const redis = require("@socket.io/redis-adapter");

// Redis를 이용하여 다중 instance 연결한다.
io.adapter(
  redis({ host: process.env.REDIS_HOST, port: process.env.REDIS_HOST_PORT })
);

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
   * [ALL] "message" 이벤트 발생 시, "message" 이벤트를 등록한 소켓에게 메시지 전달합니다.
   */
  socket.on("message", (msg) => {
    io.emit("message", msg);
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
