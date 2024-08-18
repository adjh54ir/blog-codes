import React, { useEffect, useState } from 'react';
import io from 'socket.io-client';
const SocketIoRoomComponet = () => {
	// socket.io-client를 통해서 소켓 서버에 연결합니다.
	const socket = io('http://localhost:5001');
	// 상태 관리
	const [message, setMessage] = useState<string>(''); // 입력되는 메시지
	const [messages, setMessages] = useState<{ id: string; text: string }[]>([]); // 입력되는 메시지가 쌓이는 배열
	const [roomArr, setRoomArr] = useState<number[]>([
		0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
	]);

	const [isEnter, setIsEnter] = useState<boolean>(false);

	useEffect(() => {
		//
	}, []);

	/**
	 * Socket.io의 특정 이벤트가 발생했을 때 특정 작업을 수행
	 */
	const initSocket = () => {
		// "message"가 발생하였을때, 이벤트가 수행합니다.
		socket.on('message', (message: { id: string; text: string }) => {
			// event 수행
			setMessages((prevMessages) => [...prevMessages, message]);
		});
	};

	const enterChat = () => {};

	return (
		<>
			{isEnter === false ? (
				<div
					style={{
						marginLeft: 200,
						flex: 1,
						flexDirection: 'column',
					}}>
					<h2 style={{ marginLeft: 320 }}>
						Socket.io의 Websocket 채팅방에 오신것을 환영합니다.
					</h2>

					<div
						style={{
							marginRight: 20,
							flex: 1,
							flexDirection: 'row',
							flexWrap: 'wrap',
						}}>
						{roomArr.map((item) => {
							return (
								<button
									style={{
										height: 100,
										marginRight: 20,
										marginBottom: 20,
										width: 300,
									}}>
									{item} 번 채팅방
								</button>
							);
						})}
					</div>
					<button
						style={{
							marginLeft: 350,
							height: 100,
							width: 400,
							background: 'skyblue',
						}}
						onClick={enterChat}>
						채팅방 입장
					</button>
				</div>
			) : (
				<></>
			)}
		</>
	);
};
export default SocketIoRoomComponet;
