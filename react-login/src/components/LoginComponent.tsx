import React, { useEffect, useRef, useState } from 'react';
import LoginService from '../services/LoginService';
import { LoginType } from '../types/LoginType';
import { useNavigate } from 'react-router';

const LoginComponent = () => {
	useEffect(() => { }, []);

	const navigate = useNavigate();

	const [userInfo, setUserInfo] = useState<LoginType.LoginType>({
		userId: '',
		userPw: '',
	});

	const loginHandler = (() => {
		return {
			login: async () => {
				console.log('[+] 로그인을 수행합니다.', userInfo);

				await LoginService.login(userInfo)
					.then((res) => {
						const { userInfo, resultCode, failMsg, accessToken, refreshToken } = res.data;
						console.log(userInfo, resultCode, failMsg);
						console.log(accessToken, refreshToken);

						if (userInfo && accessToken) {
							console.log('[+] 로그인에 성공하였습니다.', res);

							if (localStorage.getItem('accessToken')) {
								localStorage.setItem('accessToken', '');
							}
							localStorage.setItem('accessToken', accessToken); // 로컬 스토리지내에 저장합니다.
							navigate('/main');
						} else {
							console.log('[-] 로그인에 실패하였습니다.');
							alert('아이디와 비밀번호를 확인해주세요.');
						}
					})
					.catch((error) => {
						console.log('[-] 로그인에 실패하였습니다.', error);
					});
			},
		};
	})();

	return (
		<>
			<div>
				<h1 style={{ textAlign: 'center' }}>로그인</h1>
			</div>
			<div
				style={{
					flexDirection: 'column',
					justifyContent: 'center',
					alignItems: 'center',
				}}>
				<div style={{ width: '100%', flexDirection: 'row', justifyContent: 'center', alignItems: 'center', padding: 20 }}>
					<div>아이디 : </div>
					<input type='text' onChange={(e) => setUserInfo({ ...userInfo, userId: e.target.value })} />
				</div>

				<div style={{ flexDirection: 'row', padding: 20 }}>
					<div>비밀번호 : </div>
					<input type='password' onChange={(e) => setUserInfo({ ...userInfo, userPw: e.target.value })} />
				</div>

				<div style={{ padding: 20 }}>
					<button type='button' style={{ width: 150, height: 50 }} onClick={loginHandler.login}>
						로그인
					</button>
				</div>
			</div>
		</>
	);
};
export default LoginComponent;
