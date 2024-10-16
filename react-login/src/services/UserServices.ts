import axios, { AxiosResponse } from 'axios';
import { UserType } from '../types/UserTypes';
import { CommonType } from '../types/common/CommonType';
import { CommonConstants } from '../common/CommonConstants';

class UserService {
	selectUserList = async (
		userInfo: UserType.UserInfoType,
		accessToken: string,
	): Promise<AxiosResponse<Boolean & CommonType.loginApiResponseType[], any>> => {
		return await axios.post(`/api/v1/user/user`, userInfo, {
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${accessToken}`,
			},
		});
	};
}
export default new UserService();
