import React, { useEffect, useState } from 'react';
import { io, Socket } from 'socket.io-client';

const socket = io('http://localhost:5001'); // socket.io-client를 통해서 "normal" 네임스페이스의 소켓 서버에 연결합니다.

const SocketIoRedisComponent = () => {
	// 상태 관리
	const [message, setMessage] = useState<string>(''); // 입력되는 메시지
	const [messages, setMessages] = useState<{ id: string; text: string }[]>([]); // 누적되는 메시지 관리

	useEffect(() => {
		console.log('[+] Redis ');
		initSocket();
	}, []);

	/**
	 * Socket.io의 특정 이벤트가 발생했을 때 특정 작업을 수행하도록 등록합니다.
	 */
	const initSocket = () => {
		// [Socket] "message"가 발생하였을때, 이벤트가 수행합니다.
		socket.on('message', (message: { id: string; text: string }) => {
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
		// [Socket] 메시지를 전송합니다.
		socket.emit('message', newMessage);
		setMessage(''); // 전송 이후 입력한 텍스트를 지워줍니다.
	};

	return <></>;
};
export default SocketIoRedisComponent;
