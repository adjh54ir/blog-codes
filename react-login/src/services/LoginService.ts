import { AxiosResponse } from 'axios';
import AxiosJwtInstance from '../common/instance/AxiosJwtInstance';
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
		return await AxiosJwtInstance.post('/api/v1/user/login', loginInfo);
	};
}

export default new LoginService();
