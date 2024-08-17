import React, { useState, useEffect } from 'react';
import io from 'socket.io-client';

const SocketIoComponent = () => {
	// socket.io-client를 통해서 소켓 서버에 연결합니다.
	const socket = io('http://localhost:5001');

	// 상태 관리
	const [message, setMessage] = useState<string>(''); // 입력되는 메시지
	const [messages, setMessages] = useState<{ id: string; text: string }[]>([]); // 입력되는 메시지가 쌓이는 배열

	useEffect(() => {
		initSocket();

		return () => {
			socket.off('message');
		};
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

	/**
	 * 메시지를 전송하는 메서드
	 * @param e
	 */
	const sendMessage = (e: React.FormEvent) => {
		e.preventDefault();
		const newMessage = { id: socket.id, text: message };
		socket.emit('message', newMessage);
		setMessage('');
	};

	return (
		<div>
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
		</div>
	);
};

export default SocketIoComponent;
