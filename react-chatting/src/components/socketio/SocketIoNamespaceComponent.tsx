import React, { useEffect, useState } from 'react';
import { io, Socket } from 'socket.io-client';

const socketNormal = io('http://localhost:5001/normal'); // socket.io-client를 통해서 "normal" 네임스페이스의 소켓 서버에 연결합니다.
const socketAdmin = io('http://localhost:5001/admin'); // socket.io-client를 통해서 "admin" 네임스페이스의 소켓 서버에 연결합니다.

/**
 * Namespace를 기반으로 일반 사용자 채팅방과 관리자 채팅방을 분리합니다.
 * @returns
 */
const SocketIoNameSpaceComponent = () => {
	// 상태 관리
	const [message, setMessage] = useState<string>(''); // 입력되는 메시지
	const [messages, setMessages] = useState<{ id: string; text: string }[]>([]); // 누적되는 메시지 관리

	// 방 정보를 관리합니다.
	const [namespaceInfo, setNamespaceInfo] = useState<{
		isEnter: boolean;
		name: '일반 사용자' | '관리자';
		type: 'normal' | 'admin';
		socket: Socket;
	}>({
		isEnter: false,
		name: '일반 사용자',
		type: 'normal',
		socket: socketNormal,
	});

	useEffect(() => {
		return () => {
			socketNormal.off('event:message'); // [Socket] "event:message" 이벤트의 메시지 수신을 종료합니다.
			socketNormal.off('event:message'); // [Socket] "event:message" 이벤트의 메시지 수신을 종료합니다.
		};
	}, []);

	/**
	 * namespace에 입장하고 이벤트를 등록합니다.
	 * @param type
	 */
	const enterNamespace = (type: 'normal' | 'admin') => {
		const typeTxt = type === 'normal' ? '일반 사용자' : '관리자';
		console.log(`${typeTxt} 사용자가 입장하였습니다.`);

		// namespace를 관리하는 상태를 변경합니다.
		setNamespaceInfo({
			isEnter: true,
			type: type,
			name: typeTxt,
			socket: type === 'normal' ? socketNormal : socketAdmin,
		});

		// [CASE1] "일반 사용자" 채팅방으로 접속하는 경우
		if (type === 'normal') {
			// [Socket] "normal" namespace 내에 "event:message"가 발생하였을때, State의 상태를 갱신합니다.
			socketNormal.on('event:message', (message: { id: string; text: string }) => {
				setMessages((prevMessages) => [...prevMessages, message]);
			});
		}
		// [CASE2] "관리자" 채팅방으로 접속하는 경우
		else if (type === 'admin') {
			// [Socket] "admin" namespace 내에 "event:message"가 발생하였을때, State의 상태를 갱신합니다.
			socketAdmin.on('event:message', (message: { id: string; text: string }) => {
				setMessages((prevMessages) => [...prevMessages, message]);
			});
		}
	};

	/**
	 * namespace를 떠납니다.
	 * @return void
	 *
	 */
	const leaveNamespace = (): void => {
		const { type } = namespaceInfo;
		if (type === 'normal') {
			// [Socket] "normal" namespace 내에 "event:message" 이벤트 리스너를 제거합니다.
			socketNormal.off('event:message');
		} else if (type === 'admin') {
			// [Socket] "admin" namespace 내에 "event:message" 이벤트 리스너를 제거합니다.
			socketNormal.off('event:message'); // 모든 이벤트 리스너 제거
		}
		setNamespaceInfo({
			isEnter: false,
			name: '일반 사용자',
			type: 'normal',
			socket: socketNormal,
		});
		setMessages([]);
	};

	/**
	 * 소켓을 통한 메시지 전송
	 * @param {React.FormEvent} e
	 * @return void
	 */
	const sendMessage = (e: React.FormEvent): void => {
		e.preventDefault();
		const { socket, type } = namespaceInfo;
		console.log('보내려는 namespace : ', type);

		if (type === 'normal') {
			const newMessage = { id: socket.id, text: message };
			socket.emit('event:message', newMessage); // [Socket] "normal" namespace 내에" "roomMessage" 이벤트를 발생시키며, 값을 전달합니다.
		}
		// 관리자인 경우 관리자 소켓에 접속
		else if (type === 'admin') {
			const newMessage = { id: socket.id, text: message };
			socket.emit('event:message', newMessage); // [Socket] "admin" namespace 내에 "roomMessage" 이벤트를 발생시키며, 값을 전달합니다.
		}
		setMessage(''); // 전송 이후 입력한 텍스트를 지워줍니다.
	};

	return (
		<>
			{namespaceInfo?.isEnter === false ? (
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
						<h2 style={{ marginLeft: 320 }}>{namespaceInfo.name} 채팅방</h2>
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
									<strong>
										{item.id === namespaceInfo.socket.id ? 'You' : item.id}:
									</strong>{' '}
									{item.text}
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
						<button onClick={leaveNamespace}>{namespaceInfo.name} 채팅방 나가기 </button>
					</div>
				</div>
			)}
		</>
	);
};
export default SocketIoNameSpaceComponent;
