import React, { useEffect, useState } from 'react';
import { Client, IFrame, IMessage } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

interface MessagesType {
	sender: string; // 보내는 주체
	content: string; // 보내는 메시지
}

const StompComponent = () => {
	const SERVER_URL = 'http://localhost:8081/ws-stomp'; // STOMP 연결 엔드포인트
	const PUB_ENDPOINT = '/pub/messages'; // 메시지를 전송하기 위한 엔드포인트
	const SUB_ENDPOINT = '/sub/message'; // 메시지를 수신하기 위한 엔드포인트

	// STOMP가 연결되고 생성한 Client를 관리하는 상태 관리
	const [wsClient, setWsClient] = useState<Client>();

	// 채팅방 입장 여부
	const [isEnterChat, setIsEnterChat] = useState<boolean>(false);

	// 채팅에서 누적되는 데이터를 관리합니다.
	const [messages, setMessages] = useState<MessagesType[]>([]);

	// 사용자의 구분을 짓기 위해 임시로 발급한 사용자 아이디
	const [userId, setUserId] = useState(Math.random().toString(36).substr(2, 9));

	// 채팅에서 보내지는 데이터를 관리합니다.
	const [messageObj, setMessageObj] = useState<MessagesType>({
		content: '',
		sender: userId,
	});

	/**
	 * STOMP를 연결하고 라이프사이클을 관리하는 Handler
	 */
	const stompHandler = (() => {
		return {
			/**
			 * STOMP 연결을 시도합니다.
			 * @returns
			 */
			connect: () => {
				// [STEP1] 연결 시 Client 객체를 생성합니다.
				const client = new Client({
					webSocketFactory: () => new SockJS(SERVER_URL),
					reconnectDelay: 5000,
					heartbeatIncoming: 4000,
					heartbeatOutgoing: 4000,

					// [STEP2] 웹 소켓 연결
					onConnect: (conn: IFrame) => {
						console.log('[+] WebSocket 연결이 되었습니다.', conn);
						setIsEnterChat(true); // 채팅방 입장여부 상태를 변경
						// [WebSocket - Subscribe] 특정 엔드포인트로 메시지를 수신합니다.
						client.subscribe(SUB_ENDPOINT, (message: IMessage) => {
							const receiveData = JSON.parse(message.body);
							setMessages((prevMessages) => [
								...prevMessages,
								{
									content: receiveData.content,
									sender: receiveData.sender,
								},
							]);
						});
					},
					// 웹 소켓 연결 종료
					onWebSocketClose: (close) => {
						console.log('[-] WebSocket 연결이 종료 되었습니다.', close);
					},
					// 웹 소켓 연결 에러
					onWebSocketError: (error) => {
						console.error('[-] 웹 소켓 연결 오류가 발생하였습니다.', error);
					},
					// STOMP 프로토콜 에러
					onStompError: (frame) => {
						console.error('Broker reported error: ' + frame.headers['message']);
						console.error('Additional details: ' + frame.body);
					},
				});
				setWsClient(client); // 구성한 Client 객체를 상태 관리에 추가합니다.
				client.activate(); // Client를 활성화 합니다.

				return () => {
					stompHandler.disconnect(); // Socket 연결을 종료합니다.
				};
			},
			/**
			 * 웹 소켓 메시지를 전송합니다.
			 */
			sendMessage: () => {
				if (wsClient && wsClient.connected && messageObj.content.trim() !== '') {
					// [WebSocket - Publish] 특정 엔드포인트로 메시지를 전송합니다.
					wsClient.publish({
						destination: PUB_ENDPOINT,
						body: JSON.stringify({ ...messageObj, sender: userId }),
					});
					// 입력한 값을 초기화합니다.
					setMessageObj({ content: '', sender: userId });
				}
			},
			/**
			 * 웹 소켓 연결을 종료합니다.
			 */
			disconnect: () => {
				console.log('[-] 웹 소켓 연결을 종료합니다.');
				if (wsClient) {
					wsClient.deactivate();
					setWsClient(undefined);
					setIsEnterChat(false);
				}
			},
		};
	})();

	return (
		<div style={{}}>
			{!isEnterChat ? (
				<div
					style={{
						flexDirection: 'column',
						justifyContent: 'center',
						alignContent: 'center',
						alignItems: 'center',
						textAlign: 'center',
					}}>
					<h2>Spring Boot WebSocket 채팅방에 오신것을 환영합니다.</h2>
					<button onClick={stompHandler.connect}>채팅방 입장</button>
				</div>
			) : (
				<div
					style={{
						flex: 1,
						flexDirection: 'column',
						justifyContent: 'center',
						alignContent: 'center',
						alignItems: 'center',
						textAlign: 'center',
					}}>
					<h1>STOMP을 이용한 채팅방입니다. </h1>
					<div style={{ flexDirection: 'row' }}>
						<input
							type='text'
							value={messageObj.content}
							onChange={(e) => setMessageObj({ ...messageObj, content: e.target.value })}
						/>
						<button onClick={stompHandler.sendMessage}>전송</button>
					</div>
					<div
						style={{
							display: 'flex',
							flexDirection: 'column',
							height: 300,
							backgroundColor: '#f5d682',
							border: '1px solid red',
							margin: 20,
						}}>
						{messages.map((item, index) => (
							<h1
								style={{
									fontSize: 13,
									textAlign: 'left',
								}}
								key={`messages-${index}`}>
								{item.sender === userId ? `[ME] ${item.content}` : `[OTHER] ${item.content}`}
							</h1>
						))}
					</div>
					<div style={{ marginTop: 10 }}>
						<button onClick={stompHandler.disconnect}> 채팅방 나가기 </button>
					</div>
				</div>
			)}
		</div>
	);
};

export default StompComponent;
