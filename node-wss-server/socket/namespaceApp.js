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
 * 4. "일반 사용자" Namespace 소켓 연결 시작
 */
const nnsp = io.of("/normal");
nnsp.on("connection", (socket) => {
  console.log("[+] 일반 사용자가 소켓 서버에 연결되었습니다.");
  /**
   * normal namespace 내에서 "event:message" 이벤트 발생 시, namespace에서 "message"를 수신하는 사용자에게 메시지를 전달합니다.
   */
  socket.on("event:message", (msg) => {
    console.log("메시지가 도착했습니다 ", msg);
    nnsp.emit("event:message", msg);
  });

  /**
   * "disconnect" 이벤트 발생 시, 소켓 연결을 종료합니다.
   */
  socket.on("disconnect", () => {
    console.log("a user disconnected");
    socket.disconnect();
  });
});

/*
 * 5. "관리자" Namespace 소켓 연결 시작
 */
const nsp = io.of("/admin");

nsp.on("connection", (socket) => {
  console.log("[+] 관리자가 소켓 서버에 연결되었습니다.");

  /**
   * admin namespace 내에서 "event:message" 이벤트 발생 시, namespace에서 "message"를 수신하는 사용자에게 메시지를 전달합니다.
   */
  socket.on("event:message", (msg) => {
    nsp.emit("event:message", msg);
  });
  /**
   * "disconnect" 이벤트 발생 시, 소켓 연결을 종료합니다
   */
  socket.on("disconnect", () => {
    console.log("a user disconnected");
    socket.disconnect();
  });
});
