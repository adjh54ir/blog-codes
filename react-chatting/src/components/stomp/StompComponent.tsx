import React, { useEffect, useState } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const StompComponent = () => {
	// WebSocket 서버 URL
	const SERVER_URL = 'ws://localhost:8081/ws-stomp';

	const [messages, setMessages] = useState<string[]>([]);
	const [isEnterChat, setIsEnterChat] = useState<boolean>(false);
	const [messageObj, setMessageObj] = useState({
		content: '',
		sender: '',
	});

	const [wsClient, setWsClient] = useState<Client>();

	useEffect(() => {
		console.log('[+] init');

		// STOMP 클라이언트 생성
		const client = new Client({
			webSocketFactory: () => {
				// SockJS 엔드포인트 설정
				socket = new SockJS(SERVER_URL);
				return socket;
			},
			reconnectDelay: 5000,
			heartbeatIncoming: 4000,
			heartbeatOutgoing: 4000,
		});

		setWsClient(client);

		console.log(client);

		return () => {
			handleStomp.disconnect();
		};
	}, []);

	let socket;

	const temp = () => {
		// 웹소켓 연결 설정
		const client = new Client({
			brokerURL: SERVER_URL,
			connectHeaders: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
				'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept',
			},

			onConnect: () => {
				console.log('Connected to STOMP');
				setWsClient(client);

				// 메시지 구독
				client.subscribe('/sub/topic/message', (message) => {
					console.log('[+] 메시지 구독이 되었습니다.');
					const receivedMessage = JSON.parse(message.body);
					setMessages((prevMessages) => [...prevMessages, receivedMessage]);
				});
			},
			debug: (msg: string) => {
				console.log('debug :: ', msg);
			},
		});

		client.activate();
		console.log('client :: ', client);

		// 컴포넌트 언마운트 시 연결 해제
		return () => {
			if (client) {
				client.deactivate();
			}
		};
	};

	/**
	 * STOMP 프로토콜에서 처리되는 이벤트들을 관리하는 Handler입니다.
	 */
	const handleStomp = (() => {
		return {
			/**
			 * 웹 소켓 연결을 수행합니다.
			 */
			connect: () => {
				console.log('Connected:', wsClient);
				// 토픽 구독
				const subscription = wsClient!.subscribe('/sub/messages', (message) => {
					console.log('Received message:', message.body);
				});

				console.log(subscription);

				// const client = new Client({
				// 	brokerURL: SERVER_URL,
				// 	connectHeaders: {
				// 		'heart-beat': '10000,10000',
				// 	},

				// 	reconnectDelay: 5000,
				// 	heartbeatIncoming: 10000,
				// 	heartbeatOutgoing: 10000,
				// 	debug: (str: string) => {
				// 		console.log(str);
				// 	},
				// 	onConnect: () => {
				// 		console.log('[+] 웹 소켓 연결이 완료되었습니다.');
				// 		setIsEnterChat(true);
				// 		client.subscribe(`${SERVER_URL}/sub/topic/message`, (message) => console.log(`Received: ${message.body}`));
				// 	},
				// 	onStompError: (frame) => {
				// 		console.error('Broker reported error: ', frame.headers['message']);
				// 		console.error('Additional details:', frame.body);
				// 	},
				// 	onWebSocketError: (error) => {
				// 		console.error('WebSocket error:', error);
				// 		client.deactivate();
				// 	},
				// });
				// setWsClient(client);

				// client.activate();
				// console.log('생성된 클라이언트', client);
			},
			/**
			 * 웹 소켓 연결을 종료합니다.
			 */
			disconnect: () => {
				if (wsClient) {
					wsClient.deactivate();
				}
			},

			/**
			 *
			 */
			sendMessage: (message: string) => {
				if (wsClient && messageObj.content.trim() !== '') {
					console.log('sendToMessage :: ', messageObj.content);

					wsClient.publish({
						destination: `${SERVER_URL}/pub/topic/messages`,
						body: JSON.stringify(messageObj),
					});
					setMessageObj({
						content: '',
						sender: '',
					});
				}
			},

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
					<button style={{ marginLeft: 250 }} onClick={handleStomp.connect}>
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
							onChange={(e) => setMessageObj({ ...messageObj, content: e.target.value })}
						/>
						<button onClick={() => handleStomp.sendMessage(messageObj.content)}>전송</button>
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
export default StompComponent;
