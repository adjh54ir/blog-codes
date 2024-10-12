import axios, { AxiosResponse } from 'axios';
import { UserType } from '../types/UserTypes';
import { CommonType } from '../types/common/CommonType';

class UserService {
	selectUserList = async (
		userInfo: UserType.UserInfoType,
		token: string,
	): Promise<AxiosResponse<Boolean & CommonType.loginApiResponseType[], any>> => {
		return await axios.post(`/api/v1/user/user`, userInfo, {
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
		});
	};
}
export default new UserService();
