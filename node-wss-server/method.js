const http = require("http"); // Node 제공 모듈 사용
const cors = require("cors"); // 외부 라이브러리 사용
const server = http.createServer(app).listen(port); // 설정 값에 따르는 서버 정보 구축
const io = require("socket.io")(server);

import { io as clientIo } from "socket.io-client";
/**
 *
 */
const socketIoClient = () => {
  // socket.io-client를 통해서 소켓 서버에 연결합니다.
  const socket = clientIo("http://localhost:5001");
  // 2. 소켓 이벤트 '수신'
  socket.on("hello", (arg, callback) => {
    console.log(arg); // "world"
    callback("got it");
  });

  // 2. 이벤트 '송신'
  socket.emit("hello", "world", (response) => {
    console.log(response); // "got it"
  });

  // 4. 소켓 이벤트 '송신' 시간 지정
  socket.timeout(5000).emit("hello", "world", (err, response) => {
    if (err) {
      // the other side did not acknowledge the event in the given delay
    } else {
      console.log(response); // "got it"
    }
  });

  socket.off();
};

// 1. 소켓에 연결을 수행합니다.
io.on("connection", (socket) => {
  // classic users
});

// 2. 특정 룸이나 네임스페이스("/admin")에 대한 연결을 수행합니다.
io.of("/admin").on("connection", (socket) => {
  // admin users
});
