import React, { CSSProperties, useEffect, useState } from 'react';
import { useNavigate } from 'react-router';

import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { Client } from '@stomp/stompjs';
import { Link } from 'react-router-dom';

const MainComponenet = () => {
	useEffect(() => {
		return () => {};
	}, []);
	const handleMoveTo = (() => {
		return {
			main: () => {},
			socketIo: () => {},
			socketIoRoom: () => {},
			socketIoNameSpace: () => {},
			socketIosRedis: () => {},
			stompProtocol: () => {},
		};
	})();

	return (
		<>
			<h1>채팅방 선택</h1>
			<div style={styles.container}>
				<h2>Node.js</h2>
				<Link to='/socketIo' style={styles.linkButton}>
					<button style={styles.button}>Socket.IO 소켓 통신 기본 채팅방</button>
				</Link>
				<Link to='/socketIo/room' style={styles.linkButton}>
					<button style={styles.button}>Socket.IO 소켓 통신 Room 채팅방</button>
				</Link>
				<Link to='/socketIo/namespace' style={styles.linkButton}>
					<button style={styles.button}>Socket.IO 소켓 통신 NameSpace 채팅방</button>
				</Link>
				<Link to='/socketIo/redis' style={styles.linkButton}>
					<button style={styles.button}>Socket.IO 소켓 통신 PM2 + Redis 활용 채팅방</button>
				</Link>

				<h2>Java</h2>
				<Link to='/stomp' style={styles.linkButton}>
					<button style={styles.button}>STOMP 프로토콜을 이용한 기본 채팅방</button>
				</Link>
			</div>
		</>
	);
};
const styles: { [key: string]: CSSProperties } = {
	container: {
		width: '100%',
		height: '100%',
		display: 'flex',
		flexDirection: 'column',
		gap: '20px',
		marginLeft: 10,
	},
	linkButton: {
		width: 300,
		height: 50,
		display: 'inline-block',
		textDecoration: 'none',
	},
	button: {
		width: '100%',
		height: '100%',
	},
};

export default MainComponenet;
