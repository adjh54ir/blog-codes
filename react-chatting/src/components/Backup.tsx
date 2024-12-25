import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { Client } from '@stomp/stompjs';
const Backup = () => {
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
				console.error(`Broker reported error: ${frame.headers['message']}`);
				console.error(`Additional details: ${frame.body}`);
			},
			onWebSocketError: (error) => {
				console.error(`WebSocket error: ${error}`);
			},
		});
		client.activate();
	};
};
export default Backup;
