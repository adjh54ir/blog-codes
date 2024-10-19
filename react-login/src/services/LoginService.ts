import { AxiosResponse } from 'axios';
import AxiosJwtInstance from '../common/instance/AxiosJwtInstance';
import { LoginType } from '../types/LoginType';

class LoginService {
	/**
	 * 사용자 아이디, 비밀번호를 기반으로 API 호출
	 * @param loginInfo
	 */
	login = async (
		loginInfo: LoginType.LoginType,
	): Promise<AxiosResponse<Boolean & LoginType.loginApiResponseType, any>> => {
		return await AxiosJwtInstance.post('/api/v1/user/login', loginInfo);
	};
}

export default new LoginService();
