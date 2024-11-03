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
			/**
			 * 카카오 로그인
			 */
			kakao: () => {
				const kakaoAuthUrl = process.env.REACT_APP_API_OAUTH2_KAKAO_AUTH_URL;
				const kakaoClientId = process.env.REACT_APP_API_OAUTH2_KAKAO_CLIENT_ID;
				const kakaoRedirectUrl = process.env.REACT_APP_API_OAUTH2_KAKAO_REDIRECT_URL;
				window.location.href = `${kakaoAuthUrl}?client_id=${kakaoClientId}&redirect_uri=${kakaoRedirectUrl}&response_type=code`;
			},
			/**
			 * 네이버 로그인
			 */
			naver: () => {
				const naverAuthUrl = process.env.REACT_APP_API_OAUTH2_NAVER_AUTH_URL;
				const naverClientId = process.env.REACT_APP_API_OAUTH2_NAVER_CLIENT_ID;
				const naverRedirectUrl = process.env.REACT_APP_API_OAUTH2_NAVER_REDIRECT_URL;
				window.location.href = `${naverAuthUrl}?client_id=${naverClientId}&redirect_uri=${naverRedirectUrl}&response_type=code&state=RANDOM_STATE`;
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
					flex: 1,
					flexDirection: 'row',
					justifyContent: 'center',
					alignItems: 'center',
				}}>
				<div style={{}}>
					<img
						style={{ width: 150, height: 40, marginRight: 40 }}
						src={'assets/icons/kakao_login_medium_narrow.png'}
						onClick={oAuthLoginHandler.kakao}
						alt='카카오'
					/>
					<img
						style={{ width: 150, height: 40 }}
						src={'assets/icons/btnG_complete_type.png'}
						onClick={oAuthLoginHandler.naver}
						alt='네이버'
					/>
				</div>
			</div>
		</div>
	);
};

export default OauthLoginComponent;
