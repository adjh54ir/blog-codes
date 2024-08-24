import React, { useEffect, useState } from 'react';
import { io } from 'socket.io-client';

/**
 * Namespace를 기반으로 일반 사용자 채팅방과 관리자 채팅방을 분리합니다.
 * @returns
 */

const socket = io('http://localhost:5001'); // socket.io-client를 통해서 소켓 서버에 연결합니다.

const SocketIoNameSpaceComponent = () => {
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

	const enterNamespace = (type: 'normal' | 'admin') => {
		const typeTxt = type === 'normal' ? '일반 사용자' : '관리자';
		console.log(`${typeTxt} 사용자가 입장하였습니다.`);
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
							width: '100%',
							textAlign: 'center',
							flex: 1,
							flexDirection: 'row',
							alignContent: 'cneter',
							alignItems: 'center',
						}}>
						<div
							style={{
								marginRight: 20,
								marginTop: 60,
								flex: 1,
								flexDirection: 'row',
								flexWrap: 'wrap',
							}}>
							<button
								style={{
									height: 100,
									marginRight: 20,
									marginBottom: 20,
									width: 300,
								}}
								onClick={() => enterNamespace('normal')}>
								일반 사용자 채팅방
							</button>
							<button
								style={{
									height: 100,
									marginRight: 20,
									marginBottom: 20,
									width: 300,
								}}
								onClick={() => enterNamespace('admin')}>
								관리자 채팅방
							</button>
						</div>
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
					{/* <div style={{ marginTop: 200 }}>
						<button onClick={leaveRoom}>방 나가기 </button>
					</div> */}
				</div>
			)}
		</>
	);
};
export default SocketIoNameSpaceComponent;
