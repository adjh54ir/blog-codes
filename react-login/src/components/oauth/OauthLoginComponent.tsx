import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import LoginService from '../../services/LoginService';
import { LoginType } from '../../types/LoginType';

const OauthLoginComponent = () => {
	useEffect(() => {
		console.log('[+] loading ...');
	}, []);
	const navigate = useNavigate();

	const [userInfo, setUserInfo] = useState<LoginType.LoginType>({
		userId: '',
		userPw: '',
	});

	const oAuthLoginHandler = (() => {
		return {
			kakao: () => {
				console.log("카카오 로그인")
			},
		};
	})();

	return (
		<div style={{ width: '100%', textAlign: 'center' }}>
			<div style={{ marginBottom: 100 }}>
				<h1 style={{ textAlign: 'center' }}>OAuth 2.0 기반 소셜 로그인</h1>
			</div>
			<div
				style={{
					flexDirection: 'column',
					justifyContent: 'center',
					alignItems: 'center',
				}}>
				<div style={{ padding: 20 }}>
					<img src={'assets/images/kakao_login_medium_narrow.png'} alt='카카오' onClick={oAuthLoginHandler.kakao} />
				</div>
			</div>
		</div>
	);
};

export default OauthLoginComponent;
