import React from 'react';
import UserServices from '../services/UserServices';
import { UserType } from '../types/UserTypes';
import { useNavigate } from 'react-router';
import { eventWrapper } from '@testing-library/user-event/dist/utils';

const MainComponenet = () => {
	const navigation = useNavigate();

	const eventHandler = (() => {
		return {
			logout: () => {
				localStorage.removeItem('accessToken');
				navigation('/');
			},
		};
	})();

	const apiHandler = (() => {
		return {
			accessResource: async () => {
				console.log('[+] 리소스 접근을 시도합니다.');
				const accessToken = localStorage.getItem('accessToken');
				console.log('accessToken :: ', accessToken);
				const refreshToken = localStorage.getItem('refreshToken');
				console.log('refreshToken :: ', refreshToken);
				const userInfo: UserType.UserInfoType = {
					userId: '',
					userNm: '',
					userSt: '',
				};

				await UserServices.selectUserList(userInfo, accessToken!, refreshToken!)
					.then((res) => {
						console.log(res.data);
					})
					.catch((error) => {
						console.log(`error :: ${error} `);
					});
			},
		};
	})();

	return (
		<>
			<h1>로그인에 성공하였습니다.</h1>

			<div>
				<button type='button' onClick={apiHandler.accessResource}>
					리소스 접근
				</button>
			</div>

			<div>
				<button type='button' onClick={eventHandler.logout}>
					로그아웃
				</button>
			</div>
		</>
	);
};
export default MainComponenet;
