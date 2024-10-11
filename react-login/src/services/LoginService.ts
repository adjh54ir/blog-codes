import axios, { AxiosResponse } from 'axios';
import { LoginType } from '../types/LoginType';

class LoginService {
	/**
	 * 사용자 아이디, 비밀번호를 기반으로 API 호출
	 * @param loginInfo
	 */
	login = async (loginInfo: LoginType.LoginType): Promise<AxiosResponse<Boolean & LoginType.loginApiResponseType, any>> => {
		try {
			const response = await axios.post(`/api/v1/user/login`, loginInfo);
			console.log('Response:', response.data);
			return response;
		} catch (error) {
			throw new Error(`Error posting data:${error}`)
		}
	};
}

export default new LoginService();
