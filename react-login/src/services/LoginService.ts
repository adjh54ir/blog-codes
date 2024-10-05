import axios from 'axios';
import { LoginType } from '../types/LoginType';

class LoginService {
	/**
	 * 사용자 아이디, 비밀번호를 기반으로 API 호출
	 * @param loginInfo
	 */
	login = async (loginInfo: LoginType.LoginType) => {
		try {
			const response = await axios.post(`/api/v1/user/login`, loginInfo);
			console.log('Response:', response.data);
		} catch (error) {
			console.error('Error posting data:', error);
		}
	};
}

export default new LoginService();
