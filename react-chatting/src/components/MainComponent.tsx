import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';

import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { Client } from '@stomp/stompjs';

const MainComponenet = () => {
	const [stompClient, setStompClient] = useState<Stomp.Client>();

	const API_URL = 'http://localhost:8080';

	const [messages, setMessages] = useState<string[]>([]);
	const [isEnterChat, setIsEnterChat] = useState<boolean>(false);
	const [messageObj, setMessageObj] = useState({
		content: '',
		sender: '',
	});

	useEffect(() => {
		return () => {};
	}, []);

	/**
	 * 채팅방 입장을 하게 되면 초기 소켓 서버에 추가됩니다.
	 * [STEP1] WebSocket 객체 생성
	 * [STEP2] WebScoket 연결
	 * [STEP3] 특정 WebSocket 구독 : 사용자들의 메시지를 받기 위해 Subscribe를 수행
	 */
	const enterChat = () => {
		console.log('채팅방에 입장하였습니다.');

		const client = new Client({
			brokerURL: 'ws://127.0.0.1:8080/ws-stomp',
			onConnect: () => {
				client.subscribe('/sub/messages', (message) =>
					console.log(`Received: ${message.body}`),
				);
				client.publish({
					destination: '/pub/topic/message',
					body: 'First Message',
				});
			},
			onStompError: (frame) => {
				console.error('Broker reported error: ', frame.headers['message']);
				console.error('Additional details:', frame.body);
			},
			onWebSocketError: (error) => {
				console.error('WebSocket error:', error);
				client.deactivate();
			},
		});
		client.activate();
	};

	/**
	 * 채팅관련 이벤트를 관리합니다.
	 */
	const handleChat = (() => {
		return {
			/**
			 * 전송 버튼을 눌렀을 경우 처리
			 */
			sendToMessage: () => {
				if (stompClient && messageObj.content.trim() !== '') {
					console.log('sendToMessage :: ', messageObj.content);

					//

					stompClient.send(
						`${API_URL}/pub/topic/messages`,
						{},
						JSON.stringify(messageObj),
					);
					// setMessageObj({
					// 	content: '',
					// 	sender: '',
					// });
				}
			},

			/**
			 *
			 */
			showMessage: (message: string) => {
				setMessages((prevMessages) => [...prevMessages, message]);
			},
		};
	})();

	return (
		<>
			{isEnterChat === false ? (
				<div
					style={{
						marginLeft: 500,
						marginTop: 300,
						flex: 1,
						flexDirection: 'column',
					}}>
					<h2>Spring Boot WebSocket 채팅방에 오신것을 환영합니다.</h2>
					<button style={{ marginLeft: 250 }} onClick={enterChat}>
						{' '}
						채팅방 입장{' '}
					</button>
				</div>
			) : (
				<div
					style={{
						marginLeft: 500,
						marginTop: 300,
					}}>
					<div>
						<input
							type='text'
							value={messageObj.content}
							onChange={(e) =>
								setMessageObj({ ...messageObj, content: e.target.value })
							}
						/>
						<button onClick={handleChat.sendToMessage}>전송</button>
					</div>
					<div
						id='response'
						style={{
							width: 200,
							height: 300,
							backgroundColor: '#f5d682',
							border: '1px solid red',
						}}>
						<div style={{ flex: 1, flexDirection: 'column' }}>
							<div>sadas</div>
							{messages.map((item, index) => (
								<h1>{item}</h1>
							))}
						</div>
					</div>
				</div>
			)}
		</>
	);
};

export default MainComponenet;
