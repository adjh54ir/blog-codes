import axios, { AxiosResponse } from 'axios';
import AxiosBlackListIntance from '../common/instance/AxiosBlackListIntance';
import AxiosCustomInstance from '../common/instance/AxiosCustomInstance';
import { LoginType } from '../types/LoginType';
import AxiosTokenProgressIntance from '../common/instance/AxiosTokenProgressIntance';

class LoginService {
	/**
	 * 로그인을 수행합니다.
	 * @param loginInfo
	 */
	login = async (
		loginInfo: LoginType.LoginType,
	): Promise<AxiosResponse<Boolean & LoginType.loginApiResponseType, any>> => {
		try {
			return await AxiosTokenProgressIntance.post('/api/v1/user/login', loginInfo);
		} catch (error) {
			throw new Error(`Axios Login Error :${error}`);
		}
	};
}

export default new LoginService();
