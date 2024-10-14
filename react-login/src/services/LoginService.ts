import axios, { AxiosResponse } from 'axios';
import AxiosBlackListIntance from '../common/instance/AxiosBlackListIntance';
import AxiosCustomInstance from '../common/instance/AxiosCustomInstance';
import { LoginType } from '../types/LoginType';

class LoginService {
	/**
	 * 사용자 아이디, 비밀번호를 기반으로 API 호출
	 * @param loginInfo
	 */
	login = async (
		loginInfo: LoginType.LoginType,
	): Promise<AxiosResponse<Boolean & LoginType.loginApiResponseType, any>> => {
		try {
			return await AxiosBlackListIntance.post('/api/v1/user/login', loginInfo);
		} catch (error) {
			throw new Error(`Axios Login Error :${error}`);
		}
	};
}

export default new LoginService();
