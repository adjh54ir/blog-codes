import React from 'react';

const LoginComponent = () => {
	/**
	 * 로그인 페이지를 출력합니다.
	 */
	const openImplicitKeycloak = () => {
		const clientId = 'spring-boot-app';
		const redirectUri = 'http://localhost:3000/login/callback';
		// 브라우저 리다이렉트
		window.location.href = `http://localhost:9001/realms/dev-realm/protocol/openid-connect/auth?client_id=${clientId}&response_type=token&redirect_uri=${redirectUri}`;
	};

	return (
		<div>
			<button onClick={openImplicitKeycloak}>로그인 페이지 출력</button>
		</div>
	);
};
export default LoginComponent;
