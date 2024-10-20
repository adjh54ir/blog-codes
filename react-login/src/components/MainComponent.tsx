import UserServices from '../services/UserServices';
import { UserType } from '../types/UserTypes';
import { useNavigate } from 'react-router';

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
				const userInfo: UserType.UserInfoType = {
					userId: '',
					userNm: '',
					userSt: '',
				};
				await UserServices.selectUserList(userInfo)
					.then((res) => {
						console.log('결과 값 :: ', res.data);
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
