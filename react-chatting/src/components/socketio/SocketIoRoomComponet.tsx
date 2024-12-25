import React, { useEffect, useState } from 'react';
import io from 'socket.io-client';

/**
 * Socket.io의 구성요소 중 Room을 이용한 채팅
 * @returns
 */
const socket = io('http://localhost:5001'); // socket.io-client를 통해서 소켓 서버에 연결합니다.

const SocketIoRoomComponet = () => {
	const roomArr = Array.from({ length: 10 }, (_, i) => i + 1); // 방 개수 구성

	// 상태 관리
	const [message, setMessage] = useState<string>(''); // 입력되는 메시지
	const [messages, setMessages] = useState<{ id: string; text: string }[]>([]); // 누적되는 메시지 관리
	// 방 정보를 관리합니다.
	const [roomInfo, setRoomInfo] = useState<{
		isEnter: boolean;
		nowRoomNum: number;
	}>({ isEnter: false, nowRoomNum: 0 });

	useEffect(() => {
		return () => {
			socket.off('roomMessage'); // [Socket] "roomMessage" 이벤트의 메시지 수신을 종료합니다.
		};
	}, []);

	/**
	 * 방 입장
	 * @param choiceRoomNum
	 * @return void
	 */
	const enterRoom = (choiceRoomNum: number): void => {
		console.log(`${choiceRoomNum} 채팅방을 입장하셨습니다.`);
		setRoomInfo({ isEnter: true, nowRoomNum: choiceRoomNum });
		socket.emit(`joinRoom-${choiceRoomNum}`); // [Socket] "joinRoom-x"로 이벤트를 발생시키며, 특정 방에 참가합니다.

		// [Socket] "roomMessage" 이벤트 발생 시, 값을 수신하고 상태관리에 저장합니다.
		socket.on('roomMessage', (message: { id: string; text: string }) => {
			console.log('메시지를 수신합니다.', message);
			setMessages((prevMessages) => [...prevMessages, message]);
		});
	};

	/**
	 * 방 나가기
	 * @return void
	 */
	const leaveRoom = (): void => {
		console.log(`${roomInfo.nowRoomNum} 채팅방에 퇴장하였습니다..`);
		socket.emit(`leaveRoom-${roomInfo.nowRoomNum}`); // [Socket] "leaveRoom-x" 이벤트를 발생시키며, 특정 방에 나갑니다.
		socket.off('roomMessage'); // [Socket] "roomMessage" 이벤트의 메시지 수신을 종료합니다.
		setRoomInfo({ isEnter: false, nowRoomNum: 0 });
		setMessages([]);
	};

	/**
	 * 소켓을 통한 메시지 전송
	 * @param {React.FormEvent} e
	 * @return void
	 */
	const sendMessage = (e: React.FormEvent): void => {
		e.preventDefault();
		const newMessage = { id: socket.id, text: message };
		socket.emit('roomMessage', newMessage); // [Socket] "roomMessage" 이벤트를 발생시키며, 값을 전달합니다.
		setMessage(''); // 전송 이후 입력한 텍스트를 지워줍니다.
	};

	return (
		<>
			{roomInfo?.isEnter === false ? (
				<div
					style={{
						flex: 1,
						flexDirection: 'column',
					}}>
					<h2 style={{ width: '100%', textAlign: 'center' }}>
						Socket.io의 Websocket 채팅방에 오신것을 환영합니다.
					</h2>

					<div
						style={{
							marginRight: 20,
							marginTop: 60,
							flex: 1,
							flexDirection: 'row',
							flexWrap: 'wrap',
						}}>
						{roomArr.map((item, idx) => {
							return (
								<button
									key={`room-${idx}`}
									style={{
										height: 100,
										marginRight: 20,
										marginBottom: 20,
										width: 300,
									}}
									onClick={() => enterRoom(item)}>
									{item} 번 채팅방
								</button>
							);
						})}
					</div>
				</div>
			) : (
				<div>
					<div
						style={{
							marginLeft: 200,
							flex: 1,
							flexDirection: 'column',
						}}>
						<h2 style={{ marginLeft: 320 }}>{roomInfo?.nowRoomNum} 번 채팅방</h2>
					</div>

					<h1>Chat</h1>
					<div
						style={{
							width: 500,
							height: 300,
							backgroundColor: '#f5d682',
							border: '1px solid red',
						}}>
						<div style={{ flex: 1, flexDirection: 'column' }}>
							{messages.map((item, index) => (
								<div key={index}>
									<strong>{item.id === socket.id ? 'You' : item.id}:</strong> {item.text}
								</div>
							))}
						</div>
					</div>
					<form onSubmit={sendMessage}>
						<input
							type='text'
							value={message}
							onChange={(e) => setMessage(e.target.value)}
						/>
						<button type='submit'>Send</button>
					</form>
					<div style={{ marginTop: 200 }}>
						<button onClick={leaveRoom}>방 나가기 </button>
					</div>
				</div>
			)}
		</>
	);
};
export default SocketIoRoomComponet;
