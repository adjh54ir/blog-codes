// import library
const app = require("express")(); // 외부 라이브러리 사용
const cors = require("cors"); // 외부 라이브러리 사용
const http = require("http"); // Node 제공 모듈 사용
const redis = require("redis");
const redisAdapter = require("@socket.io/redis-adapter");
/*
 * 1. WebSocket Server 설정 : port, cors 지정
 */
const port = process.env.PORT;
app.set("port", port); // 웹 소켓 포트를 지정합니다
app.use(cors({ origin: "*" }));
// cors를 지정해줍니다.

/*
 * 2. WebSocket Server 설정값 및 socket.io 구성
 */
const server = http.createServer(app).listen(port); // 설정 값에 따르는 서버 정보 구축
// 설정 값에 따라 socket.io 구축
const io = require("socket.io")(server, {
  cors: {
    origin: "*",
    methods: ["*"], // 모든 메서드 허용
  },
});

/*
 * 3. Redis 설정합니다.(Pub/Sub Redis 연결 구성)
 */
// 3.1. 발행자(Publisher) 클라이언트 생성
const pubClient = redis.createClient({
  host: process.env.REDIS_HOST,
  port: process.env.REDIS_HOST_PORT,
});
// 3.2. 구독자(Subscriber) 클라이언트 생성
const subClient = pubClient.duplicate();

// 3.3. Redis 클라이언트 연결
Promise.all([pubClient.connect(), subClient.connect()])
  .then(() => io.adapter(redisAdapter.createAdapter(pubClient, subClient))) // Redis adapter 설정
  .catch((error) => console.error("Redis connection error:", error));

/*
 * 4. 루트 엔드포인트로 접속 시 health Check 수행
 */
app.get("/", (req, res, next) => {
  res.send("Hello world!"); // 화면상으로 출력
  console.log("====== Health Check ======="); // 로그 출력
});

/*
 * 5. 소켓 연결 시작
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
